
//by aenu 2019

//license wtfpl2.0

#include <string.h>
#include <jnijjj.h>
#include <stdio.h>
#include <unistd.h>
#include <fcntl.h>
#include "log.h"

#include "clang-c/Index.h"

static inline unsigned getLineEnd(const char* text,int off){
    while(text[++off]!='\n');
    return off;
}

#define TRY_ADD_ERROR(Diagnostic,filepath,M) {          \
    CXFile File;                                        \
    unsigned Position;                                  \
    clang_getSpellingLocation(                          \
      clang_getDiagnosticLocation(Diagnostic),          \
        &File,0,0,&Position);                           \
    if (File) {                                         \
      CXString FName = clang_getFileName(File);         \
      if(strcmp(clang_getCString(FName),filepath)==0){  \
        CXString Text = clang_getDiagnosticSpelling(Diagnostic);   \
        const char* text__=clang_getCString(Text);                 \
        jstring loc_text=(*env)->NewStringUTF(env,text__?:"...");  \
        clang_disposeString(Text);                                 \
        (*env)->CallVoidMethod(env,self,M,Position,                \
            getLineEnd(sourceFile.Contents,Position),loc_text );   \
        (*env)->DeleteLocalRef(env, loc_text);          \       
      }                                                 \
      clang_disposeString(FName);                       \
    }                                                   \
}
      

#define TRY_ADD_WARNING(Diagnostic,filepath,M) {        \
    CXFile File;                                        \
    unsigned Position;                                  \
    clang_getSpellingLocation(                          \
      clang_getDiagnosticLocation(Diagnostic),          \
        &File,0,0,&Position);                           \
    if (File) {                                         \
      CXString FName = clang_getFileName(File);         \
      if(strcmp(clang_getCString(FName),filepath)==0){  \
        unsigned N = clang_getDiagnosticNumRanges(Diagnostic);           \
        CXFile StartFile, EndFile;                                       \
        unsigned StartP,EndP;                                            \
        CXSourceRange SRange = clang_getDiagnosticRange(Diagnostic,0);   \
        CXSourceRange ERange = clang_getDiagnosticRange(Diagnostic,N-1); \
        clang_getSpellingLocation(clang_getRangeStart(SRange),           \
                                    &StartFile,0,0,&StartP);             \
        clang_getSpellingLocation(clang_getRangeEnd(ERange),             \
                                    &EndFile,0,0,&EndP);                 \
        if (StartFile == EndFile && StartFile == File){                  \
          CXString Text = clang_getDiagnosticSpelling(Diagnostic);       \
          const char* text__=clang_getCString(Text);                     \
          jstring loc_text=(*env)->NewStringUTF(env,text__?:"...");      \
          clang_disposeString(Text);                                     \    
          (*env)->CallVoidMethod(env,self,M,StartP,EndP,loc_text);       \      
          (*env)->DeleteLocalRef(env, loc_text);        \    
        }                                               \
      }                                                 \
      clang_disposeString(FName);                       \
    }                                                   \
}

static void 
cxx_diag(JNIEnv* env,jobject self,jstring source,jstring source_file_path,jobjectArray cxflags){
   
    jmethodID mid_add_warning=(*env)->GetMethodID(
      env,
      (*env)->GetObjectClass(env,self),
      "nc_add_warning","(IILjava/lang/String;)V");
    jmethodID mid_add_error=(*env)->GetMethodID(
      env,
      (*env)->GetObjectClass(env,self),
      "nc_add_error","(IILjava/lang/String;)V");
    
    struct CXUnsavedFile sourceFile;
    memset(&sourceFile,0,sizeof (struct CXUnsavedFile));
    
    sourceFile.Filename=(*env)->GetStringUTFChars(env,source_file_path,NULL);
    sourceFile.Contents=(*env)->GetStringUTFChars(env,source,NULL);
    sourceFile.Length=(*env)->GetStringUTFLength(env,source);
    
    CXIndex index=clang_createIndex(0,1);
    
    CXTranslationUnit translationUnit ;
    
    int n=(*env)->GetArrayLength(env,cxflags);
    
    char** cmds=(char**)malloc(sizeof(char*)*n);
    
#define INIT_CMD(IN,OUT,N)                                    \
    for(int i=0;i<N;i++){                                     \
        jstring cmd=(*env)->GetObjectArrayElement(env,IN,i);  \
        OUT[i]=(*env)->GetStringUTFChars(env,cmd,NULL);       \
    }
    
    INIT_CMD(cxflags,cmds,n);
#undef INIT_CMD

    enum CXErrorCode EC=
      clang_parseTranslationUnit2(
        index, sourceFile.Filename,
        cmds,n,
        &sourceFile, 1, 
        clang_defaultEditingTranslationUnitOptions()
       , &translationUnit);
    LOGW("EC %d",EC);
   
#define FREE_CMD(J,C,N)                                       \
    for(int i=0;i<N;i++){                                     \
        jstring cmd=(*env)->GetObjectArrayElement(env,J,i);   \
        (*env)->ReleaseStringUTFChars(env,cmd,C[i]);          \
    }
    
    FREE_CMD(cxflags,cmds,n)
#undef FREE_CMD
        
    unsigned NumDiags;
    CXDiagnostic Diag;
    CXString DiagStr;

    NumDiags=clang_getNumDiagnostics(translationUnit);
    
    for (int i=0;i!=NumDiags;++i){
      Diag=clang_getDiagnostic(translationUnit,i);
      switch(clang_getDiagnosticSeverity(Diag)){
          case CXDiagnostic_Warning:
              TRY_ADD_WARNING(Diag,sourceFile.Filename,mid_add_warning);
              break;
          case CXDiagnostic_Error:
          case CXDiagnostic_Fatal:
              TRY_ADD_ERROR(Diag,sourceFile.Filename,mid_add_error);
              break;
      }
      
      clang_disposeDiagnostic(Diag);
    }
    
    clang_disposeTranslationUnit(translationUnit);
    clang_disposeIndex(index);
    
    (*env)->ReleaseStringUTFChars(env,source_file_path,sourceFile.Filename);
    (*env)->ReleaseStringUTFChars(env,source,sourceFile.Contents);
}
   
static int
register_cxx_diag(JNIEnv* env){
    static const char* const class_path = "aenu/eide/PL/CxxCodeDiag$DiagThread";
    static const JNINativeMethod const methods[] = {
      { "native_diag", "(Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;)V",(void*)cxx_diag}
    };
    jclass clazz = (*env)->FindClass(env,class_path);
    
    return (*env)->RegisterNatives(env,clazz, methods, sizeof(methods)/sizeof(methods[0]));
}

__attribute__((visibility("default"),club))
jint JNI_OnLoad(JavaVM* vm, void* reserved) {
    
    jint result = -1;
    JNIEnv* env = NULL;
    
    if ((*vm)->GetEnv(vm,&env, JNI_VERSION_1_4) != JNI_OK) {
        LOGE("ERROR: GetEnv failed");
        goto bail;
    }
    
    if (register_cxx_diag(env) != 0) {
        LOGE("ERROR: register_cxx_diag");
        goto bail;
    }

    result = JNI_VERSION_1_4;

bail:
    return result;
}

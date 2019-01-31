

//by aenu 2019

//license wtfpl2.0

#include <string.h>
#include <jni.h>
#include <stdio.h>
#include <unistd.h>
#include <fcntl.h>
#include <sys/stat.h> 
#include "log.h"

#include "clang-c/Index.h"    

#ifndef PAGESIZE
#define PAGESIZE 4096
#endif

static inline int read_file(const char* const path,char** ptr){
    struct stat st;

    if(stat(path, &st) < 0){  
        LOGE("获取文件信息失败");
        exit(55);
    }else{  
        *ptr=malloc(st.st_size);  
        
        {//read
            char buf[PAGESIZE];
            int n;
            int fp=open(path,O_RDONLY);
            int total=0;
            while(n=read(fp,buf,PAGESIZE)){
                memcpy(*ptr+total,buf,n);
                total+=n;
            }
            
            return total;
        }
    }  
}

static inline unsigned getLineEnd(const char* text,int off){
    while(text[++off]!='\n');
    return off;
}

#define ADD_DIAG(Diagnostic,M) {                        \
    CXFile File;                                        \
    unsigned Line,Column,Position;                      \
    clang_getSpellingLocation(                          \
      clang_getDiagnosticLocation(Diagnostic),          \
        &File,&Line,&Column,&Position);                 \
    if (File) {                                         \
      CXString FName = clang_getFileName(File);         \
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
        const char* file__=clang_getCString(FName);                    \
        jstring loc_text=(*env)->NewStringUTF(env,text__?:"...");      \
        clang_disposeString(Text);                                     \
        jstring loc_file=(*env)->NewStringUTF(env,file__);             \
        (*env)->CallVoidMethod(env,self,M,                             \
                loc_file,StartP,EndP,Line,Column,loc_text);            \      
        (*env)->DeleteLocalRef(env, loc_text);        \
        (*env)->DeleteLocalRef(env, loc_file);        \
      }                                               \
      clang_disposeString(FName);                     \
    }                                                 \
}                        


#define ADD_FATAL(Diagnostic,M) {                       \
    CXFile File;                                        \
    unsigned Line,Column,Position;                      \
    clang_getSpellingLocation(                          \
      clang_getDiagnosticLocation(Diagnostic),          \
        &File,&Line,&Column,&Position);                 \
    if (File) {                                         \
      CXString FName = clang_getFileName(File);         \
      CXString Text = clang_getDiagnosticSpelling(Diagnostic);   \
      const char* text__=clang_getCString(Text);                 \
      const char* file__=clang_getCString(FName);                \
      jstring loc_text=(*env)->NewStringUTF(env,text__?:"...");  \
      jstring loc_file=(*env)->NewStringUTF(env,file__);         \   
      clang_disposeString(Text);                                 \
      clang_disposeString(FName);                                \
      (*env)->CallVoidMethod(env,self,M,loc_file                 \
              ,Position,getLineEnd(file_data,Position)           \
                      ,Line,Column,loc_text);                    \      
      (*env)->DeleteLocalRef(env, loc_text);            \       
      (*env)->DeleteLocalRef(env, loc_file);            \
    }                                                   \
}

static void
cxx_diag_from_file(JNIEnv* env,jobject self,jstring filePath,jobjectArray cxflags){
    jmethodID mid_add_warning=(*env)->GetMethodID(
      env,
      (*env)->GetObjectClass(env,self),
      "nc_add_warning","(Ljava/lang/String;IIIILjava/lang/String;)V");
    jmethodID mid_add_error=(*env)->GetMethodID(
      env,
      (*env)->GetObjectClass(env,self),
      "nc_add_error","(Ljava/lang/String;IIIILjava/lang/String;)V");
      
    const char* file_path=(*env)->GetStringUTFChars(env,filePath,NULL);
  
    int n=(*env)->GetArrayLength(env,cxflags);
    
    char** cmds=(char**)malloc(sizeof(char*)*n);
    
    CXIndex index=clang_createIndex(0,1);
    
#define INIT_CMD(IN,OUT,N)                                    \
    for(int i=0;i<N;i++){                                     \
        jstring cmd=(*env)->GetObjectArrayElement(env,IN,i);  \
        OUT[i]=(*env)->GetStringUTFChars(env,cmd,NULL);       \
    }
    
    INIT_CMD(cxflags,cmds,n);
#undef INIT_CMD

    struct CXUnsavedFile sourceFile;
    memset(&sourceFile,0,sizeof (struct CXUnsavedFile));
    
    char* file_data;
    
    sourceFile.Filename=file_path;
    sourceFile.Length=read_file(file_path,&file_data);
    sourceFile.Contents=file_data;
    
    CXTranslationUnit TU;
    enum CXErrorCode EC=
      clang_parseTranslationUnit2(
        index, file_path,
        cmds,n,
        &sourceFile,1, 
        clang_defaultEditingTranslationUnitOptions()
       , &TU);
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

    NumDiags=clang_getNumDiagnostics(TU);

    for (int i=0;i!=NumDiags;++i){
      Diag=clang_getDiagnostic(TU,i);
      switch(clang_getDiagnosticSeverity(Diag)){
          case CXDiagnostic_Warning:
              ADD_DIAG(Diag,mid_add_warning);
              break;
          case CXDiagnostic_Error:   
              ADD_DIAG(Diag,mid_add_error);
              break;
          case CXDiagnostic_Fatal:          
              ADD_FATAL(Diag,mid_add_error);      
              break;
      }
      clang_disposeDiagnostic(Diag);
    }
    
    clang_disposeTranslationUnit(TU);
    clang_disposeIndex(index);
    
    free(file_data);
    
    (*env)->ReleaseStringUTFChars(env,filePath,file_path);
  
}


static int
register_cx_diag(JNIEnv* env){
    static const char* const class_path = "aenu/eide/diagnostic/CXProjectDiagnostic$CXDiagsticThread";
    static const JNINativeMethod const methods[] = {
      { "native_diag_from_file", "(Ljava/lang/String;[Ljava/lang/String;)V",(void*)cxx_diag_from_file}
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
    
    if (register_cx_diag(env) != 0) {
        LOGE("ERROR: register_cx_diag");
        goto bail;
    }

    result = JNI_VERSION_1_4;

bail:
    return result;
}


//by aenu
//date 2018:11:19
//  -- 2018:11:21

//license wtfpl2.0

#include <string.h>
#include <jni.h>
#include <stdio.h>
#include <unistd.h>
#include <fcntl.h>
#include "log.h"

#include "clang-c/Index.h"

static void 
cxx_tokenize(JNIEnv* env,jobject self,jstring source,jstring source_file_path,jobjectArray cxflags){
   
    jmethodID mid_add_pair=(*env)->GetMethodID(
      env,
      (*env)->GetObjectClass(env,self),
      "nc_add_pair","(II)V");
    
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
        
    CXFile file=clang_getFile(translationUnit,sourceFile.Filename);
    CXSourceLocation start_loc=clang_getLocationForOffset(translationUnit,file,0);
    CXSourceLocation end_loc=clang_getLocationForOffset(translationUnit,file,sourceFile.Length-1);
    CXSourceRange range = clang_getRange(start_loc, end_loc);
    
    CXToken *Tokens;
    unsigned NumTokens;
        
    clang_tokenize(translationUnit,range,&Tokens,&NumTokens);
        
    for(unsigned j=0;j<NumTokens;j++){
        CXSourceLocation tk_loc=clang_getTokenLocation(translationUnit,Tokens[j]);
        unsigned line,column,offset;
        clang_getExpansionLocation(tk_loc,0,&line,&column,&offset);
        
        static const enum{
            CXToken_Punctuation_Type=2,
            CXToken_Keyword_Type=1,
            CXToken_Identifier_Type=3,
            CXToken_Literal_Type=4,
            CXToken_Comment_Type=2
        };
        
#define HANDLE_CASE(X) \
  case X: \
    (*env)->CallVoidMethod(env,self,mid_add_pair,offset,X##_Type); \
      break;
          
        switch(clang_getTokenKind(Tokens[j])){
            HANDLE_CASE(CXToken_Punctuation)       
            HANDLE_CASE(CXToken_Keyword)        
            HANDLE_CASE(CXToken_Identifier)
            HANDLE_CASE(CXToken_Literal)
            HANDLE_CASE(CXToken_Comment)             
            default:LOGI("???");break;
        }  
#undef HANDLE_CASE
    }
    clang_disposeTokens(translationUnit,Tokens,NumTokens);   
    
    clang_disposeTranslationUnit(translationUnit);
    clang_disposeIndex(index);
    
    (*env)->ReleaseStringUTFChars(env,source_file_path,sourceFile.Filename);
    (*env)->ReleaseStringUTFChars(env,source,sourceFile.Contents);
}
   
static int
register_cxx_lexer(JNIEnv* env){
    static const char* const class_path = "aenu/eide/PL/CxxLexer$LexThread";
    static const JNINativeMethod const methods[] = {
      { "native_tokenize", "(Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;)V",(void*)cxx_tokenize}
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
    
    if (register_cxx_lexer(env) != 0) {
        LOGE("ERROR: register_cxx_lexer");
        goto bail;
    }

    result = JNI_VERSION_1_4;

bail:
    return result;
}

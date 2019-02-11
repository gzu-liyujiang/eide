
//by aenu
//date 2018:11:22

//license wtfpl2.0

#include <string.h>
#include <jni.h>
#include <stdio.h>
#include "log.h"

#include "clang-c/Index.h"

static void inline
get_line_and_column(const char* str,unsigned offset,unsigned* line,unsigned* column){
    unsigned cur_line=1;
    unsigned cur_column=0;
    
    for(unsigned i=0;i<offset;i++){
        char c=str[i];
        if(c=='\n'){
            cur_line+=1;
            cur_column=0;
        }
        else{
            cur_column+=1;
        }
    }
    
    *line=cur_line;
    *column=cur_column;
}

#define strlow(str) { \
    unsigned strl=strlen(str); \
    for(int stri=0;stri<strl;stri++) { \
        char strc=str[stri]; \
        if(strc>='A'&&strc<='Z') \
            str[stri]=strc+'a'-'A'; \
    } \
}
    

static void 
cxx_complete(JNIEnv* env,jobject self,jstring source,jint pos,jint replacePos,jstring source_file_path,jobjectArray cxflags){
   
    jmethodID mid_add_complete=(*env)->GetMethodID(
      env,
      (*env)->GetObjectClass(env,self),
      "nc_add_complete","(Landroid/graphics/Bitmap;Ljava/lang/String;Ljava/lang/String;)V");
    
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

    unsigned line, column;
    get_line_and_column(sourceFile.Contents,pos,&line,&column);
   
    char input[256];
    unsigned len=pos-replacePos;
    
    memcpy(input,&sourceFile.Contents[replacePos],len);     
    input[len]=0;
    strlow(input);
    
    {//default
    
        typedef struct{
            const char* const text;
            const char* const hint;        
        }CodeComplete;
        static const CodeComplete DCC[]={
            //
            {"#ifdef",0},
            {"#define",0},
            {"#else",0},
            {"#ifndef",0},
            {"#endif",0},
            {"#include",0},
            {"#program",0},
            {"#error",0},
            {"#undef",0},
            //
            {"__attribute__((",0},
            //
            {"__builtin_return_address(",0},
            {"__builtin_frame_address(",0},
            {"__builtin_expect(",0},
            {"__builtin_memcpy(",0},
            {"__builtin_memset(",0},
            {"__builtin_memcmp(",0},
            {"__builtin_strcat(",0},
            {"__builtin_strcmp(",0},
            {"__builtin_strcpy(",0},
            {"__builtin_strlen(",0},
            {"__builtin_log(",0},
            {"__builtin_cos(",0},
            {"__builtin_abs(",0},
            {"__builtin_exp(",0},    
            {"__builtin_printf(",0},
            {"__builtin_scanf(",0},
            {"__builtin_putchar(",0},
            {"__builtin_puts(",0},
        };
        
        for(int i=0;i<sizeof(DCC)/sizeof(DCC[0]);i++)
            if(__builtin_memcmp(input,DCC[i].text,len)==0){
                jstring loc_text;
                jstring loc_hint;
            
                (*env)->CallVoidMethod(env,self,mid_add_complete,
                  0,
                  loc_text=(*env)->NewStringUTF(env,DCC[i].text),
                  loc_hint=(DCC[i].hint==0?0:(*env)->NewStringUTF(env,DCC[i].hint)) );
            
                //.....
                (*env)->DeleteLocalRef(env, loc_text);
                if(loc_hint) (*env)->DeleteLocalRef(env, loc_hint);
            }
    }
    
    /*
#define BTH(OUT,IN,BITS) {\
    OUT=0;\
    for(unsigned i=0;i<BITS;i++){ \
        unsigned t=1;\
        for(int j=0;j<i;j++){ \
            t*=10; \
            if(IN/t%10==1) \
                OUT|=(1<<j);\
        }\
    }\
}
    
    unsigned ccOpt;
    
    //        10987654321098765432109876543210
    BTH(ccOpt,          1111111111111111111111,32);
    
#undef BTH   */

    unsigned ccOpt=0x001fffff;//
    
    CXCodeCompleteResults* compResults = clang_codeCompleteAt(translationUnit, sourceFile.Filename,
        line, column,&sourceFile, 1, ccOpt);//clang_defaultCodeCompleteOptions());
   
   // LOGW("N %d",compResults->NumResults);
        
    for(unsigned i=0;i<compResults->NumResults;i++){
        CXCompletionResult result = compResults->Results[i]; 
        
        unsigned chunks=clang_getNumCompletionChunks(result.CompletionString);
       
        char text[256];
        char hint[256];
        text[0]=0;
        hint[0]=0;
   
#define HANDLE_CASE(X,M) \
  case CXCompletionChunk_##X:{\
    M;\
  }break;
        
        for(unsigned j=0;j<chunks;j++){
            CXString cx_text=clang_getCompletionChunkText(result.CompletionString,j);
            enum CXCompletionChunkKind cx_kind = clang_getCompletionChunkKind(result.CompletionString,j);
         
            switch(cx_kind){
                HANDLE_CASE(Optional,    
                  sprintf(&text[strlen(text)],"%s",clang_getCString(cx_text));
                );
                HANDLE_CASE(TypedText,
                  sprintf(&text[strlen(text)],"%s",clang_getCString(cx_text));
                );
                HANDLE_CASE(Text,
                  sprintf(&hint[strlen(hint)],"%s",clang_getCString(cx_text));
                );
                HANDLE_CASE(Placeholder,
                  sprintf(&hint[strlen(hint)],"%s",clang_getCString(cx_text));
                );
                HANDLE_CASE(Informative,
                  sprintf(&hint[strlen(hint)],"%s",clang_getCString(cx_text));        
                );
                HANDLE_CASE(CurrentParameter,
                  sprintf(&hint[strlen(hint)],"%s",clang_getCString(cx_text));     
                );
                HANDLE_CASE(LeftParen,
                  sprintf(&hint[strlen(hint)],"%s",clang_getCString(cx_text));
                );
                HANDLE_CASE(RightParen,
                  sprintf(&hint[strlen(hint)],"%s",clang_getCString(cx_text));
                );
                HANDLE_CASE(LeftBracket,
                  sprintf(&hint[strlen(hint)],"%s",clang_getCString(cx_text));
                );
                HANDLE_CASE(RightBracket,
                  sprintf(&hint[strlen(hint)],"%s",clang_getCString(cx_text));
                );
                HANDLE_CASE(LeftBrace,
                  sprintf(&hint[strlen(hint)],"%s",clang_getCString(cx_text));
                );
                HANDLE_CASE(RightBrace,
                  sprintf(&hint[strlen(hint)],"%s",clang_getCString(cx_text));
                );
                HANDLE_CASE(LeftAngle,
                  sprintf(&hint[strlen(hint)],"%s",clang_getCString(cx_text));
                );
                HANDLE_CASE(RightAngle,
                  sprintf(&hint[strlen(hint)],"%s",clang_getCString(cx_text));
                );
                HANDLE_CASE(Comma,
                  sprintf(&hint[strlen(hint)],"%s",clang_getCString(cx_text));
                );
                HANDLE_CASE(ResultType,
                  sprintf(&hint[strlen(hint)],"%s",clang_getCString(cx_text));        
                );
                HANDLE_CASE(Colon,
                  sprintf(&hint[strlen(hint)],"%s",clang_getCString(cx_text));
                );             
                HANDLE_CASE(SemiColon,
                  sprintf(&hint[strlen(hint)],"%s",clang_getCString(cx_text));
                );          
                HANDLE_CASE(Equal,
                  sprintf(&hint[strlen(hint)],"%s",clang_getCString(cx_text));
                );          
                HANDLE_CASE(HorizontalSpace,
                  sprintf(&hint[strlen(hint)],"%s",clang_getCString(cx_text));
                );          
                HANDLE_CASE(VerticalSpace,
                  sprintf(&hint[strlen(hint)],"%s",clang_getCString(cx_text));
                );                       
            }
            clang_disposeString(cx_text);
        }
       
#undef HANDLE_CASE
    
        char text_low[256];
       
        __builtin_strcpy(text_low,text);
        strlow(text_low);
        
        if(__builtin_memcmp(input,text_low,len)==0){
            
            jstring loc_text;
            jstring loc_hint;
            
            (*env)->CallVoidMethod(env,self,mid_add_complete,
              0,
              loc_text=(*env)->NewStringUTF(env,text),
              loc_hint=(hint[0]==0?0:(*env)->NewStringUTF(env,hint)) );
            
            //.....
            (*env)->DeleteLocalRef(env, loc_text);
            if(loc_hint) (*env)->DeleteLocalRef(env, loc_hint);
        }     
    }
    
    (*env)->ReleaseStringUTFChars(env,source_file_path,sourceFile.Filename);
    (*env)->ReleaseStringUTFChars(env,source,sourceFile.Contents);
}
   
#undef strlow

static int
register_cxx_complete(JNIEnv* env){
    
    static const char* const class_path = "aenu/eide/PL/CxxAutoCompletePanel";
    static const JNINativeMethod const methods[] = {
      { "native_complete", "(Ljava/lang/String;IILjava/lang/String;[Ljava/lang/String;)V",(void*)cxx_complete}
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
    
    if (register_cxx_complete(env) != 0) {
        LOGE("ERROR: register_cxx_complete");
        goto bail;
    }

    result = JNI_VERSION_1_4;

bail:
    return result;
}

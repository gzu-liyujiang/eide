
//by aenu 2019

//license wtfpl2.0

#include <string.h>
#include <jni.h>
#include <stdio.h>
#include <unistd.h>
#include <fcntl.h>
#include <sys/stat.h>
#include <stdlib.h>
#include "log.h"

#include "clang-c/Index.h"

#ifndef PAGESIZE
#define PAGESIZE 4096
#endif

static inline int read_file(const char *const path, char **ptr) {
    struct stat st;

    if (stat(path, &st) < 0) {
        LOGE("获取文件信息失败");
        exit(55);
    } else {
        *ptr = malloc(st.st_size);

        {//read
            char buf[PAGESIZE];
            int n;
            int fp = open(path, O_RDONLY);
            int total = 0;
            while (n = read(fp, buf, PAGESIZE)) {
                memcpy(*ptr + total, buf, n);
                total += n;
            }

            return total;
        }
    }
}

static inline unsigned getLineEnd(const char *text, int off) {
    char c;
    while (c = text[++off])
        if (c == '\n') break;
    return off;
}

static jmethodID cb_onNewError;
static jmethodID cb_onNewWarning;

static jmethodID msg_newMessage;

static jclass DiagnosticMessage;

static inline void
initialize_jni(JNIEnv *env) {

    jclass callback = (*env)->FindClass(env, "aenu/eide/diagnostic/DiagnosticCallback");
    jclass message = (*env)->FindClass(env, "aenu/eide/diagnostic/DiagnosticMessage");

    cb_onNewError = (*env)->GetMethodID(
            env, callback,
            "onNewError", "(Laenu/eide/diagnostic/DiagnosticMessage;)V");

    cb_onNewWarning = (*env)->GetMethodID(
            env, callback,
            "onNewWarning", "(Laenu/eide/diagnostic/DiagnosticMessage;)V");

    msg_newMessage = (*env)->GetStaticMethodID(
            env, message,
            "newMessage",
            "(Ljava/lang/String;IIIILjava/lang/String;)Laenu/eide/diagnostic/DiagnosticMessage;");

    DiagnosticMessage = (*env)->NewGlobalRef(
            env, message);
}

static inline void
release_jni(JNIEnv *env) {
    (*env)->DeleteGlobalRef(env, DiagnosticMessage);
}

#define ON_DIAG(Diagnostic, M) {                         \
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
      jobject msg=(*env)->CallStaticObjectMethod(env,            \
          DiagnosticMessage,msg_newMessage,                      \
              loc_file,Position,getLineEnd(file_data,Position),  \
                  Line,Column,loc_text);                \      
      (*env)->CallVoidMethod(env,callback,M,msg);       \
      (*env)->DeleteLocalRef(env, msg);                 \    
      (*env)->DeleteLocalRef(env, loc_text);            \       
      (*env)->DeleteLocalRef(env, loc_file);            \
    }                                                   \
}

__attribute__((hot)) static void
cxx_diag_from_file(JNIEnv *env, jobject self, jstring filePath, jobjectArray cxflags,
                   jobject callback) {

    const char *file_path = (*env)->GetStringUTFChars(env, filePath, NULL);

    int n = (*env)->GetArrayLength(env, cxflags);

    char **cmds = (char **) malloc(sizeof(char *) * n);

    CXIndex index = clang_createIndex(0, 1);

#define INIT_CMD(IN, OUT, N)                                    \
    for(int i=0;i<N;i++){                                     \
        jstring cmd=(*env)->GetObjectArrayElement(env,IN,i);  \
        OUT[i]=(*env)->GetStringUTFChars(env,cmd,NULL);       \
    }

    INIT_CMD(cxflags, cmds, n);
#undef INIT_CMD

    struct CXUnsavedFile sourceFile;
    memset(&sourceFile, 0, sizeof(struct CXUnsavedFile));

    char *file_data;

    sourceFile.Filename = file_path;
    sourceFile.Length = read_file(file_path, &file_data);
    sourceFile.Contents = file_data;

    CXTranslationUnit TU;
    enum CXErrorCode EC =
            clang_parseTranslationUnit2(
                    index, file_path,
                    cmds, n,
                    &sourceFile, 1,
                    clang_defaultEditingTranslationUnitOptions(), &TU);
    LOGW("EC %d", EC);
#define FREE_CMD(J, C, N)                                       \
    for(int i=0;i<N;i++){                                     \
        jstring cmd=(*env)->GetObjectArrayElement(env,J,i);   \
        (*env)->ReleaseStringUTFChars(env,cmd,C[i]);          \
    }

    FREE_CMD(cxflags, cmds, n)
#undef FREE_CMD

    unsigned NumDiags;
    CXDiagnostic Diag;
    CXString DiagStr;
    enum CXDiagnosticSeverity ds;


    NumDiags = clang_getNumDiagnostics(TU);

    for (int i = 0; i != NumDiags; ++i) {
        Diag = clang_getDiagnostic(TU, i);

        switch (ds = clang_getDiagnosticSeverity(Diag)) {
            case CXDiagnostic_Warning:
                //  LOGW("CXDiagnostic_Warning");
            ON_DIAG(Diag, cb_onNewWarning);
                break;
            case CXDiagnostic_Error:
                //  LOGW("CXDiagnostic_Error");
            ON_DIAG(Diag, cb_onNewError);
                break;
            case CXDiagnostic_Fatal:
                //  LOGW("CXDiagnostic_Fatal");
            ON_DIAG(Diag, cb_onNewError);
                break;
            default:
                LOGW("kk %d", ds);
                break;
        }
        clang_disposeDiagnostic(Diag);
    }

    clang_disposeTranslationUnit(TU);
    clang_disposeIndex(index);

    free(file_data);

    (*env)->ReleaseStringUTFChars(env, filePath, file_path);

}


__attribute__((hot)) static void
cxx_diag_from_source(JNIEnv *env, jobject self, jstring filePath, jstring source,
                     jobjectArray cxflags, jobject callback) {

    const char *file_path = (*env)->GetStringUTFChars(env, filePath, NULL);

    int n = (*env)->GetArrayLength(env, cxflags);

    char **cmds = (char **) malloc(sizeof(char *) * n);

    CXIndex index = clang_createIndex(0, 1);

#define INIT_CMD(IN, OUT, N)                                    \
    for(int i=0;i<N;i++){                                     \
        jstring cmd=(*env)->GetObjectArrayElement(env,IN,i);  \
        OUT[i]=(char *)(*env)->GetStringUTFChars(env,cmd,NULL);       \
    }

    INIT_CMD(cxflags, cmds, n);
#undef INIT_CMD

    struct CXUnsavedFile sourceFile;
    memset(&sourceFile, 0, sizeof(struct CXUnsavedFile));

    sourceFile.Filename = file_path;
    sourceFile.Contents = (*env)->GetStringUTFChars(env, source, NULL);
    sourceFile.Length = (*env)->GetStringUTFLength(env, source);

    const char *file_data = sourceFile.Contents;

    CXTranslationUnit TU;
    enum CXErrorCode EC =
            clang_parseTranslationUnit2(
                    index, file_path,
                    (const char *) cmds, n,
                    &sourceFile, 1,
                    clang_defaultEditingTranslationUnitOptions(), &TU);
    LOGW("EC %d", EC);
#define FREE_CMD(J, C, N)                                       \
    for(int i=0;i<N;i++){                                     \
        jstring cmd=(*env)->GetObjectArrayElement(env,J,i);   \
        (*env)->ReleaseStringUTFChars(env,cmd,C[i]);          \
    }

    FREE_CMD(cxflags, cmds, n)
#undef FREE_CMD

    unsigned NumDiags;
    CXDiagnostic Diag;
    CXString DiagStr;

    NumDiags = clang_getNumDiagnostics(TU);

    for (int i = 0; i != NumDiags; ++i) {
        Diag = clang_getDiagnostic(TU, i);
        switch (clang_getDiagnosticSeverity(Diag)) {
            case CXDiagnostic_Warning: ON_DIAG(Diag, cb_onNewWarning);
                break;
            case CXDiagnostic_Error: ON_DIAG(Diag, cb_onNewError);
                break;
            case CXDiagnostic_Fatal: ON_DIAG(Diag, cb_onNewError);
                break;
            default:
                break;
        }
        clang_disposeDiagnostic(Diag);
    }

    clang_disposeTranslationUnit(TU);
    clang_disposeIndex(index);

    (*env)->ReleaseStringUTFChars(env, source, sourceFile.Contents);
    (*env)->ReleaseStringUTFChars(env, filePath, file_path);

}

static int
register_cx_diag(JNIEnv *env) {
    static const char *const class_path = "aenu/eide/diagnostic/CXDiagnosticJni";
    static const JNINativeMethod methods[] = {
            {"diag_from_file",   "(Ljava/lang/String;[Ljava/lang/String;Laenu/eide/diagnostic/DiagnosticCallback;)V",                   (void *) cxx_diag_from_file},
            {"diag_from_source", "(Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Laenu/eide/diagnostic/DiagnosticCallback;)V", (void *) cxx_diag_from_source},
    };
    jclass clazz = (*env)->FindClass(env, class_path);

    return (*env)->RegisterNatives(env, clazz, methods, sizeof(methods) / sizeof(methods[0]));
}

__attribute__((visibility("default"))) jint
JNI_OnLoad(JavaVM *vm, void *reserved) {

    jint result = -1;
    JNIEnv *env = NULL;

    if ((*vm)->GetEnv(vm, NULL, JNI_VERSION_1_4) != JNI_OK) {
        LOGE("ERROR: GetEnv failed");
        goto bail;
    }

    if (register_cx_diag(env) != 0) {
        LOGE("ERROR: register_cx_diag");
        goto bail;
    }

    initialize_jni(env);

    result = JNI_VERSION_1_4;

    bail:
    return result;
}

__attribute__((visibility("default"))) void
JNI_OnUnload(JavaVM *vm, void *reserved) {
    JNIEnv *env = NULL;

    if ((*vm)->GetEnv(vm, NULL, JNI_VERSION_1_4) != JNI_OK) {
        return;
    }

    release_jni(env);
}
    

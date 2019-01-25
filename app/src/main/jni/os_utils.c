
#include <jni.h>
#include <unistd.h>
#include <sys/stat.h>

JNIEXPORT jint JNICALL Java_aenu_eide_util_OSUtils_fork(JNIEnv *env, jclass clazz) {
    return fork();
}

JNIEXPORT jint JNICALL Java_aenu_eide_util_OSUtils_setenv(JNIEnv *env, jclass clazz,jstring k,jstring v,jint r) {
    
    const char* key=(*env)->GetStringUTFChars(env,k,NULL);
    const char* value=(*env)->GetStringUTFChars(env,v,NULL);
 
    int R=setenv(key,value,r);
    
    (*env)->ReleaseStringUTFChars(env,k,key);
    (*env)->ReleaseStringUTFChars(env,v,value);
   
    return R;
}

JNIEXPORT jint JNICALL Java_aenu_eide_util_OSUtils_chmod(JNIEnv *env, jclass clazz,jstring path,jint mode) {
    const char* p=(*env)->GetStringUTFChars(env,path,NULL);
    
    int R=chmod(p,mode);
    
    (*env)->ReleaseStringUTFChars(env,path,p);
  
    return R;
}

JNIEXPORT jint JNICALL Java_aenu_eide_util_OSUtils_mkfifo(JNIEnv *env, jclass clazz,jstring path,jint mode) {
    const char* p=(*env)->GetStringUTFChars(env,path,NULL);
    
    int R=mkfifo(p,mode);
    
    (*env)->ReleaseStringUTFChars(env,path,p);
  
    return R;
}


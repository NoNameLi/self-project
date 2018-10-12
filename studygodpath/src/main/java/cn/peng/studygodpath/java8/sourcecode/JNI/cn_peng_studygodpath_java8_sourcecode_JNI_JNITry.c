#include "cn_peng_studygodpath_java8_sourcecode_JNI_JNITry.h"

JNIEXPORT jstring JNICALL Java_cn_peng_studygodpath_java8_sourcecode_JNI_JNITry_hello
  (JNIEnv *env, jclass jcls){
    printf("c to you Hello by JNI");
    const char *cc = "c to you Hello by JNI";
    jstring jstr = env->NewStringUTF(cc);
    return jstr;
  }


JNIEXPORT jint JNICALL Java_cn_peng_studygodpath_java8_sourcecode_JNI_JNITry_add
  (JNIEnv *env, jclass jcls, jintArray jarr){
    int result = 0;
  	jint *carr;
    carr = env->GetIntArrayElements(jarr, JNI_FALSE);
  	jsize len = env->GetArrayLength(jarr);
  	if(carr == NULL){
  		return 0;
  	}
  	for(int i = 0; i < len; i++){
  		result += carr[i];
  	}
  	env->ReleaseIntArrayElements(jarr,carr,0);
  	return result;
  }


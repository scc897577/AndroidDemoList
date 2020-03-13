#include <jni.h>
#include <string>
#define JNI_CLASS_PATH "com/kt/c2java/Student"

extern "C" JNIEXPORT jstring JNICALL
Java_com_kt_c2java_MainActivity_stringFromJNI(
        JNIEnv *env,
        jobject /* this */) {



    //第一步  under construction
    jclass clazz = env->FindClass(JNI_CLASS_PATH);

    //第二步
    jmethodID method_init_id = env->GetMethodID(clazz,"<init>","()V");
    jmethodID method_set_id = env->GetMethodID(clazz, "setYear", "(I)V");
    jmethodID method_get_id = env->GetMethodID(clazz, "getYear", "()I");

    //第三步
    jobject obj = env->NewObject(clazz, method_init_id);

    //第四步
    env->CallVoidMethod(obj, method_set_id, 28);
    int year = env->CallIntMethod(obj, method_get_id);
    char tmp [] = {0,};
    sprintf(tmp, "%d", year);


    std::string hello = "Hello from C++,year = ";
    hello.append(tmp);


    return env->NewStringUTF(hello.c_str());
}

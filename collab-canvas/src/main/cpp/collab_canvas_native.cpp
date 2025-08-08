#include <jni.h>

// Placeholder for collab_canvas_native.cpp
extern "C" JNIEXPORT jstring JNICALL
Java_com_genesis_collabcanvas_NativeLib_getVersion(JNIEnv *env, jobject /* this */) {
    return env->NewStringUTF("CollabCanvas Native 1.0");
}

#include <stdlib.h>
#include <jni.h>
#include <android/log.h>
#include <string>
#include "platform/android/jni/JniHelper.h"
#include "cocoa/CCString.h"
#include "Java_com_kyokomi_games_nativebasegameutils_GameNativeJniHelper.h"

#define  LOG_TAG    "Java_com_kyokomi_games_nativebasegameutils_GameNativeJniHelper.cpp"
#define  LOGD(...)  __android_log_print(ANDROID_LOG_DEBUG,LOG_TAG,__VA_ARGS__)

#define  CLASS_NAME "com/kyokomi/games/nativebasegameutils/GameNativeJniHelper"

using namespace cocos2d;
using namespace std;

void userSignInJNI() {
    JniMethodInfo t;
    if (JniHelper::getStaticMethodInfo(t, CLASS_NAME, "userSignIn", "()V")) {
    	t.env->CallStaticVoidMethod(t.classID, t.methodID);
    	t.env->DeleteLocalRef(t.classID);
    }
}

void userSignOutJNI() {
    JniMethodInfo t;
    if (JniHelper::getStaticMethodInfo(t, CLASS_NAME, "userSignOut", "()V")) {
    	t.env->CallStaticVoidMethod(t.classID, t.methodID);
    	t.env->DeleteLocalRef(t.classID);
    }
}

void sendScoreJNI(int nBoardResId, long lScore) {
    JniMethodInfo t;
    if (JniHelper::getStaticMethodInfo(t, CLASS_NAME, "sendScore", "(IL)V")) {
    	t.env->CallStaticVoidMethod(t.classID, t.methodID, nBoardResId, lScore);
    	t.env->DeleteLocalRef(t.classID);
    }
}

void showAllScoreBoardJNI() {
    JniMethodInfo t;
    if (JniHelper::getStaticMethodInfo(t, CLASS_NAME, "showAllScoreBoard", "()V")) {
    	t.env->CallStaticVoidMethod(t.classID, t.methodID);
    	t.env->DeleteLocalRef(t.classID);
    }
}

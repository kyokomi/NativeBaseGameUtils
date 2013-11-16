# Cocos2dx用のJNIの導入手順

## メモ

Cocos2dx-3.0alpha0でしか試してません。

## 手順

1. Cocos2dxのプロジェクトの「Classes」配下にこのファイルを同じフォルダにある以下のソースをコピーしてください。

- Java_com_kyokomi_games_nativebasegameutils_GameNativeJniHelper.cpp
- Java_com_kyokomi_games_nativebasegameutils_GameNativeJniHelper.h

2. 「proj.android/jni/Android.mk」のLOCAL_SRC_FILESに以下を追記してください。

- ../../Classes/Java_com_kyokomi_games_nativebasegameutils_GameNativeJniHelper.cpp \

3. 後は好きなSceneとかでincludeして呼び出したいメソッドを実行するだけです。
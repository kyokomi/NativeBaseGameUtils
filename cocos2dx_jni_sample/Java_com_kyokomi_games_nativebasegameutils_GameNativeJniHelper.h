
#ifndef __Java_com_kyokomi_games_nativebasegameutils_GameNativeJniHelper_H__
#define __Java_com_kyokomi_games_nativebasegameutils_GameNativeJniHelper_H__

#include <string>

extern void userSignInJNI();
extern void userSignOutJNI();
extern void sendScoreJNI(int nBoardResId, long lScore);
extern void showAllScoreBoardJNI();

#endif /* __Java_com_kyokomi_games_nativebasegameutils_GameNativeJniHelper_H__ */

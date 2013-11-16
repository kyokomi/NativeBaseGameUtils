package com.kyokomi.games.nativebasegameutils;

/**
 * NativeとGooglePlayGameServiceのやりとりを行うヘルパークラス。
 * @author kyokomi
 *
 */
public class GameNativeJniHelper {
	
	private static BaseGameNativeActivity sBaseActivity;

	/**
	 * イニシャライズ
	 * @param baseGameNativeActivity
	 */
	public static void init(final BaseGameNativeActivity baseGameNativeActivity) {
		sBaseActivity = baseGameNativeActivity;
	}
	
	private static void showAlertUnsignInError() {
		sBaseActivity.showAlert(sBaseActivity.getString(R.string.gamenativejnihelper_unsign_in_failed));		
	}
	// ==========================================================
	// Native Call Method
	// ==========================================================
	
	/**
	 * Google+サインイン
	 */
	public static void userSignIn() {
		sBaseActivity.runOnUiThread(new Runnable() {
			@Override
			public void run() {
				if (!sBaseActivity.isSignedIn()) {
					sBaseActivity.beginUserInitiatedSignIn();	
				}
			}
		});
	}
	
	/**
	 * Google+サインアウト
	 */
	public static void userSignOut() {
		sBaseActivity.runOnUiThread(new Runnable() {
			@Override
			public void run() {
				if (sBaseActivity.isSignedIn()) {
					sBaseActivity.signOut();
				}
			}
		});
	}
	
	/**
	 * リーダーボードへスコアを送信
	 * @param boardResId
	 * @param score
	 */
	public static void sendScore(final int boardResId, final long score) {
		sBaseActivity.runOnUiThread(new Runnable() {
			@Override
			public void run() {
				if (sBaseActivity.isSignedIn()) {
					sBaseActivity.submitBoardScore(boardResId, score);
				} else {
					sBaseActivity.showAlert(sBaseActivity.getString(
							R.string.gamenativejnihelper_unsign_in_failed));
				}
			}
		});
	}
	
	/**
	 * 全てのスコアボードを開く
	 */
	public static void showAllScoreBoard() {
		sBaseActivity.runOnUiThread(new Runnable() {
			@Override
			public void run() {
				if (sBaseActivity.isSignedIn()) {
					sBaseActivity.showAllLeaderBoard();
				} else {
					showAlertUnsignInError();
				}
			}
		});
	}
}

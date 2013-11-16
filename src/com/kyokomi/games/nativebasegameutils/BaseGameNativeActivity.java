package com.kyokomi.games.nativebasegameutils;


import com.google.android.gms.appstate.AppStateClient;
import com.google.android.gms.games.GamesClient;
import com.google.android.gms.plus.PlusClient;

import android.app.NativeActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

/**
 * BaseGameActivityのNativeActivity版。
 * @author kyokomi
 *
 */
public abstract class BaseGameNativeActivity extends NativeActivity  implements GameHelper.GameHelperListener {

	protected GameHelper mHelper;

	/** ゲーム全般の機能 */
	public static final int CLIENT_GAMES = GameHelper.CLIENT_GAMES;
	/** アプリの“状態”管理機能 */
	public static final int CLIENT_APPSTATE = GameHelper.CLIENT_APPSTATE;
	/** Google+のソーシャル機能 */
	public static final int CLIENT_PLUS = GameHelper.CLIENT_PLUS;
	/** 全て */
	public static final int CLIENT_ALL = GameHelper.CLIENT_ALL;

	/** デフォルトはゲーム全般 */
	protected int mRequestedClients = CLIENT_GAMES;

	private String[] mAdditionalScopes;

	protected String mDebugTag = BaseGameNativeActivity.class.getSimpleName();
	protected boolean mDebugLog = false;

	/** 全リーダーボード表示後のresultCode */
	public static int SHOW_LEADERBOARD_RESULT_CODE = 1111;

	protected BaseGameNativeActivity() {
		super();
		mHelper = new GameHelper(this);
	}

	protected BaseGameNativeActivity(int requestedClients) {
		super();
		setRequestedClients(requestedClients);
	}

	protected void setRequestedClients(int requestedClients, String ... additionalScopes) {
		mRequestedClients = requestedClients;
		mAdditionalScopes = additionalScopes;
	}

	@Override
	protected void onCreate(Bundle b) {
		super.onCreate(b);
		mHelper = new GameHelper(this);
		if (mDebugLog) {
			mHelper.enableDebugLog(mDebugLog, mDebugTag);
		}
		mHelper.setup(this, mRequestedClients, mAdditionalScopes);
		
		GameNativeJniHelper.init(this);
	}

	@Override
	protected void onStart() {
		super.onStart();
		if (mHelper != null) {
			mHelper.onStart(this);
		}
	}

	@Override
	protected void onStop() {
		super.onStop();
		if (mHelper != null) {
			mHelper.onStop();
		}
	}

	@Override
	protected void onActivityResult(int request, int response, Intent data) {
		super.onActivityResult(request, response, data);
		if (mHelper != null) {
			mHelper.onActivityResult(request, response, data);
		}
	}

    /**
     * Called to notify us that sign in failed. Notice that a failure in sign in is not
     * necessarily due to an error; it might be that the user never signed in, so our
     * attempt to automatically sign in fails because the user has not gone through
     * the authorization flow. So our reaction to sign in failure is to show the sign in
     * button. When the user clicks that button, the sign in process will start/resume.
     */
    @Override
    public void onSignInFailed() {
    	// エラー後に呼ばれるのでエラーメッセージとかは不要
    	// リトライ方法を提示してあげるべき
    }

    /**
     * Called to notify us that sign in succeeded. We react by loading the loot from the
     * cloud and updating the UI to show a sign-out button.
     */
    @Override
    public void onSignInSucceeded() {
    	Toast.makeText(this, 
    			getString(R.string.basegamenativeactivity_signin_successed), 
    			Toast.LENGTH_LONG).show();
    }
    
	protected GamesClient getGamesClient() {
		return mHelper.getGamesClient();
	}

	protected AppStateClient getAppStateClient() {
		return mHelper.getAppStateClient();
	}

	protected PlusClient getPlusClient() {
		return mHelper.getPlusClient();
	}

	protected boolean isSignedIn() {
		return mHelper.isSignedIn();
	}

	protected void beginUserInitiatedSignIn() {
		mHelper.beginUserInitiatedSignIn();
	}

	protected void signOut() {
		mHelper.signOut();
	}

	protected void showAlert(String title, String message) {
		mHelper.showAlert(title, message);
	}

	protected void showAlert(String message) {
		mHelper.showAlert(message);
	}

	protected void enableDebugLog(boolean enabled, String tag) {
		mDebugLog = true;
		mDebugTag = tag;
		if (mHelper != null) {
			mHelper.enableDebugLog(enabled, tag);
		}
	}

	protected String getInvitationId() {
		return mHelper.getInvitationId();
	}

	protected void reconnectClients(int whichClients) {
		mHelper.reconnectClients(whichClients);
	}

	protected String getScopes() {
		return mHelper.getScopes();
	}

	protected String[] getScopesArray() {
		return mHelper.getScopesArray();
	}

	protected boolean hasSignInError() {
		return mHelper.hasSignInError();
	}

	protected GameHelper.SignInFailureReason getSignInError() {
		return mHelper.getSignInError();
	}
	
	protected void submitBoardScore(int boardResId, long score) {
		getGamesClient().submitScore(getString(boardResId), score);
	}
	
	protected void showAllLeaderBoard() {
		startActivityForResult(getGamesClient().getAllLeaderboardsIntent(), SHOW_LEADERBOARD_RESULT_CODE);
	}
}

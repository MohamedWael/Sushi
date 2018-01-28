package com.blogspot.mowael.sushi.facebook;

import android.app.Activity;
import android.content.Intent;
import android.support.v4.app.Fragment;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;

/**
 * Created by mohamed on 1/28/18.
 */

public class LoginWithCustomButton {
    private CallbackManager mCallbackManager;

    public LoginWithCustomButton() {
        mCallbackManager = FacebookConfig.getInstance().getDefaultCallBackManager();
    }

    public void logInWithReadPermissions(Activity context, FBPermissions... fbPermissions) {
        LoginManager.getInstance().logInWithReadPermissions(context, FacebookConfig.permissionsToStringList(fbPermissions));
    }

    public void logInWithReadPermissions(Fragment context, FBPermissions... fbPermissions) {
        LoginManager.getInstance().logInWithReadPermissions(context, FacebookConfig.permissionsToStringList(fbPermissions));
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        mCallbackManager.onActivityResult(requestCode, resultCode, data);
    }

    public void setLoginCallback(FacebookCallback<LoginResult> loginCallback) {
        LoginManager.getInstance().registerCallback(mCallbackManager, loginCallback);
    }

    /**
     * override onActivityResult on your activity or fragment and call LoginWithCustomButton onActivityResult
     *
     * @param activity      context
     * @param fbPermissions facebook read permissions
     * @param callback      response callback
     */
    public void login(Activity activity, FBPermissions fbPermissions, FacebookCallback<LoginResult> callback) {
        logInWithReadPermissions(activity, fbPermissions);
        setLoginCallback(callback);
    }

    /**
     * override onActivityResult on your activity or fragment and call LoginWithCustomButton onActivityResult
     *
     * @param fragment      context
     * @param fbPermissions facebook read permissions
     * @param callback      response callback
     */
    public void login(Fragment fragment, FBPermissions fbPermissions, FacebookCallback<LoginResult> callback) {
        logInWithReadPermissions(fragment, fbPermissions);
        setLoginCallback(callback);
    }

    public LoginManager getLoginManager() {
        return LoginManager.getInstance();
    }

}

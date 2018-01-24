package com.blogspot.mowael.sushi.facebook;

import android.content.Intent;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

import java.util.Arrays;

/**
 * Created by mohamed on 1/24/18.
 */

public class LoginWithFBButton {

    private CallbackManager mCallbackManager;
    private LoginButton mLoginButton;

    /**
     * you have to set read permissions
     *
     * @param loginButton the default facebook login button
     */
    public LoginWithFBButton(LoginButton loginButton) {
        this.mLoginButton = loginButton;
        mCallbackManager = CallbackManager.Factory.create();
    }

    /**
     * @param loginButton   the default facebook login button
     * @param fbPermissions facebook read permissions
     */
    public LoginWithFBButton(LoginButton loginButton, FBPermissions... fbPermissions) {
        this(loginButton);
        setReadPermissions(fbPermissions);
    }

    /**
     * Set the initial permissions to request from the user while logging in
     *
     * @param permissions facebook read permissions
     */
    public void setReadPermissions(FBPermissions... permissions) {
        String[] permissionsString = new String[permissions.length];
        for (int i = 0; i < permissions.length; i++) {
            permissionsString[i] = permissions[i].getPermission();
        }
        setReadPermissions(permissionsString);
    }


    /**
     * Set the initial permissions to request from the user while logging in
     *
     * @param permissions facebook read permissions
     */
    public void setReadPermissions(String... permissions) {
        mLoginButton.setReadPermissions(Arrays.asList(permissions));
    }

    /**
     * // Register a callback to respond to the user
     *
     * @param callback FacebookCallback of type LoginResult
     */
    public void registerCallback(FacebookCallback<LoginResult> callback) {
        mLoginButton.registerCallback(mCallbackManager, callback);
    }


    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        mCallbackManager.onActivityResult(requestCode, resultCode, data);
    }

    public LoginButton getLoginButton() {
        return mLoginButton;
    }

    public CallbackManager getCallbackManager() {
        return mCallbackManager;
    }
}

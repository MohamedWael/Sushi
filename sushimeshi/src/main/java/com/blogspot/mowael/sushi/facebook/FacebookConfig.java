package com.blogspot.mowael.sushi.facebook;

import android.app.Application;

import com.facebook.CallbackManager;
import com.facebook.appevents.AppEventsLogger;

import java.util.Arrays;
import java.util.List;

/**
 * Created by mohamed on 1/24/18.
 */

public class FacebookConfig {
    private static FacebookConfig instance;

    //this singleton should be synchronized but i didn't as facebook must have handled it
    public static FacebookConfig getInstance() {
        if (instance == null) {
            instance = new FacebookConfig();
        }
        return instance;
    }

    private FacebookConfig() {

    }

    public void enableAppEventsLogger(Application context) {
        AppEventsLogger.activateApp(context);
    }

    public CallbackManager getDefaultCallBackManager() {
        return CallbackManager.Factory.create();
    }

    public static String[] permissionsToStringArray(FBPermissions... permissions) {
        String[] permissionsString = new String[permissions.length];
        for (int i = 0; i < permissions.length; i++) {
            permissionsString[i] = permissions[i].getPermission();
        }
        return permissionsString;
    }

    public static List<String> permissionsToStringList(FBPermissions... permissions) {
        return Arrays.asList(permissionsToStringArray(permissions));
    }
}

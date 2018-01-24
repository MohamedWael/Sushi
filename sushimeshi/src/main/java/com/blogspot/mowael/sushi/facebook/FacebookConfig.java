package com.blogspot.mowael.sushi.facebook;

import android.app.Application;

import com.facebook.appevents.AppEventsLogger;

/**
 * Created by mohamed on 1/24/18.
 */

public class FacebookConfig {
    private FacebookConfig instance;

    //this singleton should be synchronized but i didn't as facebook must have handled it
    public FacebookConfig getInstance() {
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
}

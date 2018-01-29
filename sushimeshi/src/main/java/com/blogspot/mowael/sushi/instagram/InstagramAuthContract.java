package com.blogspot.mowael.sushi.instagram;

import android.webkit.WebViewClient;

/**
 * Created by mohamed on 1/29/18.
 */

public interface InstagramAuthContract {
    interface InstagramAuthView {
        void loadUri(String url);

        void dismissDialog();

        void refreshView();

        void showProgress();

        void stopProgress();

        void setTitle(String title);

        void showError(String description);


    }

    interface InstagramAuthPresenter {
        void setAuthenticationData(String clientID, String redirectUri);

        void loadUri(String clientID, String redirectUri);

        void loadUri();

        void setAuthListener(InstagramAuthListener listener);

        WebViewClient getClient();

        void onDestroy();
    }
}

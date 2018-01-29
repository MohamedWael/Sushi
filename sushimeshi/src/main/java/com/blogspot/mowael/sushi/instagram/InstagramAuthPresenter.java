package com.blogspot.mowael.sushi.instagram;

import android.graphics.Bitmap;
import android.text.TextUtils;
import android.util.Log;
import android.webkit.WebView;
import android.webkit.WebViewClient;

/**
 * Created by mohamed on 1/29/18.
 */

public class InstagramAuthPresenter implements InstagramAuthContract.InstagramAuthPresenter {

    private String TAG = InstagramAuthPresenter.class.getSimpleName();
    private InstagramAuthContract.InstagramAuthView view;
    private String clientID;
    private String redirectUri;
    private InstagramAuthListener listener;

    public InstagramAuthPresenter(InstagramAuthContract.InstagramAuthView view) {
        this.view = view;
    }

    public InstagramAuthContract.InstagramAuthView getView() {
        return view;
    }

    @Override
    public void setAuthenticationData(String clientID, String redirectUri) {
        this.clientID = clientID;
        this.redirectUri = redirectUri;
    }

    @Override
    public void loadUri(String clientID, String redirectUri) {
        setAuthenticationData(clientID, redirectUri);
        loadUri();
    }

    @Override
    public void loadUri() {
        if (TextUtils.isEmpty(clientID)) {
            throw new IllegalStateException("you have to set instagram client ID");
        }
        view.loadUri(InstagramConfig.createAuthUrl(clientID, redirectUri));
        getView().showProgress();
    }

    @Override
    public void setAuthListener(InstagramAuthListener listener) {
        this.listener = listener;
    }

    @Override
    public WebViewClient getClient() {
        return new WebViewClient() {

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {

                Log.d("RedirectingURL", url);
                if (url.startsWith(redirectUri)) {
                    String urls[] = url.split("=");
                    if (listener != null) listener.onSuccess(url, urls[1]);
                    getView().dismissDialog();
                    return true;
                }
                return false;
            }

            @Override
            public void onReceivedError(WebView view, int errorCode, String description,
                                        String failingUrl) {
                Log.d(TAG, "Page error: " + description);
                super.onReceivedError(view, errorCode, description, failingUrl);
                if (listener != null) listener.onError(description);
                getView().showError(description);
                getView().stopProgress();
            }

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                Log.d(TAG, "Loading URL: " + url);
                super.onPageStarted(view, url, favicon);
                getView().showProgress();
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                String title = view.getTitle();
                if (!TextUtils.isEmpty(title))
                    getView().setTitle(title);
                Log.d(TAG, "onPageFinished URL: " + url);
                getView().refreshView();
                getView().stopProgress();
            }

        };
    }

    @Override
    public void onDestroy() {
        listener = null;
    }


}

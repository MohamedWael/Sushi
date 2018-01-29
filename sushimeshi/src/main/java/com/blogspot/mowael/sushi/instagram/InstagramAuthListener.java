package com.blogspot.mowael.sushi.instagram;

/**
 * Created by mohamed on 1/29/18.
 */

public interface InstagramAuthListener {
    void onSuccess(String redirectUri, String code);

    void onError(String description);
}

package com.blogspot.mowael.sushi.instagram;

import android.util.Log;

/**
 * Created by mohamed on 1/29/18.
 */

public class InstagramConfig {
    public static String baseUrl = "https://api.instagram.com/";
    public static String authUrl = baseUrl + "oauth/authorize/";

    private static String CLIENT_ID = "client_id";
    private static String REDIRECT_URI = "redirect_uri";
    private static String RESPONSE_TYPE = "response_type";
    private static String CODE = "code";
    private static String TOKEN = "token";

    public static String createAuthUrl(String clientID, String redirectUri) {
        String QUERY = "?";
        String AND = "&";
        String EQUAL = "=";
        String uri = authUrl + QUERY + CLIENT_ID + EQUAL + clientID + AND + REDIRECT_URI + EQUAL + redirectUri + AND + RESPONSE_TYPE + EQUAL + CODE;
        Log.e("InstagramAuth", uri);
        return uri;
    }

    public static String createAuthUrl(String clientID) {
        String QUERY = "?";
        String AND = "&";
        String EQUAL = "=";
        String uri = authUrl + QUERY + CLIENT_ID + EQUAL + clientID + AND + RESPONSE_TYPE + EQUAL + CODE;
        Log.e("InstagramAuth", uri);
        return uri;
    }


}

package com.blogspot.mowael.sushi.facebook;

/**
 * Created by mohamed on 1/24/18.
 */

public enum FBPermissions {
    EMAIL("email"), USER_POSTS("user_posts"), PUBLISH_ACTIONS("publish_actions"), PUBLIC_PROFILE("public_profile"), APP("app");

    private String permission;

    FBPermissions(String permission) {
        this.permission = permission;
    }

    public String getPermission() {
        return permission;
    }
}

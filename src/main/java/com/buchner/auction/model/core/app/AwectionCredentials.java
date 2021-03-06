package com.buchner.auction.model.core.app;

/**
 * Represents credentials as entered by a user when logging in.
 */
public class AwectionCredentials {

    private String userName;
    private String password;

    public AwectionCredentials(String userName, String password) {

        this.userName = userName;
        this.password = password;
    }

    public String getUserName() {

        return userName;
    }

    public String getPassword() {

        return password;
    }
}

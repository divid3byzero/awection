package com.buchner.awection.model.user;

import com.buchner.awection.model.core.AwectionCredentials;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

@Named
@RequestScoped
public class LoginView {

    private String userName;
    private String password;

    public LoginView() {

    }

    public String getUserName() {

        return userName;
    }

    public void setUserName(String userName) {

        this.userName = userName;
    }

    public String getPassword() {

        return password;
    }

    public void setPassword(String password) {

        this.password = password;
    }

    public AwectionCredentials getAwectionCredentials() {

        return new AwectionCredentials(userName, password);
    }
}

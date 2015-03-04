package com.buchner.awection.model.user;

import com.buchner.awection.model.core.app.AuctionUser;

import javax.enterprise.context.RequestScoped;

@RequestScoped
public class UserRegisterView {


    private String firstName;
    private String surName;
    private String mail;
    private String password;

    public UserRegisterView() {

    }

    public String getFirstName() {

        return firstName;
    }

    public void setFirstName(String firstName) {

        this.firstName = firstName;
    }

    public String getSurName() {

        return surName;
    }

    public void setSurName(String surName) {

        this.surName = surName;
    }

    public String getMail() {

        return mail;
    }

    public void setMail(String mail) {

        this.mail = mail;
    }

    public String getPassword() {

        return password;
    }

    public void setPassword(String password) {

        this.password = password;
    }

    public AuctionUser createAuctionUser() {

        return new AuctionUser(firstName, surName, mail, password);
    }
}

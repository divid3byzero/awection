package com.buchner.awection.model.core.app;

public class AuctionUser {

    private String firstName;
    private String surname;
    private String mailAddress;
    private String password;

    public AuctionUser(String firstName, String surname, String mailAddress,
        String password) {

        this.firstName = firstName;
        this.surname = surname;
        this.mailAddress = mailAddress;
        this.password = password;
    }

    public String getFirstName() {

        return firstName;
    }

    public String getSurname() {

        return surname;
    }

    public String getMailAddress() {

        return mailAddress;
    }

    public String getPassword() {

        return password;
    }
}

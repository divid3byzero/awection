package com.buchner.auction.model.core.bean;

import java.math.BigDecimal;

public class AuctionResultBean {

    private String description;
    private BigDecimal price;
    private String firstName;
    private String surname;
    private String mail;

    public AuctionResultBean(String description, BigDecimal price, String firstName,
        String surname, String mail) {

        this.description = description;
        this.price = price;
        this.firstName = firstName;
        this.surname = surname;
        this.mail = mail;
    }

    public String getDescription() {

        return description;
    }

    public BigDecimal getPrice() {

        return price;
    }

    public String getFirstName() {

        return firstName;
    }

    public String getSurname() {

        return surname;
    }

    public String getMail() {

        return mail;
    }
}

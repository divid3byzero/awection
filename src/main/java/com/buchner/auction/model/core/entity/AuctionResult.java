package com.buchner.auction.model.core.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;
import java.math.BigDecimal;

@Entity
@Table(name = "auction_results")
public class AuctionResult {

    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private BigDecimal price;
    private String firstName;
    private String surname;
    private String mail;

    public AuctionResult() {

    }

    public int getId() {

        return id;
    }

    public BigDecimal getPrice() {

        return price;
    }

    public void setPrice(BigDecimal price) {

        this.price = price;
    }

    public String getFirstName() {

        return firstName;
    }

    public void setFirstName(String firstName) {

        this.firstName = firstName;
    }

    public String getSurname() {

        return surname;
    }

    public void setSurname(String surname) {

        this.surname = surname;
    }

    public String getMail() {

        return mail;
    }

    public void setMail(String mail) {

        this.mail = mail;
    }
}

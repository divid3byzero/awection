package com.buchner.auction.model.core.entity;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "auction_results")
public class AuctionResult {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Enumerated(EnumType.STRING)
    private AuctionType auctionType;

    private BigDecimal price;
    private String firstName;
    private String surname;
    private String mail;
    private String description;

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

    public AuctionType getAuctionType() {

        return auctionType;
    }

    public void setAuctionType(AuctionType auctionType) {

        this.auctionType = auctionType;
    }

    public String getDescription() {

        return description;
    }

    public void setDescription(String description) {

        this.description = description;
    }
}
package com.buchner.auction.model.core.bean;

import com.buchner.auction.model.core.entity.AuctionResult;
import com.buchner.auction.model.core.entity.AuctionType;
import com.buchner.auction.model.core.entity.Bid;
import com.buchner.auction.model.core.entity.Bidder;

import java.math.BigDecimal;

public class TradeResultBean {

    private String description;
    private BigDecimal price;
    private String firstName;
    private String surname;
    private String mail;
    private AuctionType auctionType;

    private boolean auctionRunning;
    private Bidder bidder;
    private Bid bid;

    public TradeResultBean() {

    }

    public String getDescription() {

        return description;
    }

    public void setDescription(String description) {

        this.description = description;
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

    public AuctionResult buildAuctionResult() {

        AuctionResult auctionResult = new AuctionResult();
        auctionResult.setPrice(price);
        auctionResult.setFirstName(firstName);
        auctionResult.setSurname(surname);
        auctionResult.setMail(mail);
        auctionResult.setAuctionType(auctionType);
        auctionResult.setDescription(description);
        return auctionResult;
    }

    public boolean isAuctionRunning() {

        return auctionRunning;
    }

    public void setAuctionRunning(boolean auctionRunning) {

        this.auctionRunning = auctionRunning;
    }

    public Bidder getBidder() {

        return bidder;
    }

    public void setBidder(Bidder bidder) {

        this.bidder = bidder;
    }

    public Bid getBid() {

        return bid;
    }

    public void setBid(Bid bid) {

        this.bid = bid;
    }
}

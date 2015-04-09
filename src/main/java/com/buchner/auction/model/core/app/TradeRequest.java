package com.buchner.auction.model.core.app;

import com.buchner.auction.model.core.entity.Auction;

import java.math.BigDecimal;

/**
 * Representation of a bid on an auction by a user.
 * Holds all needed information to process a bid.
 */
public class TradeRequest {

    private Auction auction;
    private BigDecimal amount;
    private long userId;

    public TradeRequest() {

    }

    public Auction getAuction() {

        return auction;
    }

    public void setAuction(Auction auction) {

        this.auction = auction;
    }

    public BigDecimal getAmount() {

        return amount;
    }

    public void setAmount(BigDecimal amount) {

        this.amount = amount;
    }

    public long getUserId() {

        return userId;
    }

    public void setUserId(long userId) {

        this.userId = userId;
    }
}

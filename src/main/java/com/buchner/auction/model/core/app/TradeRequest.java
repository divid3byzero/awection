package com.buchner.auction.model.core.app;

import com.buchner.auction.model.core.entity.Auction;
import com.buchner.auction.model.core.entity.CdaType;

import java.math.BigDecimal;

public class TradeRequest {

    private Auction auction;
    private BigDecimal amount;
    private long userId;
    private CdaType cdaType;

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

    public CdaType getCdaType() {

        return cdaType;
    }

    public void setCdaType(CdaType cdaType) {

        this.cdaType = cdaType;
    }
}

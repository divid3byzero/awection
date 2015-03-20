package com.buchner.auction.model.core.bean;

import java.math.BigDecimal;

public class BidBean {

    private int bidderId;
    private int auctionId;
    private BigDecimal bidAmount;

    public BidBean(int bidderId, int auctionId, BigDecimal bidAmount) {

        this.bidderId = bidderId;
        this.auctionId = auctionId;
        this.bidAmount = bidAmount;
    }

    public int getBidderId() {

        return bidderId;
    }

    public int getAuctionId() {

        return auctionId;
    }

    public BigDecimal getBidAmount() {

        return bidAmount;
    }
}

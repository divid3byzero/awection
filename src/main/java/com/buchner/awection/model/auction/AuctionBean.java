package com.buchner.awection.model.auction;

import com.buchner.awection.model.core.entity.AuctionType;

import java.math.BigDecimal;

public class AuctionBean {

    private AuctionType auctionType;
    private String articleDescription;
    private BigDecimal price;
    private boolean isRunning;

    public AuctionBean(AuctionType auctionType, String articleDescription, BigDecimal price,
        boolean isRunning) {

        this.auctionType = auctionType;
        this.articleDescription = articleDescription;
        this.price = price;
        this.isRunning = isRunning;
    }

    public AuctionType getAuctionType() {

        return auctionType;
    }

    public String getArticleDescription() {

        return articleDescription;
    }

    public BigDecimal getPrice() {

        return price;
    }

    public boolean isRunning() {

        return isRunning;
    }
}

package com.buchner.auction.model.core.bean;

import com.buchner.auction.model.core.entity.AuctionType;

import java.math.BigDecimal;

/**
 * Model class representing an auction.
 */
public class AuctionBean {

    private int id;
    private int articleId;
    private AuctionType auctionType;
    private String articleDescription;
    private BigDecimal price;
    private boolean isRunning;

    public AuctionBean(int id, int articleId,
        AuctionType auctionType, String articleDescription, BigDecimal price, boolean isRunning) {

        this.id = id;
        this.articleId = articleId;
        this.auctionType = auctionType;
        this.articleDescription = articleDescription;
        this.price = price;
        this.isRunning = isRunning;
    }

    public int getId() {

        return id;
    }

    public int getArticleId() {

        return articleId;
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

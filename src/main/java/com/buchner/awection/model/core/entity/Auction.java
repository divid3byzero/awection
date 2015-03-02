package com.buchner.awection.model.core.entity;

import com.buchner.awection.model.core.AuctionType;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "auctions")
public class Auction {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @JoinColumn(name = "id", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private AuctionUser auctionUser;

    @Enumerated(EnumType.STRING)
    private AuctionType auctionType;

    private BigDecimal endPrice;
    private boolean isRunning;
    private int articleId;

    public Auction() {

    }

    public int getId() {

        return id;
    }

    public AuctionUser getAuctionUser() {

        return auctionUser;
    }

    public void setAuctionUser(AuctionUser auctionUser) {

        this.auctionUser = auctionUser;
    }

    public AuctionType getAuctionType() {

        return auctionType;
    }

    public void setAuctionType(AuctionType auctionType) {

        this.auctionType = auctionType;
    }

    public BigDecimal getEndPrice() {

        return endPrice;
    }

    public void setEndPrice(BigDecimal endPrice) {

        this.endPrice = endPrice;
    }

    public boolean isRunning() {

        return isRunning;
    }

    public void setRunning(boolean isRunning) {

        this.isRunning = isRunning;
    }

    public int getArticleId() {

        return articleId;
    }

    public void setArticleId(int articleId) {

        this.articleId = articleId;
    }
}

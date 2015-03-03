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

    @Enumerated(EnumType.STRING)
    private AuctionType auctionType;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fkArticle", referencedColumnName = "id")
    private Article article;

    private long userId;
    private BigDecimal price;
    private boolean isRunning;

    public Auction() {

    }

    public int getId() {

        return id;
    }

    public AuctionType getAuctionType() {

        return auctionType;
    }

    public void setAuctionType(AuctionType auctionType) {

        this.auctionType = auctionType;
    }

    public Article getArticle() {

        return article;
    }

    public void setArticle(Article article) {

        this.article = article;
    }

    public long getUserId() {

        return userId;
    }

    public void setUserId(long userId) {

        this.userId = userId;
    }

    public BigDecimal getPrice() {

        return price;
    }

    public void setPrice(BigDecimal price) {

        this.price = price;
    }

    public boolean isRunning() {

        return isRunning;
    }

    public void setRunning(boolean isRunning) {

        this.isRunning = isRunning;
    }

    private AuctionType getAuctionType(String identifier) {

        for (AuctionType auctionType : AuctionType.values()) {
            if (identifier.equals(auctionType.getIdentifier())) {
                return auctionType;
            }
        }
        return null;
    }
}

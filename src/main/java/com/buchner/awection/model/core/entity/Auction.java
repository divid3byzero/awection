package com.buchner.awection.model.core.entity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "auctions")
public class Auction {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Enumerated(EnumType.STRING)
    private AuctionType auctionType;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_article", referencedColumnName = "id")
    private Article article;

    @OneToMany(mappedBy = "auction", fetch = FetchType.LAZY)
    private List<Bidder> bidder;

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

    public void setId(int id) {

        this.id = id;
    }

    public List<Bidder> getBidder() {

        return bidder;
    }

    public void setBidder(Bidder bidder) {

        this.bidder.add(bidder);
        if (!this.equals(bidder.getAuction())) {
            bidder.setAuction(this);
        }
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

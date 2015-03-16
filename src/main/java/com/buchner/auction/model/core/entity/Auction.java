package com.buchner.auction.model.core.entity;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "auctions")
@NamedQueries({
    @NamedQuery(name = "Auction.fromBidderAndType", query = "select au from Auction au inner join au.bidder aub where au.auctionType = :auctionType and au.isRunning = 1 and aub.userId = :userId"),
    @NamedQuery(name = "Auction.findByArticle", query = "select au from Auction au where au.article.id = :articleId")
})
public class Auction {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Enumerated(EnumType.STRING)
    private AuctionType auctionType;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_article", referencedColumnName = "id")
    private Article article;

    @ManyToMany(mappedBy = "auctions", fetch = FetchType.LAZY)
    private List<Bidder> bidder;

    private long userId;
    private BigDecimal price;
    private boolean isRunning;

    @Temporal(TemporalType.TIMESTAMP)
    private Date startTimestamp;

    @Temporal(TemporalType.TIMESTAMP)
    private Date endTime;

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

    public void addBidder(Bidder bidder) {

        this.bidder.add(bidder);
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

    public Date getStartTimestamp() {

        return startTimestamp;
    }

    public Date getEndTime() {

        return endTime;
    }

    public void setEndTime(Date endTime) {

        this.endTime = endTime;
    }

    private AuctionType getAuctionType(String identifier) {

        for (AuctionType auctionType : AuctionType.values()) {
            if (identifier.equals(auctionType.getIdentifier())) {
                return auctionType;
            }
        }
        return null;
    }

    @PrePersist
    private void prePersist() {

        DateTime dateTime = new DateTime(DateTimeZone.forID("Europe/Berlin"));
        startTimestamp = dateTime.toDate();
    }
}

package com.buchner.auction.model.core.entity;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;

import javax.persistence.*;
import java.math.BigDecimal;
import java.text.ParseException;
import java.util.Date;

/**
 * Bid entity with definition of named queries for
 * better performance.
 */
@Entity
@Table(name = "bids")
@NamedQueries({
        @NamedQuery(name = "Bid.getByUserId", query = "select b from Bid b join b.bidder bb where bb.userId = :userId"),
        @NamedQuery(name = "Bid.getByAuction", query = "select b from Bid b join b.bidder bb join bb.auctions bba where bb.userId = :userId and bba.id = :auctionId")
})
public class Bid {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "bidder_fk", referencedColumnName = "id")
    private Bidder bidder;

    private int auctionId;

    @Column(name = "bid_timestamp", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date timestamp;

    private BigDecimal amount;

    public int getId() {

        return id;
    }

    public Bidder getBidder() {

        return bidder;
    }

    public void setBidder(Bidder bidder) {

        this.bidder = bidder;
    }

    public int getAuctionId() {

        return auctionId;
    }

    public void setAuctionId(int auctionId) {

        this.auctionId = auctionId;
    }

    public Date getTimeStamp() {

        return timestamp;
    }

    public BigDecimal getAmount() {

        return amount;
    }

    public void setAmount(BigDecimal amount) {

        this.amount = amount;
    }

    @PrePersist
    public void prePersist() throws ParseException {

        DateTime now = new DateTime(DateTimeZone.forID("Europe/Berlin"));
        timestamp = now.toDate();
    }
}

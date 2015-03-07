package com.buchner.awection.model.core.entity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

@Entity
@Table(name = "bids")
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

        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-ddHH:mm:ss");
        TimeZone timeZone = TimeZone.getTimeZone("Europe/Berlin");
        simpleDateFormat.setTimeZone(timeZone);
        String format = simpleDateFormat.format(date);
        timestamp = simpleDateFormat.parse(format);
    }
}

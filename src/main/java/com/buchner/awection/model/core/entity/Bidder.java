package com.buchner.awection.model.core.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "bidder")
public class Bidder {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "auction_fk", referencedColumnName = "id")
    private Auction auction;

    @OneToMany(mappedBy = "bidder", fetch = FetchType.LAZY)
    private List<Bid> bids;

    private long userId;

    public Bidder() {

    }

    public int getId() {

        return id;
    }

    public Auction getAuction() {

        return auction;
    }

    public void setAuction(Auction auction) {

        this.auction = auction;
    }

    public List<Bid> getBids() {

        return bids;
    }

    public void setBids(Bid bid) {

        bids.add(bid);
        if (!this.equals(bid.getBidder())) {
            bid.setBidder(this);
        }
    }

    public long getUserId() {

        return userId;
    }

    public void setUserId(long userId) {

        this.userId = userId;
    }
}

package com.buchner.auction.model.core.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "bidder")
public class Bidder {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "auction_bidder", joinColumns = {
        @JoinColumn(name = "auction_id", referencedColumnName = "id")},
        inverseJoinColumns = {@JoinColumn(name = "bidder_id", referencedColumnName = "id")})
    private List<Auction> auctions;

    @OneToMany(mappedBy = "bidder", fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    private List<Bid> bids;

    private long userId;

    public Bidder() {

        auctions = new ArrayList<>();
    }

    public int getId() {

        return id;
    }

    public List<Auction> getAuctions() {

        return auctions;
    }

    public void addAuction(Auction auction) {

        auctions.add(auction);
        if (!auction.getBidder().contains(this)) {
            auction.addBidder(this);
        }
    }

    public List<Bid> getBids() {

        return bids;
    }

    public void addBid(Bid bid) {

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

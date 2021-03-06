package com.buchner.auction.model.core.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Bidder entity with definition of named queries for
 * better performance.
 */
@Entity
@Table(name = "bidder")
@NamedQueries({
        @NamedQuery(name = "Bidder.findBidByBidder", query = "select b from Bidder b join b.bids bb where b.userId = :userId"),
})
public class Bidder {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "auction_bidder", joinColumns = {
            @JoinColumn(name = "bidder_id")},
            inverseJoinColumns = {@JoinColumn(name = "auction_id")})
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

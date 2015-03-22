package com.buchner.auction.model.core.app;

import com.buchner.auction.model.core.entity.Bid;

import java.util.Comparator;

/**
 * Comparator to sort lists of bids ascending according to their value.
 */
public class BidComperator implements Comparator<Bid> {

    @Override public int compare(Bid o1, Bid o2) {

        return o2.getAmount().compareTo(o1.getAmount());
    }
}

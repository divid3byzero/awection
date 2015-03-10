package com.buchner.auction.model.core.app;

import com.buchner.auction.model.core.entity.Bid;

import java.util.Comparator;

public class BidComperator implements Comparator<Bid> {

    @Override public int compare(Bid o1, Bid o2) {

        return o1.getAmount().compareTo(o2.getAmount());
    }
}
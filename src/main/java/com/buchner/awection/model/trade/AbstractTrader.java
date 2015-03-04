package com.buchner.awection.model.trade;

import com.buchner.awection.model.core.entity.AuctionType;
import com.buchner.awection.model.core.entity.Auction;

public abstract class AbstractTrader {

    protected AuctionType auctionType;
    protected AbstractTrader abstractTrader;


    public void handleTrade(Auction auction) {

        if (auctionType.equals(auction.getAuctionType())) {
            trade(auction);
        }

        if (null != abstractTrader) {
            abstractTrader.handleTrade(auction);
        }
    }

    protected abstract void trade(Auction auction);
}

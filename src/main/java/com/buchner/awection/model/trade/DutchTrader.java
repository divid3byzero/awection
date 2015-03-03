package com.buchner.awection.model.trade;

import com.buchner.awection.model.core.AuctionType;
import com.buchner.awection.model.core.entity.Auction;

public class DutchTrader extends AbstractTrader {

    public DutchTrader(AuctionType auctionType) {

        this.auctionType = auctionType;
    }

    @Override protected void trade(Auction auction) {

    }
}

package com.buchner.awection.model.core.trade;

import com.buchner.awection.model.core.entity.AuctionType;
import com.buchner.awection.model.core.entity.Auction;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

@Dutch
@RequestScoped
public class DutchTrader extends AbstractTrader {

    @Inject
    @English
    private AbstractTrader abstractTrader;

    public DutchTrader() {

    }

    public DutchTrader(AuctionType auctionType) {

        this.auctionType = auctionType;
    }

    @Override protected void trade(Auction auction) {

    }
}

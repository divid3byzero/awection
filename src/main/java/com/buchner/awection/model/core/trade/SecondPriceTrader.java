package com.buchner.awection.model.core.trade;

import com.buchner.awection.model.core.entity.AuctionType;
import com.buchner.awection.model.core.entity.Auction;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

@SecondPrice
@RequestScoped
public class SecondPriceTrader extends AbstractTrader {

    @Cda
    @Inject
    private AbstractTrader abstractTrader;

    public SecondPriceTrader() {

    }

    public SecondPriceTrader(AuctionType auctionType) {

        this.auctionType = auctionType;
    }

    @Override protected void trade(Auction auction) {

    }
}

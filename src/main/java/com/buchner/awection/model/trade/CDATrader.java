package com.buchner.awection.model.trade;

import com.buchner.awection.model.core.entity.AuctionType;
import com.buchner.awection.model.core.entity.Auction;

import javax.enterprise.context.RequestScoped;

@Cda
@RequestScoped
public class CdaTrader extends AbstractTrader {

    public CdaTrader() {

    }

    public CdaTrader(AuctionType auctionType) {

        this.auctionType = auctionType;
    }

    @Override protected void trade(Auction auction) {

    }
}

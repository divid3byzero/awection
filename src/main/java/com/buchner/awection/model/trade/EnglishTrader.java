package com.buchner.awection.model.trade;

import com.buchner.awection.model.core.entity.AuctionType;
import com.buchner.awection.model.core.entity.Auction;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

@English
@RequestScoped
public class EnglishTrader extends AbstractTrader {

    @Inject
    @SecondPrice
    private AbstractTrader abstractTrader;

    public EnglishTrader() {

    }

    public EnglishTrader(AuctionType auctionType) {

        this.auctionType = auctionType;
    }

    @Override protected void trade(Auction auction) {

    }
}

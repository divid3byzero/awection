package com.buchner.awection.model.core.trade;

import com.buchner.awection.model.core.entity.Auction;
import com.buchner.awection.model.core.entity.AuctionType;
import com.buchner.awection.model.core.entity.Bidder;

import javax.inject.Inject;
import java.math.BigDecimal;

public abstract class AbstractTrader {

    protected AuctionType auctionType;

    protected AuctionManager auctionManager;


    public Bidder handleTrade(Auction auction, BigDecimal amount, long userId) {

        return trade(auction, amount, userId);
    }

    public AuctionType getAuctionType() {

        return auctionType;
    }

    protected abstract Bidder trade(Auction auction, BigDecimal amount, long userId);
}


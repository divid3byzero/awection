package com.buchner.auction.model.core.trade;

import com.buchner.auction.model.core.entity.Auction;
import com.buchner.auction.model.core.entity.AuctionType;
import com.buchner.auction.model.core.entity.Bidder;

import java.math.BigDecimal;

public abstract class AbstractTrader {

    protected AuctionType auctionType;


    public Bidder handleTrade(Auction auction, BigDecimal amount, long userId) {

        return trade(auction, amount, userId);
    }

    public AuctionType getAuctionType() {

        return auctionType;
    }

    protected abstract Bidder trade(Auction auction, BigDecimal amount, long userId);
}


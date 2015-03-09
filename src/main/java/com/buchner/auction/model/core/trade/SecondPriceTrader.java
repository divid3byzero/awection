package com.buchner.auction.model.core.trade;

import com.buchner.auction.model.core.entity.AuctionType;
import com.buchner.auction.model.core.entity.Auction;
import com.buchner.auction.model.core.entity.Bidder;

import javax.enterprise.context.RequestScoped;
import java.math.BigDecimal;

@RequestScoped
public class SecondPriceTrader extends AbstractTrader {


    public SecondPriceTrader() {

        this.auctionType = AuctionType.SECOND_PRICE;
    }

    public SecondPriceTrader(AuctionType auctionType) {

        this.auctionType = auctionType;
    }


    @Override protected Bidder trade(Auction auction, BigDecimal amount, long userId) {

        return null;
    }
}

package com.buchner.awection.model.core.trade;

import com.buchner.awection.model.core.entity.AuctionType;
import com.buchner.awection.model.core.entity.Auction;
import com.buchner.awection.model.core.entity.Bidder;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import java.math.BigDecimal;

@RequestScoped
public class DutchTrader extends AbstractTrader {


    @Inject
    private AuctionManager auctionManager;

    public DutchTrader() {

        this.auctionType = AuctionType.DUTCH;
    }

    public DutchTrader(AuctionType auctionType) {

        this.auctionType = auctionType;
    }


    @Override protected Bidder trade(Auction auction, BigDecimal amount, long userId) {

        return null;
    }
}

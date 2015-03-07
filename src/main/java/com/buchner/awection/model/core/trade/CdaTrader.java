package com.buchner.awection.model.core.trade;

import com.buchner.awection.model.core.entity.AuctionType;
import com.buchner.awection.model.core.entity.Auction;
import com.buchner.awection.model.core.entity.Bidder;

import javax.enterprise.context.RequestScoped;
import java.math.BigDecimal;

@RequestScoped
public class CdaTrader extends AbstractTrader {

    public CdaTrader() {

        this.auctionType = AuctionType.CDA;
    }

    public CdaTrader(AuctionType auctionType) {

        this.auctionType = auctionType;
    }


    @Override protected Bidder trade(Auction auction, BigDecimal amount, long userId) {

        return null;
    }
}

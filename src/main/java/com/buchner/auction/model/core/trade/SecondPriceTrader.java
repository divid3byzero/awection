package com.buchner.auction.model.core.trade;

import com.buchner.auction.model.core.entity.AuctionResult;
import com.buchner.auction.model.core.entity.AuctionType;
import com.buchner.auction.model.core.entity.Auction;
import com.buchner.auction.model.core.entity.Bidder;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;

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


    @Override protected AuctionResult trade(Auction auction, BigDecimal amount, long userId) {

        return null;
    }

    @Override protected AuctionResult findAuctionWinner(Auction auction, long userId)
        throws PortalException, SystemException {
        return null;
    }
}

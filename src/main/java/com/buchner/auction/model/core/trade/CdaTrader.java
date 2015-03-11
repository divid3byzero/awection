package com.buchner.auction.model.core.trade;

import com.buchner.auction.model.core.entity.Auction;
import com.buchner.auction.model.core.entity.AuctionResult;
import com.buchner.auction.model.core.entity.AuctionType;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;

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



    @Override protected AuctionResult trade(Auction auction, BigDecimal amount, long userId)
        throws SystemException, PortalException {

        return null;
    }

    @Override protected AuctionResult findAuctionWinner(Auction auction, long userId)
        throws PortalException, SystemException {

        return null;
    }
}

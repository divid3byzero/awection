package com.buchner.auction.model.core.trade;

import com.buchner.auction.model.core.app.TradeRequest;
import com.buchner.auction.model.core.bean.TradeResponse;
import com.buchner.auction.model.core.database.OrderBookDao;
import com.buchner.auction.model.core.entity.Auction;
import com.buchner.auction.model.core.entity.AuctionType;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import java.math.BigDecimal;

@RequestScoped
public class CdaTrader extends AbstractTrader {

    @Inject
    private OrderBookDao orderBookDao;

    private TradeResponse tradeResponse;

    protected CdaTrader() {

        this.auctionType = AuctionType.CDA;
        this.tradeResponse = new TradeResponse();
    }

    @Override protected TradeResponse trade(TradeRequest tradeRequest)
        throws SystemException, PortalException {

        //TODO If request is buyer request -> check in seller order book if there is a corresponding entry.

        //TODO If request is selling request -> check in buyer order book if there is a corresponding entry.

        //TODO If there is an entry, match those bids, remove them from both order books and add
        //TODO create/return trade response by calling the findActionWinner method

        //TODO If there is not match enter bids into their corresponding order books.

        return null;
    }

    @Override protected TradeResponse findAuctionWinner(TradeRequest tradeRequest)
        throws PortalException, SystemException {

        return null;
    }

    @Override protected TradeResponse handleTimeOut(TradeRequest tradeRequest)
        throws SystemException, PortalException {

        //TODO If auction has timed out set auction running to false and return trade result with set auction timeout flag.
        return null;
    }
}

package com.buchner.auction.controller.trade;

import com.buchner.auction.model.core.app.AuctionSession;
import com.buchner.auction.model.trade.TradeView;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 * Controller class that controls trading.
 * Add a user to an auction and sends bids.
 */
@Named
@ApplicationScoped
public class TradeController {

    @Inject
    private TradeView tradeView;

    @Inject
    private AuctionSession auctionSession;

    public TradeController() {

    }

    public void startAuction(int articleId) {

        tradeView.joinAuction(articleId);
        auctionSession.clearSearchResults();
    }

    public void bid(int auctionId) {

        tradeView.sendBid(auctionId);
    }

    public void bid(int auctionId, String amount) {

        tradeView.sendBid(auctionId, amount);
    }

}

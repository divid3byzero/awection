package com.buchner.auction.controller.trade;

import com.buchner.auction.model.trade.TradeView;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@ApplicationScoped
public class TradeController {

    @Inject
    private TradeView tradeView;


    public TradeController() {

    }

    public void startAuction(int articleId) {

        tradeView.joinAuction(articleId);
    }

    public void bid(int auctionId) {

        tradeView.sendBid(auctionId);
    }

    public void checkDutchPrice(int auctionId) {


    }

}

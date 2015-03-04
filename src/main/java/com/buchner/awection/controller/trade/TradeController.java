package com.buchner.awection.controller.trade;

import com.buchner.awection.model.auction.TradeView;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@ApplicationScoped
public class TradeController {

    @Inject
    private TradeView tradeView;

    protected TradeController() {

    }

    public void startAuction(int articleId) {

        tradeView.joinAuction(articleId);
    }

}

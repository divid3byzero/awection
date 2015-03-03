package com.buchner.awection.model.auction;

import com.buchner.awection.model.core.entity.Auction;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

@Named
@RequestScoped
public class TradeView {

    private Auction auction;

    private TradeViewState tradeViewState;

    protected TradeView() {

    }

    public Auction getAuction() {

        return auction;
    }

    public void setAuction(Auction auction) {

        this.auction = auction;
    }
}

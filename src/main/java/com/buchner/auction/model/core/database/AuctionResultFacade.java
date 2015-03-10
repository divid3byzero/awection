package com.buchner.auction.model.core.database;

import com.buchner.auction.model.core.entity.AuctionResult;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

@Transaction
@RequestScoped
public class AuctionResultFacade {

    @Inject
    private AuctionResultDao auctionResultDao;

    protected AuctionResultFacade() {

    }

    public void saveAuctionResult(AuctionResult auctionResult) {

        auctionResultDao.save(auctionResult);
    }
}

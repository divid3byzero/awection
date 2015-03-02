package com.buchner.awection.model.core.database;

import com.buchner.awection.model.core.entity.Auction;

import javax.inject.Inject;

@Transaction
public class AuctionFacade {

    @Inject
    private AuctionDao auctionDao;

    public AuctionFacade() {

    }

    public void createAuction(Auction auction) {

        auctionDao.save(auction.getArticle());
        auctionDao.save(auction);
    }
}

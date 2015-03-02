package com.buchner.awection.controller.auction;

import com.buchner.awection.model.auction.AuctionView;
import com.buchner.awection.model.core.database.AuctionFacade;
import com.buchner.awection.model.core.entity.Auction;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@ApplicationScoped
public class AuctionController {

    @Inject
    private AuctionFacade auctionFacade;

    @Inject
    private AuctionView auctionView;

    public AuctionController() {

    }

    public void saveAuction() {

        Auction auction = auctionView.buildAuctionWithArticle();
        auctionFacade.createAuction(auction);
    }

}

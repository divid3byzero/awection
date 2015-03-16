package com.buchner.auction.model.auction;

import com.buchner.auction.model.core.app.TradeResponse;
import com.buchner.auction.model.core.database.AuctionResultFacade;
import com.buchner.auction.model.core.entity.AuctionType;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;

@Named
@RequestScoped
public class AuctionResultView {

    @Inject
    private AuctionResultFacade auctionResultFacade;

    protected AuctionResultView() {

    }

    public List<TradeResponse> auctionResultsByType(AuctionType auctionType) {

        return auctionResultFacade.getAuctionResult(auctionType);
    }
}

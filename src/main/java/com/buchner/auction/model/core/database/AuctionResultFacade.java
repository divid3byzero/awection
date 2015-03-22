package com.buchner.auction.model.core.database;

import com.buchner.auction.model.core.app.BeanService;
import com.buchner.auction.model.core.app.TradeResponse;
import com.buchner.auction.model.core.entity.AuctionResult;
import com.buchner.auction.model.core.entity.AuctionType;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import java.util.List;

/**
 * Offers transactional services concerning auction results.
 * For further explanation @see AuctionFacade
 */
@Transaction
@RequestScoped
public class AuctionResultFacade {

    @Inject
    private AuctionResultDao auctionResultDao;

    @Inject
    private BeanService beanService;

    protected AuctionResultFacade() {

    }

    public void saveAuctionResult(AuctionResult auctionResult) {

        auctionResultDao.save(auctionResult);
    }

    public List<TradeResponse> getAuctionResult(AuctionType auctionType) {

        List<AuctionResult> auctionResultsByType =
            auctionResultDao.findAuctionResultsByType(auctionType);
        return beanService.buildAuctionResultBeans(auctionResultsByType);
    }
}

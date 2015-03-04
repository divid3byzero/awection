package com.buchner.awection.model.core.database;

import com.buchner.awection.model.core.app.BeanService;
import com.buchner.awection.model.core.bean.AuctionBean;
import com.buchner.awection.model.core.entity.Auction;
import com.buchner.awection.model.core.entity.AuctionType;
import com.buchner.awection.model.core.entity.Bidder;
import com.liferay.portal.model.User;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import java.util.List;

@Transaction
@RequestScoped
public class TradeFacade {

    @Inject
    private TradeDao tradeDao;

    @Inject
    private AuctionDao auctionDao;

    @Inject
    private BeanService beanService;

    @Inject
    private User currentUser;

    protected TradeFacade() {

    }

    public List<AuctionBean> getAuctionByBidderAndType(AuctionType auctionType) {

        return beanService.buildAuctionBeans(
            tradeDao.findAuctionFromBidderAndType(currentUser.getUserId(), auctionType));
    }

    public void addBidderToAuction(int articleId) {

        Auction byArticle = auctionDao.findByArticle(articleId);
        Bidder bidder = new Bidder();
        bidder.setAuction(byArticle);
        bidder.setUserId(currentUser.getUserId());
        auctionDao.save(byArticle);
        tradeDao.save(bidder);
    }


}

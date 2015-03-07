package com.buchner.awection.model.core.database;

import com.buchner.awection.model.core.app.BeanService;
import com.buchner.awection.model.core.bean.AuctionBean;
import com.buchner.awection.model.core.entity.Auction;
import com.buchner.awection.model.core.entity.AuctionType;
import com.buchner.awection.model.core.entity.Bidder;
import com.buchner.awection.model.core.trade.AbstractTrader;
import com.buchner.awection.model.core.trade.AuctionManager;
import com.liferay.portal.model.User;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Instance;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import java.math.BigDecimal;
import java.util.List;

@Transaction
@RequestScoped
public class TradeFacade {

    @Inject
    private AuctionManager auctionManager;

    @Inject
    private BidderDao bidderDao;

    @Inject
    private AuctionDao auctionDao;

    @Inject
    private BeanService beanService;

    @Inject
    private User currentUser;

    @Inject
    private Instance<AbstractTrader> abstractTrader;

    protected TradeFacade() {

    }

    public List<AuctionBean> getAuctionByBidderAndType(AuctionType auctionType) {

        return beanService.buildAuctionBeans(
            auctionDao.findAuctionFromBidderAndType(currentUser.getUserId(), auctionType));
    }

    public void addBidderToAuction(int articleId) {

        Auction auction = auctionDao.findByArticle(articleId);
        Bidder bidder = new Bidder();
        bidder.addAuction(auction);
        bidder.setUserId(currentUser.getUserId());
        auctionDao.save(auction);
        bidderDao.save(bidder);
    }

    public void createBid(int auctionId, BigDecimal amount) {

        Auction auction = auctionDao.findById(auctionId);
        Bidder bidder = trade(amount, auction);
        if (null != bidder) {
            auction.setPrice(amount);
            bidderDao.save(bidder);
            auctionDao.save(auction);
        } else {
            FacesContext facesContext = FacesContext.getCurrentInstance();
            facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Error", "Your bid does not meet the requirements."));
        }
    }

    private Bidder trade(BigDecimal amount, Auction auction) {

        Bidder bidder = null;
        for (AbstractTrader trader : abstractTrader) {
            if (trader.getAuctionType().equals(auction.getAuctionType())) {
                bidder = trader.handleTrade(auction, amount, currentUser.getUserId());
            }
        }
        return bidder;
    }

}

package com.buchner.auction.model.core.database;

import com.buchner.auction.model.core.app.BeanService;
import com.buchner.auction.model.core.bean.AuctionBean;
import com.buchner.auction.model.core.entity.Auction;
import com.buchner.auction.model.core.entity.AuctionType;
import com.buchner.auction.model.core.entity.Bidder;
import com.buchner.auction.model.core.trade.AbstractTrader;
import com.liferay.portal.model.User;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Instance;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Transaction
@RequestScoped
public class TradeFacade {

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

    public Auction getByAuctionId(int auctionId) {

        return auctionDao.findById(auctionId);
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
        DateTime now = new DateTime(DateTimeZone.forID("Europe/Berlin"));
        Date nowDate = now.toDate();
        if (nowDate.compareTo(auction.getEndTime()) == 0
            || nowDate.compareTo(auction.getEndTime()) == 1) {

            auction.setRunning(false);
            auctionDao.save(auction);
            auctionMessage();
        } else {

            Bidder bidder = trade(amount, auction);
            if (null != bidder) {
                auction.setPrice(amount);
                bidderDao.save(bidder);
                auctionDao.save(auction);
            } else {
                auctionMessage();
            }
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

    private void auctionMessage() {

        FacesContext facesContext = FacesContext.getCurrentInstance();
        facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info",
            "Bid does not meet requirements or auction is over."));
    }

}

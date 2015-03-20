package com.buchner.auction.model.core.database;

import com.buchner.auction.model.core.app.BeanService;
import com.buchner.auction.model.core.app.TradeRequest;
import com.buchner.auction.model.core.app.TradeResponse;
import com.buchner.auction.model.core.bean.AuctionBean;
import com.buchner.auction.model.core.bean.BidBean;
import com.buchner.auction.model.core.entity.Auction;
import com.buchner.auction.model.core.entity.AuctionType;
import com.buchner.auction.model.core.entity.Bid;
import com.buchner.auction.model.core.entity.Bidder;
import com.buchner.auction.model.core.trade.AbstractTrader;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.model.User;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Instance;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import java.util.List;

@Transaction
@RequestScoped
public class TradeFacade {

    @Inject
    private BidderDao bidderDao;

    @Inject
    private BidDao bidDao;

    @Inject
    private AuctionDao auctionDao;

    @Inject
    private AuctionResultDao auctionResultDao;

    @Inject
    private BeanService beanService;

    @Inject
    private User currentUser;

    @Inject
    private Instance<AbstractTrader> abstractTrader;

    protected TradeFacade() {

    }

    public List<AuctionBean> getAuctionByBidderAndType(AuctionType auctionType, long userId) {

        return beanService.buildAuctionBeans(
            auctionDao.findAuctionFromBidderAndType(userId, auctionType));
    }

    public void addBidderToAuction(int articleId, long userId) {

        Auction auction = auctionDao.findByArticle(articleId);
        Bidder bidder = new Bidder();
        bidder.addAuction(auction);
        auction.addBidder(bidder);
        bidder.setUserId(userId);
        auctionDao.save(auction);
        bidderDao.save(bidder);
    }

    public void fireTrader(TradeRequest tradeRequest) {

        Auction auction = tradeRequest.getAuction();

        if (auction.isRunning()) {

            for (AbstractTrader trader : abstractTrader) {
                if (trader.getAuctionType().equals(auction.getAuctionType())) {
                    try {

                        TradeResponse tradeResponse =
                            trader.handleTrade(tradeRequest);
                        handleTradeResponse(tradeResponse);

                    } catch (PortalException | SystemException e) {
                        throw new IllegalArgumentException("Fatal error occurred");
                    }
                }
            }

        } else {
            auctionMessage("Auction is over.");
        }
    }

    private void handleTradeResponse(TradeResponse tradeResponse) {

        if (null != tradeResponse) {

            if (!tradeResponse.isAuctionTimeout()) {

                if (!tradeResponse.isAuctionRunning()) {
                    auctionResultDao.save(tradeResponse.buildAuctionResult());
                    auctionMessage("Auction is over.");
                } else {
                    bidderDao.save(tradeResponse.getBidder());
                }
            } else {
                auctionMessage("Auction has timed out.");
            }
        }
    }

    public BidBean getBidByUserId() {

        return beanService.buildBidBean(bidDao.getbyUserId(currentUser.getUserId()));
    }

    private void auctionMessage(String message) {

        FacesContext facesContext = FacesContext.getCurrentInstance();
        facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info",
            message));
    }

    public boolean hasBidden() {

        return null != bidderDao.findByUserId(currentUser.getUserId());
    }

}

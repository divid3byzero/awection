package com.buchner.auction.model.core.database;

import com.buchner.auction.model.core.app.BeanService;
import com.buchner.auction.model.core.app.TradeRequest;
import com.buchner.auction.model.core.app.TradeResponse;
import com.buchner.auction.model.core.bean.AuctionBean;
import com.buchner.auction.model.core.bean.BidBean;
import com.buchner.auction.model.core.entity.Auction;
import com.buchner.auction.model.core.entity.AuctionType;
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

/**
 * Facade class responsible for all transactional trading and bidding related
 * actions. @see AuctionFacade for further information concerning the application
 * of transactions. All needed dependencies are injected using the CDI framework.
 */
@Transaction
@RequestScoped
public class TradeFacade {

    /* DAO classes for database access */
    @Inject
    private BidderDao bidderDao;

    @Inject
    private BidDao bidDao;

    @Inject
    private AuctionDao auctionDao;

    @Inject
    private AuctionResultDao auctionResultDao;

    /* Service class for creating beans from entities. */
    @Inject
    private BeanService beanService;

    /* Currently logged on user. */
    @Inject
    private User currentUser;

    /* All implementations of the abstract trader class.
     * Instance<...> acts like a list and holds all available implementations
     * that have been found using class path discovery.*/
    @Inject
    private Instance<AbstractTrader> abstractTrader;

    protected TradeFacade() {

    }

    /**
     * Used to get all auctions to which the current user is registered as bidder
     * and are equal to a certain type.
     */
    public List<AuctionBean> getAuctionByBidderAndType(AuctionType auctionType, long userId) {

        return beanService.buildAuctionBeans(
            auctionDao.findAuctionFromBidderAndType(userId, auctionType));
    }

    /**
     * This adds a bidder to an auction. The method is called when a user
     * decides to participate in an auction after search for articles.
     */
    public void addBidderToAuction(int articleId, long userId) {

        Auction auction = auctionDao.findByArticle(articleId);
        Bidder bidder = new Bidder();
        bidder.addAuction(auction);
        auction.addBidder(bidder);
        bidder.setUserId(userId);
        auctionDao.save(auction);
        bidderDao.save(bidder);
    }

    /**
     * This is basically the core method of the trading mechanism.
     * 
     *
     * @param tradeRequest
     */
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

    public List<BidBean> getBidsByUserId(int auctionId) {

        return beanService.buildBidBeans(bidDao.getByBidderAndAuction(currentUser.getUserId(), auctionId));
    }

    private void auctionMessage(String message) {

        FacesContext facesContext = FacesContext.getCurrentInstance();
        facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info",
            message));
    }

    public boolean hasBidden(int auctionId) {

        return bidderDao.getBidByAuction(currentUser.getUserId(), auctionId).size() > 0;
    }

}

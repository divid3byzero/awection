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
     * This is the core method of the trading mechanism. It accepts a trade request
     * which is the representation of a bid enriched with additional information
     * needed to process the bid. After checking if the auction is still running,
     * (there is a possibilitiy that the auction has bees ended by another bidder. This is
     * especially relevant for Dutch auctions.) it iterates over all available trader instances
     * and tries to find the fitting one. If there is a trader that is responsible for handling
     * the auction, the trade request is passed to that trader instance. The trader processes
     * the request according to its implementation and returns a trade response object. This trade response
     * is then processed in this facade. The trade response holds all information that has been
     * produced during the trading procedure. This information can e.g. be a flag that the auctiion
     * has timed out, information concerning the new bid etc. If the auction is already over at this point
     * a message is created for the user telling him so.
     */
    public void fireTrader(TradeRequest tradeRequest) {

        // Get auction from request and check if it is running.
        Auction auction = tradeRequest.getAuction();
        if (auction.isRunning()) {

            // Look for appropriate trader instance.
            for (AbstractTrader trader : abstractTrader) {
                if (trader.getAuctionType().equals(auction.getAuctionType())) {
                    try {

                        // Handle trade request
                        TradeResponse tradeResponse =
                            trader.handleTrade(tradeRequest);
                        // Process trade response
                        handleTradeResponse(tradeResponse);

                    } catch (PortalException | SystemException e) {
                        throw new IllegalArgumentException("Fatal error occurred");
                    }
                }
            }

        } else {
            // Inform user that auction is over.
            auctionMessage("Auction is over.");
        }
    }

    /**
     * There are a number of things that might have to be done with the trade response.
     * 1. There is the possibility of a time out, meaning that the current date/time is
     * after the set end time of an auction.
     * 2. There auction is over because there is a winner.
     * 3. It is a regular bid which has to be saved.
     * @param tradeResponse
     */
    private void handleTradeResponse(TradeResponse tradeResponse) {

        if (null != tradeResponse) {

            // Check for timeout.
            if (!tradeResponse.isAuctionTimeout()) {

                // Save winner
                if (!tradeResponse.isAuctionRunning()) {
                    auctionResultDao.save(tradeResponse.buildAuctionResult());
                    auctionMessage("Auction is over.");
                } else {
                    // Create bid.
                    bidderDao.save(tradeResponse.getBidder());
                }
            } else {
                // In case of time out show message.
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

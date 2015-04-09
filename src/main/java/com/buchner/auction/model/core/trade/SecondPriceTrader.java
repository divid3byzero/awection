package com.buchner.auction.model.core.trade;

import com.buchner.auction.model.core.app.LiferayComponentService;
import com.buchner.auction.model.core.app.TradeRequest;
import com.buchner.auction.model.core.app.TradeResponse;
import com.buchner.auction.model.core.entity.AuctionType;
import com.buchner.auction.model.core.entity.Bid;
import com.buchner.auction.model.core.entity.Bidder;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import java.util.List;

/**
 * Trader implementation for simultaneous second price auctions. Traders
 * are created per request. This allows for automatic thread safety as
 * each request is processed in a single thread that is distributed
 * from the Tomcat thread pool.
 */
@RequestScoped
public class SecondPriceTrader extends AbstractTrader {

    @Inject
    private LiferayComponentService liferayComponentService;

    private TradeResponse tradeResponse;

    protected SecondPriceTrader() {

        this.auctionType = AuctionType.SECOND_PRICE;
        tradeResponse = new TradeResponse();
    }


    /**
     * This auction type allows only for one bid per bidder. The only requirements that this bid
     * has to meet is, that its value has to be higher than the original article price.
     */
    @Override protected TradeResponse trade(TradeRequest tradeRequest)
        throws PortalException, SystemException {

        // Check if bid value is higher than article price.
        if (tradeRequest.getAuction().getPrice().compareTo(tradeRequest.getAmount()) == -1) {

            // Create bid and add to bidder.
            tradeResponse.setAuctionRunning(true);
            List<Bidder> bidder = tradeRequest.getAuction().getBidder();
            Bid bid = new Bid();
            bid.setAmount(tradeRequest.getAmount());
            bid.setAuctionId(tradeRequest.getAuction().getId());

            for (Bidder bidderElem : bidder) {
                if (tradeRequest.getUserId() == bidderElem.getUserId()) {
                    bidderElem.addBid(bid);
                    tradeResponse.setBidder(bidderElem);
                    return tradeResponse;
                }
            }
        } else {
            auctionMessage("Bid does not meet requirements");
        }
        return null;
    }

    @Override protected TradeResponse handleTimeOut(TradeRequest tradeRequest)
        throws SystemException, PortalException {

        tradeRequest.getAuction().setRunning(false);
        tradeResponse.setAuctionTimeout();
        return tradeResponse;
    }

    /**
     * Trader is not responsible for finding winner. The winner is determined after the auction is over
     * in the same way as in English auctions. The difference is in the price the winner has to pay. The winner
     * has to pay the price of the second highest bid. As the only request issued by a user is that single bid that is
     * allowed, a Quartz scheduler job is responsible for finding the winners.
     */
    @Override protected TradeResponse findAuctionWinner(TradeRequest tradeRequest)
        throws PortalException, SystemException {

        // This method is not needed for this type of auction. Winners are found via AuctionManager class.
        return null;
    }
}

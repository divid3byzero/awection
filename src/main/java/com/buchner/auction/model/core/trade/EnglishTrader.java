package com.buchner.auction.model.core.trade;

import com.buchner.auction.model.core.app.BidComperator;
import com.buchner.auction.model.core.app.LiferayComponentService;
import com.buchner.auction.model.core.app.TradeRequest;
import com.buchner.auction.model.core.app.TradeResponse;
import com.buchner.auction.model.core.entity.*;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.model.User;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

/**
 * Trader implementation for English auctions. Traders
 * are created per request. This allows for automatic thread safety as
 * each request is processed in a single thread that is distributed
 * from the Tomcat thread pool.
 */
@RequestScoped
public class EnglishTrader extends AbstractTrader {

    @Inject
    private LiferayComponentService liferayComponentService;

    private TradeResponse tradeResponse;

    protected EnglishTrader() {

        this.auctionType = AuctionType.ENGLISH;
        tradeResponse = new TradeResponse();
    }

    /**
     * In English auctions a new bid has to be higher than the currently highest bid.
     * This is the first check in this implementation of the trade() method. If the bid
     * is valid a new bid is created and added to the corresponding bidder. If the bid is invalid
     * a message is presented to the user.
     */
    @Override protected TradeResponse trade(TradeRequest tradeRequest)
        throws SystemException, PortalException {

        if (tradeRequest.getAuction().getPrice().compareTo(tradeRequest.getAmount()) == -1) {

            tradeResponse.setAuctionRunning(true);
            List<Bidder> bidder = tradeRequest.getAuction().getBidder();
            Bid bid = new Bid();
            bid.setAmount(tradeRequest.getAmount());
            bid.setAuctionId(tradeRequest.getAuction().getId());
            tradeRequest.getAuction().setPrice(tradeRequest.getAmount());

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

    /**
     * In case of an auction timeout the auction is over and winner has to be found.
     */
    @Override protected TradeResponse handleTimeOut(TradeRequest tradeRequest)
        throws SystemException, PortalException {

        tradeRequest.getAuction().setRunning(false);
        return findAuctionWinner(tradeRequest);
    }

    /**
     * The winner of an English Auction is the bidder with the highest bid after the auction
     * is over. In real life the auction lifetime is managed by an auctioneer. This application
     * manages the lifetime of auctions by defining a time span.
     */
    @Override protected TradeResponse findAuctionWinner(TradeRequest tradeRequest)
        throws PortalException, SystemException {

        List<Bidder> bidder = tradeRequest.getAuction().getBidder();
        List<Bid> auctionBids = new ArrayList<>();
        for (Bidder bidderElem : bidder) {
            auctionBids.addAll(bidderElem.getBids());
        }

        // After find all available bids they are sorted ascending according to their
        // value by a Comparator implementation.
        auctionBids.sort(new BidComperator());
        Bid winningBid = auctionBids.get(0);

        long winningUserId = winningBid.getBidder().getUserId();
        User user = liferayComponentService.findUserById(winningUserId);
        tradeResponse.setAuctionRunning(false);
        tradeResponse.setPrice(winningBid.getAmount());
        tradeResponse.setFirstName(user.getFirstName());
        tradeResponse.setSurname(user.getLastName());
        tradeResponse.setMail(user.getEmailAddress());
        tradeResponse.setAuctionType(tradeRequest.getAuction().getAuctionType());
        tradeResponse.setDescription(tradeRequest.getAuction().getArticle().getShortDesc());
        return tradeResponse;
    }
}

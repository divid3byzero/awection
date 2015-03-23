package com.buchner.auction.model.core.trade;

import com.buchner.auction.model.core.app.LiferayComponentService;
import com.buchner.auction.model.core.app.TradeRequest;
import com.buchner.auction.model.core.app.TradeResponse;
import com.buchner.auction.model.core.entity.*;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.model.User;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Trader implementation for Dutch auctions. Traders
 * are created per request. This allows for automatic thread safety as
 * each request is processed in a single thread that is distributed
 * from the Tomcat thread pool.
 */
@RequestScoped
public class DutchTrader extends AbstractTrader {

    @Inject
    private LiferayComponentService liferayComponentService;

    private TradeResponse tradeResponse;

    protected DutchTrader() {

        this.auctionType = AuctionType.DUTCH;
        tradeResponse = new TradeResponse();
    }

    /**
     * The Dutch auction ends as soon as a bidder sends a bid. So the first thing this method does is
     * end the auction. After that the bid is created and the bidder from whom that bid came has to be found.
     * The bidder gets that bid which is at the same time the winning bid. As the auction is over the findAuctionWinner()
     * method is called and the according trade response can be built.
     */
    @Override protected TradeResponse trade(TradeRequest tradeRequest)
        throws SystemException, PortalException {

        tradeRequest.getAuction().setRunning(false);
        Bid bid = new Bid();
        bid.setAmount(tradeRequest.getAmount());
        bid.setAuctionId(tradeRequest.getAuction().getId());
        List<Bidder> bidderList = tradeRequest.getAuction().getBidder();
        Bidder targetBidder = getBidder(tradeRequest.getUserId(), bidderList);

        if (null != targetBidder) {
            targetBidder.addBid(bid);
            tradeResponse = findAuctionWinner(tradeRequest);
        }
        return tradeResponse;
    }

    /**
     * Stops auction from running and sets time out flag in trade response.
     */
    @Override protected TradeResponse handleTimeOut(TradeRequest tradeRequest)
        throws SystemException, PortalException {

        tradeRequest.getAuction().setRunning(false);
        tradeResponse.setAuctionTimeout();
        return tradeResponse;
    }

    /**
     * Find winner of the auction. In this type of auction the winner corresponds to that bidder that has
     * a bid. There can be only one bidder with a bid.
     */
    @Override protected TradeResponse findAuctionWinner(TradeRequest tradeRequest)
        throws PortalException, SystemException {

        Bidder bidder = getBidder(tradeRequest.getUserId(), tradeRequest.getAuction().getBidder());
        List<Bid> bids = bidder.getBids();
        List<Bid> winningBid =
            bids.stream().filter(b -> b.getAuctionId() == tradeRequest.getAuction().getId())
                .collect(Collectors.toList());

        User user = liferayComponentService.findUserById(bidder.getUserId());
        tradeResponse.setAuctionRunning(false);
        tradeResponse.setPrice(winningBid.get(0).getAmount());
        tradeResponse.setFirstName(user.getFirstName());
        tradeResponse.setSurname(user.getLastName());
        tradeResponse.setMail(user.getEmailAddress());
        tradeResponse.setAuctionType(tradeRequest.getAuction().getAuctionType());
        tradeResponse.setDescription(tradeRequest.getAuction().getArticle().getShortDesc());
        return tradeResponse;
    }


    private Bidder getBidder(long userId, List<Bidder> bidderList) {

        for (Bidder bidder : bidderList) {
            if (userId == bidder.getUserId()) {
                return bidder;
            }
        }
        return null;
    }
}

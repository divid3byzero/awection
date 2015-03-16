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

@RequestScoped
public class DutchTrader extends AbstractTrader {

    @Inject
    private LiferayComponentService liferayComponentService;

    private TradeResponse tradeResponse;

    protected DutchTrader() {

        this.auctionType = AuctionType.DUTCH;
        tradeResponse = new TradeResponse();
    }

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

    @Override protected TradeResponse handleTimeOut(TradeRequest tradeRequest)
        throws SystemException, PortalException {

        tradeRequest.getAuction().setRunning(false);
        tradeResponse.setAuctionTimeout();
        return tradeResponse;
    }

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

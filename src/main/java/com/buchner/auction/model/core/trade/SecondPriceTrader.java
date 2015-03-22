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

@RequestScoped
public class SecondPriceTrader extends AbstractTrader {

    @Inject
    private LiferayComponentService liferayComponentService;

    private TradeResponse tradeResponse;

    protected SecondPriceTrader() {

        this.auctionType = AuctionType.SECOND_PRICE;
        tradeResponse = new TradeResponse();
    }


    @Override protected TradeResponse trade(TradeRequest tradeRequest)
        throws PortalException, SystemException {

        if (tradeRequest.getAuction().getPrice().compareTo(tradeRequest.getAmount()) == -1) {

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

    @Override protected TradeResponse findAuctionWinner(TradeRequest tradeRequest)
        throws PortalException, SystemException {

        // This method is not needed for this type of auction. Winners are found via AuctionManager class.
        return null;
    }
}

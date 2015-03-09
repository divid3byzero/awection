package com.buchner.auction.model.core.trade;

import com.buchner.auction.model.core.database.BidderDao;
import com.buchner.auction.model.core.entity.AuctionType;
import com.buchner.auction.model.core.entity.Auction;
import com.buchner.auction.model.core.entity.Bid;
import com.buchner.auction.model.core.entity.Bidder;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import java.math.BigDecimal;
import java.util.List;

@RequestScoped
public class EnglishTrader extends AbstractTrader {

    @Inject
    private BidderDao bidderDao;

    public EnglishTrader() {

        this.auctionType = AuctionType.ENGLISH;
    }

    @Override protected Bidder trade(Auction auction, BigDecimal amount, long userId) {

        if (auction.getPrice().compareTo(amount) == -1) {

            List<Bidder> bidder = auction.getBidder();
            Bid bid = new Bid();
            bid.setAmount(amount);
            bid.setAuctionId(auction.getId());

            Bidder targetBidder = null;
            for (Bidder bidderElem : bidder) {
                if (userId == bidderElem.getUserId()) {
                    targetBidder = bidderElem;
                    targetBidder.addBid(bid);
                }
            }
            return targetBidder;
        }
        return null;
    }
}

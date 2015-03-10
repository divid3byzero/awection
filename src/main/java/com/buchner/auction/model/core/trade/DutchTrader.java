package com.buchner.auction.model.core.trade;

import com.buchner.auction.model.core.app.LiferayComponentService;
import com.buchner.auction.model.core.entity.Auction;
import com.buchner.auction.model.core.entity.AuctionType;
import com.buchner.auction.model.core.entity.Bid;
import com.buchner.auction.model.core.entity.Bidder;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@RequestScoped
public class DutchTrader extends AbstractTrader {

    @Inject
    private LiferayComponentService liferayComponentService;

    protected DutchTrader() {

        this.auctionType = AuctionType.DUTCH;
    }

    @Override protected Bidder trade(Auction auction, BigDecimal amount, long userId) {


        DateTime now = new DateTime(DateTimeZone.forID("Europe/Berlin"));
        Date nowDate = now.toDate();
        if (nowDate.compareTo(auction.getEndTime()) == 0
            || nowDate.compareTo(auction.getEndTime()) == 1 || !auction.isRunning()) {

            findAuctionWinner(auction, userId);
            auctionMessage("Auction is over.");
        }

        auction.setRunning(false);
        Bid bid = new Bid();
        bid.setAmount(amount);
        bid.setAuctionId(auction.getId());
        List<Bidder> bidder = auction.getBidder();
        Bidder targetBidder = bidder.get(0);
        targetBidder.addBid(bid);
        return targetBidder;
    }

    @Override protected void findAuctionWinner(Auction auction, long userId) {

        Bidder bidder = auction.getBidder().get(0);

    }
}

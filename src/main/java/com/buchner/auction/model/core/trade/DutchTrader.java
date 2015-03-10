package com.buchner.auction.model.core.trade;

import com.buchner.auction.model.core.app.LiferayComponentService;
import com.buchner.auction.model.core.database.AuctionResultFacade;
import com.buchner.auction.model.core.entity.*;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.model.User;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RequestScoped
public class DutchTrader extends AbstractTrader {

    @Inject
    private AuctionResultFacade auctionResultFacade;

    @Inject
    private LiferayComponentService liferayComponentService;

    protected DutchTrader() {

        this.auctionType = AuctionType.DUTCH;
    }

    @Override protected void trade(Auction auction, BigDecimal amount, long userId)
        throws SystemException, PortalException {


        DateTime now = new DateTime(DateTimeZone.forID("Europe/Berlin"));
        Date nowDate = now.toDate();
        if (nowDate.compareTo(auction.getEndTime()) == 0
            || nowDate.compareTo(auction.getEndTime()) == 1 || !auction.isRunning()) {

            findAuctionWinner(auction, userId);
            auctionMessage("Auction is over.");
        } else {

            auction.setRunning(false);
            Bid bid = new Bid();
            bid.setAmount(amount);
            bid.setAuctionId(auction.getId());
            List<Bidder> bidderList = auction.getBidder();
            Bidder targetBidder = getBidder(userId, bidderList);

            if (null != targetBidder) {
                targetBidder.addBid(bid);
                findAuctionWinner(auction, userId);
            }
        }
    }

    @Override protected void findAuctionWinner(Auction auction, long userId)
        throws PortalException, SystemException {

        Bidder bidder = getBidder(userId, auction.getBidder());
        List<Bid> bids = bidder.getBids();
        List<Bid> winningBid = bids.stream().filter(b -> b.getAuctionId() == auction.getId())
            .collect(Collectors.toList());

        User user = liferayComponentService.findUserById(bidder.getUserId());
        AuctionResult auctionResult = new AuctionResult();
        auctionResult.setPrice(winningBid.get(0).getAmount());
        auctionResult.setFirstName(user.getFirstName());
        auctionResult.setSurname(user.getLastName());
        auctionResult.setMail(user.getEmailAddress());
        auctionResult.setAuctionType(auction.getAuctionType());
        auctionResult.setDescription(auction.getArticle().getShortDesc());
        auctionResultFacade.saveAuctionResult(auctionResult);
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

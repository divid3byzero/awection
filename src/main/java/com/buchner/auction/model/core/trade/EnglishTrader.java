package com.buchner.auction.model.core.trade;

import com.buchner.auction.model.core.app.BidComperator;
import com.buchner.auction.model.core.app.LiferayComponentService;
import com.buchner.auction.model.core.bean.TradeResultBean;
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
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

@RequestScoped
public class EnglishTrader extends AbstractTrader {

    @Inject
    private LiferayComponentService liferayComponentService;

    private TradeResultBean tradeResultBean;

    public EnglishTrader() {

        this.auctionType = AuctionType.ENGLISH;
        tradeResultBean = new TradeResultBean();
    }

    @Override protected TradeResultBean trade(Auction auction, BigDecimal amount, long userId)
        throws SystemException, PortalException {

        if (auctionTimeout(auction)) {

            auction.setRunning(false);
            return findAuctionWinner(auction, userId);
        }

        if (auction.getPrice().compareTo(amount) == -1) {

            tradeResultBean.setAuctionRunning(true);
            List<Bidder> bidder = auction.getBidder();
            Bid bid = new Bid();
            bid.setAmount(amount);
            bid.setAuctionId(auction.getId());
            auction.setPrice(amount);

            for (Bidder bidderElem : bidder) {
                if (userId == bidderElem.getUserId()) {
                    bidderElem.addBid(bid);
                    tradeResultBean.setBidder(bidderElem);
                    return tradeResultBean;
                }
            }

        } else {
            auctionMessage("Bid does not meet requirements");
        }
        return null;
    }

    @Override protected TradeResultBean findAuctionWinner(Auction auction, long userId)
        throws PortalException, SystemException {

        List<Bidder> bidder = auction.getBidder();
        List<Bid> auctionBids = new ArrayList<>();
        for (Bidder bidderElem : bidder) {
            auctionBids.addAll(bidderElem.getBids());
        }

        auctionBids.sort(new BidComperator());
        Bid winningBid = auctionBids.get(0);

        long winningUserId = winningBid.getBidder().getUserId();
        User user = liferayComponentService.findUserById(winningUserId);
        tradeResultBean.setAuctionRunning(false);
        tradeResultBean.setPrice(winningBid.getAmount());
        tradeResultBean.setFirstName(user.getFirstName());
        tradeResultBean.setSurname(user.getLastName());
        tradeResultBean.setMail(user.getEmailAddress());
        tradeResultBean.setAuctionType(auction.getAuctionType());
        tradeResultBean.setDescription(auction.getArticle().getShortDesc());
        return tradeResultBean;
    }
}

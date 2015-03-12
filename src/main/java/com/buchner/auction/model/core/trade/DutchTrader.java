package com.buchner.auction.model.core.trade;

import com.buchner.auction.model.core.app.LiferayComponentService;
import com.buchner.auction.model.core.bean.TradeResultBean;
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

@RequestScoped
public class DutchTrader extends AbstractTrader {

    @Inject
    private LiferayComponentService liferayComponentService;

    private TradeResultBean tradeResultBean;

    protected DutchTrader() {

        this.auctionType = AuctionType.DUTCH;
        tradeResultBean = new TradeResultBean();
    }

    @Override protected TradeResultBean trade(Auction auction, BigDecimal amount, long userId)
        throws SystemException, PortalException {

        auction.setRunning(false);
        Bid bid = new Bid();
        bid.setAmount(amount);
        bid.setAuctionId(auction.getId());
        List<Bidder> bidderList = auction.getBidder();
        Bidder targetBidder = getBidder(userId, bidderList);

        if (null != targetBidder) {
            targetBidder.addBid(bid);
            tradeResultBean = findAuctionWinner(auction, userId);
        }
        return tradeResultBean;
    }

    @Override protected TradeResultBean handleTimeOut(Auction auction, long userId)
        throws SystemException, PortalException {

        auction.setRunning(false);
        tradeResultBean.setAuctionTimeout();
        return tradeResultBean;
    }

    @Override protected TradeResultBean findAuctionWinner(Auction auction, long userId)
        throws PortalException, SystemException {

        Bidder bidder = getBidder(userId, auction.getBidder());
        List<Bid> bids = bidder.getBids();
        List<Bid> winningBid = bids.stream().filter(b -> b.getAuctionId() == auction.getId())
            .collect(Collectors.toList());

        User user = liferayComponentService.findUserById(bidder.getUserId());
        tradeResultBean.setAuctionRunning(false);
        tradeResultBean.setPrice(winningBid.get(0).getAmount());
        tradeResultBean.setFirstName(user.getFirstName());
        tradeResultBean.setSurname(user.getLastName());
        tradeResultBean.setMail(user.getEmailAddress());
        tradeResultBean.setAuctionType(auction.getAuctionType());
        tradeResultBean.setDescription(auction.getArticle().getShortDesc());
        return tradeResultBean;
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

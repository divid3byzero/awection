package com.buchner.auction.model.core.trade;

import com.buchner.auction.model.core.app.BidComperator;
import com.buchner.auction.model.core.app.LiferayComponentService;
import com.buchner.auction.model.core.bean.TradeResultBean;
import com.buchner.auction.model.core.entity.Auction;
import com.buchner.auction.model.core.entity.AuctionType;
import com.buchner.auction.model.core.entity.Bid;
import com.buchner.auction.model.core.entity.Bidder;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.model.User;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@RequestScoped
public class SecondPriceTrader extends EnglishTrader {

    @Inject
    private LiferayComponentService liferayComponentService;

    private TradeResultBean tradeResultBean;

    protected SecondPriceTrader() {

        this.auctionType = AuctionType.SECOND_PRICE;
        tradeResultBean = new TradeResultBean();
    }


    @Override protected TradeResultBean trade(Auction auction, BigDecimal amount, long userId)
        throws PortalException, SystemException {

        return super.trade(auction, amount, userId);
    }

    @Override protected TradeResultBean findAuctionWinner(Auction auction, long userId)
        throws PortalException, SystemException {

        List<Bidder> bidder = auction.getBidder();
        List<Bid> auctionBids = new ArrayList<>();
        for (Bidder bidderElem : bidder) {
            auctionBids.addAll(bidderElem.getBids());
        }

        auctionBids.sort(new BidComperator());
        Bid winningBid = auctionBids.get(1);

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

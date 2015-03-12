package com.buchner.auction.model.core.trade;

import com.buchner.auction.model.core.app.BidComperator;
import com.buchner.auction.model.core.app.LiferayComponentService;
import com.buchner.auction.model.core.entity.*;
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

    public SecondPriceTrader() {

        this.auctionType = AuctionType.SECOND_PRICE;
    }

    public SecondPriceTrader(AuctionType auctionType) {

        this.auctionType = auctionType;
    }


    @Override protected AuctionResult trade(Auction auction, BigDecimal amount, long userId)
        throws PortalException, SystemException {

        return super.trade(auction, amount, userId);
    }

    @Override protected AuctionResult findAuctionWinner(Auction auction, long userId)
        throws PortalException, SystemException {


        List<Bidder> bidder = auction.getBidder();
        List<Bid> auctionBids = new ArrayList<>();
        for (Bidder bidderElem : bidder) {
            auctionBids.addAll(bidderElem.getBids());
        }

        auctionBids.sort(new BidComperator());
        Bid winningBid = auctionBids.get(0);
        BigDecimal price = auctionBids.get(1).getAmount();

        long winningUserId = winningBid.getBidder().getUserId();
        User user = liferayComponentService.findUserById(winningUserId);
        AuctionResult auctionResult = new AuctionResult();
        auctionResult.setPrice(price);
        auctionResult.setFirstName(user.getFirstName());
        auctionResult.setSurname(user.getLastName());
        auctionResult.setMail(user.getEmailAddress());
        auctionResult.setAuctionType(auction.getAuctionType());
        auctionResult.setDescription(auction.getArticle().getShortDesc());
        return auctionResult;
    }
}

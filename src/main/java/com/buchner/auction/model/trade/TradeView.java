package com.buchner.auction.model.trade;

import com.buchner.auction.model.core.app.TradeRequest;
import com.buchner.auction.model.core.bean.AuctionBean;
import com.buchner.auction.model.core.database.AuctionFacade;
import com.buchner.auction.model.core.database.TradeFacade;
import com.buchner.auction.model.core.entity.Auction;
import com.buchner.auction.model.core.entity.AuctionType;
import com.buchner.auction.model.core.entity.CdaType;
import com.liferay.portal.model.User;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.math.BigDecimal;
import java.util.List;

@Named
@RequestScoped
public class TradeView {

    @Inject
    private TradeFacade tradeFacade;

    @Inject
    private AuctionFacade auctionFacade;

    @Inject
    private User currentUser;

    private BigDecimal bidAmount;

    protected TradeView() {

    }

    public List<AuctionBean> getRunningUserAuctions(AuctionType auctionType) {

        return tradeFacade.getAuctionByBidderAndType(auctionType, currentUser.getUserId());
    }

    public void joinAuction(int articleId) {

        tradeFacade.addBidderToAuction(articleId, currentUser.getUserId());
    }

    public BigDecimal getBidAmount() {

        return bidAmount;
    }

    public void setBidAmount(BigDecimal amount) {

        this.bidAmount = amount;
    }

    public void sendBid(int auctionId) {

        TradeRequest tradeRequest = buildTradeRequest(auctionId, bidAmount, null);
        tradeFacade.fireTrader(tradeRequest);
    }

    public void sendBid(int auctionId, String bidAmount) {

        TradeRequest tradeRequest =
            buildTradeRequest(auctionId, new BigDecimal(bidAmount), null);
        tradeFacade.fireTrader(tradeRequest);
    }

    public void sendBid(int auctionId, CdaType cdaType) {

        TradeRequest tradeRequest =
            buildTradeRequest(auctionId, bidAmount, cdaType);
        tradeFacade.fireTrader(tradeRequest);
    }

    private TradeRequest buildTradeRequest(int auctionId, BigDecimal amount, CdaType cdaType) {

        Auction auction = auctionFacade.findById(auctionId);
        TradeRequest tradeRequest = new TradeRequest();
        tradeRequest.setAuction(auction);
        tradeRequest.setAmount(amount);
        tradeRequest.setUserId(currentUser.getUserId());

        if (null != cdaType) {
            tradeRequest.setCdaType(cdaType);
        }
        return tradeRequest;
    }
}

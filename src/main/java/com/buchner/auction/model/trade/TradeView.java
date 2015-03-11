package com.buchner.auction.model.trade;

import com.buchner.auction.model.core.bean.AuctionBean;
import com.buchner.auction.model.core.database.TradeFacade;
import com.buchner.auction.model.core.entity.AuctionType;

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

    private BigDecimal bidAmount;

    protected TradeView() {

    }

    public List<AuctionBean> getRunningUserAuctions(AuctionType auctionType) {

        return tradeFacade.getAuctionByBidderAndType(auctionType);
    }

    public void joinAuction(int articleId) {

        tradeFacade.addBidderToAuction(articleId);
    }

    public BigDecimal getBidAmount() {

        return bidAmount;
    }

    public void setBidAmount(BigDecimal bidAmount) {

        this.bidAmount = bidAmount;
    }

    public void sendBid(int auctionId) {

        tradeFacade.fireTrader(auctionId, bidAmount);
    }

    public void sendBid(int auctionId, String bidAmount) {

        tradeFacade.fireTrader(auctionId, new BigDecimal(bidAmount));
    }
}

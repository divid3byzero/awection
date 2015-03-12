package com.buchner.auction.model.core.trade;

import com.buchner.auction.model.core.app.BidComperator;
import com.buchner.auction.model.core.app.LiferayComponentService;
import com.buchner.auction.model.core.bean.TradeResultBean;
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


    @Override protected TradeResultBean trade(Auction auction, BigDecimal amount, long userId)
        throws PortalException, SystemException {

        return null;
    }

    @Override protected TradeResultBean findAuctionWinner(Auction auction, long userId)
        throws PortalException, SystemException {

        return null;
    }
}

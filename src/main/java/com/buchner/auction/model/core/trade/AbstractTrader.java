package com.buchner.auction.model.core.trade;

import com.buchner.auction.model.core.bean.TradeResultBean;
import com.buchner.auction.model.core.entity.Auction;
import com.buchner.auction.model.core.entity.AuctionResult;
import com.buchner.auction.model.core.entity.AuctionType;
import com.buchner.auction.model.core.entity.Bidder;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.model.User;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import java.math.BigDecimal;
import java.util.Date;

public abstract class AbstractTrader {

    protected AuctionType auctionType;


    public TradeResultBean handleTrade(Auction auction, BigDecimal amount, long userId)
        throws PortalException, SystemException {

        return trade(auction, amount, userId);
    }

    public AuctionType getAuctionType() {

        return auctionType;
    }

    protected void auctionMessage(String message) {

        FacesContext facesContext = FacesContext.getCurrentInstance();
        facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info",
            message));
    }

    protected boolean auctionTimeout(Auction auction) {

        DateTime now = new DateTime(DateTimeZone.forID("Europe/Berlin"));
        Date nowDate = now.toDate();
        return nowDate.compareTo(auction.getEndTime()) == 0
            || nowDate.compareTo(auction.getEndTime()) == 1 || !auction.isRunning();
    }


    protected abstract TradeResultBean trade(Auction auction, BigDecimal amount, long userId)
        throws SystemException, PortalException;

    protected abstract TradeResultBean findAuctionWinner(Auction auction, long userId)
        throws PortalException, SystemException;

}


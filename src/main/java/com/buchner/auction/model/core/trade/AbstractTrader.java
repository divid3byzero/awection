package com.buchner.auction.model.core.trade;

import com.buchner.auction.model.core.app.TradeRequest;
import com.buchner.auction.model.core.app.TradeResponse;
import com.buchner.auction.model.core.entity.Auction;
import com.buchner.auction.model.core.entity.AuctionType;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import java.util.Date;

public abstract class AbstractTrader {

    protected AuctionType auctionType;


    public TradeResponse handleTrade(TradeRequest tradeRequest)
        throws PortalException, SystemException {


        if (auctionTimeout(tradeRequest.getAuction())) {
            return handleTimeOut(tradeRequest);
        }
        return trade(tradeRequest);
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


    protected abstract TradeResponse trade(TradeRequest tradeRequest)
        throws SystemException, PortalException;

    protected abstract TradeResponse findAuctionWinner(TradeRequest tradeRequest)
        throws PortalException, SystemException;

    protected abstract TradeResponse handleTimeOut(TradeRequest tradeRequest)
        throws SystemException, PortalException;

}


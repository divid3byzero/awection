package com.buchner.auction.model.core.trade;

import com.buchner.auction.model.core.entity.Auction;
import com.buchner.auction.model.core.entity.AuctionType;
import com.buchner.auction.model.core.entity.Bidder;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.model.User;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import java.math.BigDecimal;

public abstract class AbstractTrader {

    protected AuctionType auctionType;


    public void handleTrade(Auction auction, BigDecimal amount, long userId)
        throws PortalException, SystemException {

        trade(auction, amount, userId);
    }

    public AuctionType getAuctionType() {

        return auctionType;
    }

    protected abstract void trade(Auction auction, BigDecimal amount, long userId)
        throws SystemException, PortalException;

    protected abstract void findAuctionWinner(Auction auction, long userId)
        throws PortalException, SystemException;

    protected void auctionMessage(String message) {

        FacesContext facesContext = FacesContext.getCurrentInstance();
        facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info",
            message));
    }
}


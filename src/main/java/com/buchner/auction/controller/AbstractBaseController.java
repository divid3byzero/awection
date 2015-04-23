package com.buchner.auction.controller;

import com.buchner.auction.model.core.app.AuctionForm;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

public abstract class AbstractBaseController {

    protected AbstractBaseController() {

    }

    protected void viewMessage(String message) {

        FacesContext facesContext = FacesContext.getCurrentInstance();
        facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", message));
    }

    protected void clearView(AuctionForm auctionForm) {

        auctionForm.clearView();
    }
}

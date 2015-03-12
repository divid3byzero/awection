package com.buchner.auction.model.core.app;

import com.buchner.auction.model.core.entity.CdaType;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;

@Named
@ApplicationScoped
public class ElCdaTypeWrapper {

    private CdaType buyer;
    private CdaType seller;

    protected ElCdaTypeWrapper() {
        buyer = CdaType.BUYER;
        seller = CdaType.SELLER;
    }

    public CdaType getBuyer() {

        return buyer;
    }

    public CdaType getSeller() {

        return seller;
    }
}

package com.buchner.auction.model.core.app;

import com.buchner.auction.model.core.entity.AuctionType;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;

@Named
@ApplicationScoped
public class ElAuctionTypeWrapper {

    AuctionType dutchType;
    AuctionType englishType;
    AuctionType secondPriceType;
    AuctionType cdaType;

    protected ElAuctionTypeWrapper() {

        dutchType = AuctionType.DUTCH;
        englishType = AuctionType.ENGLISH;
        secondPriceType = AuctionType.SECOND_PRICE;
        cdaType = AuctionType.CDA;
    }

    public AuctionType getDutchType() {

        return dutchType;
    }

    public AuctionType getEnglishType() {

        return englishType;
    }

    public AuctionType getSecondPriceType() {

        return secondPriceType;
    }

    public AuctionType getCdaType() {

        return cdaType;
    }
}

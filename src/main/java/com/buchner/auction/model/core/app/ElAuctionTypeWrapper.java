package com.buchner.auction.model.core.app;

import com.buchner.auction.model.core.entity.AuctionType;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;

/**
 * Wraps the auction type enum to make is accessible from Faceltes.
 */
@Named
@ApplicationScoped
public class ElAuctionTypeWrapper {

    AuctionType dutchType;
    AuctionType englishType;
    AuctionType secondPriceType;

    protected ElAuctionTypeWrapper() {

        dutchType = AuctionType.DUTCH;
        englishType = AuctionType.ENGLISH;
        secondPriceType = AuctionType.SECOND_PRICE;
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

}

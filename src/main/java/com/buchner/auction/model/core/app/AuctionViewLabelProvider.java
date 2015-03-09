package com.buchner.auction.model.core.app;

import com.buchner.auction.model.core.entity.AuctionType;

import javax.inject.Named;
import java.util.ArrayList;
import java.util.List;

@Named
public class AuctionViewLabelProvider {

    public AuctionViewLabelProvider() {

    }

    public List<String> getAuctionTypes() {

        List<String> auctionTypes = new ArrayList<>();
        for (AuctionType auctionType : AuctionType.values()) {
            auctionTypes.add(auctionType.getName());
        }
        return auctionTypes;
    }

    public List<String> getAuctionCategory() {

        List<String> auctionCategory = new ArrayList<>();
        auctionCategory.add("Electronics");
        auctionCategory.add("Household");
        auctionCategory.add("Clothing/Shoes");
        return auctionCategory;
    }
}

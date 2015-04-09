package com.buchner.auction.model.core.app;

import com.buchner.auction.model.core.entity.AuctionType;

import javax.inject.Named;
import java.util.ArrayList;
import java.util.List;

/**
 * Builds label lists for UI select menus of the article creation portlet.
 */
@Named
public class AuctionViewLabelProvider {

    public AuctionViewLabelProvider() {

    }

    /**
     * @return List of available auction types
     */
    public List<String> getAuctionTypes() {

        List<String> auctionTypes = new ArrayList<>();
        for (AuctionType auctionType : AuctionType.values()) {
            auctionTypes.add(auctionType.getName());
        }
        return auctionTypes;
    }

    /**
     * @return List of available article categories.
     */
    public List<String> getAuctionCategory() {

        List<String> auctionCategory = new ArrayList<>();
        auctionCategory.add("Electronics");
        auctionCategory.add("Household");
        auctionCategory.add("Clothing/Shoes");
        return auctionCategory;
    }
}

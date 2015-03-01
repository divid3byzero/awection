package com.buchner.awection.model.article;

import javax.inject.Named;
import java.util.ArrayList;
import java.util.List;

@Named
public class ArticleViewLabelProvider {

    public ArticleViewLabelProvider() {

    }

    public List<String> getAuctionTypes() {

        List<String> auctionTypes = new ArrayList<>();
        auctionTypes.add("English");
        auctionTypes.add("Dutch");
        auctionTypes.add("Simultaneous second price auction");
        auctionTypes.add("Continuous double auction");
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

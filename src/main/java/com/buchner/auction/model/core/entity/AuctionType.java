package com.buchner.auction.model.core.entity;

/**
 * Enum that defines the different types of auctions. An enum is used
 * to have type safety.
 */
public enum AuctionType {

    ENGLISH("English Auction", "english"),
    DUTCH("Dutch Auction", "dutch"),
    SECOND_PRICE("Second Price Auction", "second_price");

    private String name;
    private String identifier;

    private AuctionType(String name, String identifier) {

        this.name = name;
        this.identifier = identifier;
    }

    public String getName() {

        return name;
    }

    public String getIdentifier() {

        return identifier;
    }
}

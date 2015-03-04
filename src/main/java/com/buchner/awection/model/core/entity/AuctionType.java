package com.buchner.awection.model.core.entity;

public enum AuctionType {

    ENGLISH("English Auction", "english"),
    DUTCH("Dutch Auction", "dutch"),
    SECOND_PRICE("Second Price Auction", "second_price"),
    CDA("Continuous Double Auction", "cda");

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

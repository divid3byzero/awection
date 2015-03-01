package com.buchner.awection.model.internal;

public enum AuctionType {

    ENGLISH("english"),
    DUTCH("dutch"),
    SECOND_PRICE("second_price"),
    CDA("cda")
    ;

    private String name;

    private AuctionType(String name) {
        this.name = name;
    }

    public String getName() {

        return name;
    }
}

package com.buchner.auction.model.core.app;

/**
 * Represents CDA order sub types.
 */
public enum CdaOrderType {

    MARKET_ORDER("Market Order"),
    LIMIT_ORDER("Limit Order");


    private String name;

    private CdaOrderType(String name) {

        this.name = name;
    }

    public String getName() {

        return name;
    }
}

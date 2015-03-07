package com.buchner.awection.model.core.trade;

public abstract class AbstractAuctionTimer {

    protected int value;

    protected AbstractAuctionTimer(int value) {

        this.value = value;
    }

    public int getValue() {

        return value;
    }

    public abstract void reset();

    public abstract void count();
}

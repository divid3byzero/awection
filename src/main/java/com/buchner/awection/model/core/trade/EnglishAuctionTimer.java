package com.buchner.awection.model.core.trade;

public class EnglishAuctionTimer extends AbstractAuctionTimer {

    private static final int INITIAL_VALUE = 5;

    public EnglishAuctionTimer() {
        super(INITIAL_VALUE);
    }

    @Override public void reset() {

        value = INITIAL_VALUE;
    }

    @Override public void count() {

        try {
            while (value > 0) {
                Thread.sleep(1000);
                value -= 5;
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

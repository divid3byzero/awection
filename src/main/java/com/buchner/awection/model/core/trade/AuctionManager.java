package com.buchner.awection.model.core.trade;

import com.buchner.awection.model.core.entity.AuctionType;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import java.util.HashMap;
import java.util.Map;

@Named
@ApplicationScoped
public class AuctionManager {

    private static final Object semaphore = new Object();

    private int englishAuctionTimer;
    private int dutchAuctionTimer;
    private int seconPriceAuctionTimer;

    private Map<AuctionType, AbstractAuctionTimer> auctionTimers;

    protected AuctionManager() {

        auctionTimers = new HashMap<>();
        auctionTimers.put(AuctionType.ENGLISH, new EnglishAuctionTimer());
    }

    public void resetTimer(AuctionType auctionType) {

        synchronized (semaphore) {

            englishAuctionTimer = 5;
        }
    }

    public int getAuctionTimerValue(AuctionType auctionType) {

        return auctionTimers.get(auctionType).getValue();
    }
}

package com.buchner.auction.model.core.app;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public class AuctionManagerJob implements Job {

    @Override public void execute(JobExecutionContext jobExecutionContext)
        throws JobExecutionException {

        AuctionManager auctionManager = AuctionManager.getInstance();
        auctionManager.checkDutchAuctionPrices();
        auctionManager.checkSecondPriceTimeout();
    }
}

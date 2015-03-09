package com.buchner.awection.model.core.app;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public class DutchAuctionPriceJob implements Job {

    @Override public void execute(JobExecutionContext jobExecutionContext)
        throws JobExecutionException {

        AuctionManager auctionManager = new AuctionManager();
        auctionManager.setDutchAuctionPrice();
    }
}

package com.buchner.auction.model.core.app;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import javax.inject.Inject;

public class DutchAuctionPriceJob implements Job {



    @Override public void execute(JobExecutionContext jobExecutionContext)
        throws JobExecutionException {

        AuctionManager auctionManager = new AuctionManager();
        auctionManager.checkDutchAuctionPrices();
    }
}

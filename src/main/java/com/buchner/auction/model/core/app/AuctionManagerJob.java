package com.buchner.auction.model.core.app;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

/**
 * Quartz scheduler job that reduces prices of Dutch auctions
 * and checks for timeouts of second price auctions
 */
public class AuctionManagerJob implements Job {

    @Override public void execute(JobExecutionContext jobExecutionContext)
        throws JobExecutionException {

        EntityManager entityManager = Persistence.createEntityManagerFactory("auction").createEntityManager();
        entityManager.getTransaction().begin();
        AuctionManager auctionManager = new AuctionManager(entityManager);
        auctionManager.checkAuctions();
        entityManager.getTransaction().commit();
    }
}

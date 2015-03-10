package com.buchner.auction.model.core.database;

import com.buchner.auction.model.core.entity.AuctionResult;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;

@RequestScoped
public class AuctionResultDao {

    @Inject
    private EntityManager entityManager;

    protected AuctionResultDao() {

    }

    public void save(AuctionResult auctionResult) {

        entityManager.persist(auctionResult);
    }
}

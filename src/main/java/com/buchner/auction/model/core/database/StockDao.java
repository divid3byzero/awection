package com.buchner.auction.model.core.database;

import com.buchner.auction.model.core.entity.Stock;

import javax.inject.Inject;
import javax.persistence.EntityManager;

public class StockDao {

    @Inject
    private EntityManager entityManager;

    protected StockDao() {

    }

    public void save(Stock stock) {

        entityManager.persist(stock);
    }
}

package com.buchner.auction.model.core.database;

import javax.inject.Inject;
import javax.persistence.EntityManager;

public class OrderBookDao {

    @Inject
    private EntityManager entityManager;

    protected OrderBookDao() {

    }
}

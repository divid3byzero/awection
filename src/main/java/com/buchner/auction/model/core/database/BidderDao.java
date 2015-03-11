package com.buchner.auction.model.core.database;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;

@RequestScoped
public class BidderDao {

    @Inject
    private EntityManager entityManager;

    protected BidderDao() {

    }

    public void save(Object entity) {

        entityManager.persist(entity);
    }

}

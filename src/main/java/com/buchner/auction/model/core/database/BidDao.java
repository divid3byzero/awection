package com.buchner.auction.model.core.database;

import com.buchner.auction.model.core.entity.Bid;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

@RequestScoped
public class BidDao {

    @Inject
    private EntityManager entityManager;

    protected BidDao() {

    }

    public Bid getbyUserId(long userId) {

        TypedQuery<Bid> namedQuery = entityManager.createNamedQuery("Bid.getByUserId", Bid.class);
        namedQuery.setParameter("userId", userId);

        try {

            return namedQuery.getSingleResult();
        } catch (NoResultException e) {

            return null;
        }
    }
}

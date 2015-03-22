package com.buchner.auction.model.core.database;

import com.buchner.auction.model.core.entity.Bid;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import java.util.Collections;
import java.util.List;

@RequestScoped
public class BidDao {

    @Inject
    private EntityManager entityManager;

    protected BidDao() {

    }

    public List<Bid> getByBidderAndAuction(long userId, int auctionId) {

        TypedQuery<Bid> namedQuery = entityManager.createNamedQuery("Bid.getByAuction", Bid.class);
        namedQuery.setParameter("userId", userId);
        namedQuery.setParameter("auctionId", auctionId);

        try {

            return namedQuery.getResultList();
        } catch (NoResultException e) {

            return Collections.emptyList();
        }
    }
}

package com.buchner.auction.model.core.database;

import com.buchner.auction.model.core.entity.Auction;
import com.buchner.auction.model.core.entity.AuctionType;
import com.buchner.auction.model.core.entity.Bid;
import com.buchner.auction.model.core.entity.Bidder;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RequestScoped
public class BidderDao {

    @Inject
    private EntityManager entityManager;

    protected BidderDao() {

    }

    public void save(Bidder entity) {

        entityManager.persist(entity);
    }

    public Bidder findById(int id) {

        return entityManager.find(Bidder.class, id);
    }

    public Bidder findByUserId(long userId) {

        TypedQuery<Bidder> namedQuery =
            entityManager.createNamedQuery("Bidder.findBidByBidder", Bidder.class);
        namedQuery.setParameter("userId", userId);

        try {

            return namedQuery.getSingleResult();
        } catch (NoResultException e) {

            return null;
        }
    }

    public List<Auction> findByBidder(long userId) {

        TypedQuery<Auction> namedQuery = entityManager
            .createNamedQuery("Auction.byBidder", Auction.class);
        namedQuery.setParameter("userId", userId);
        return namedQuery.getResultList();
    }
}

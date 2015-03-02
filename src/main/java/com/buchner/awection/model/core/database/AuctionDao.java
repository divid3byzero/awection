package com.buchner.awection.model.core.database;

import com.buchner.awection.model.core.entity.Auction;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

@RequestScoped
public class AuctionDao {

    @Inject
    private EntityManager entityManager;

    // Needed for CDI
    protected AuctionDao() {

    }

    public void save(Auction auction) {

        entityManager.persist(auction);
    }

    public Object findById(int id, Class entityClass) {

        return entityManager.find(entityClass, id);
    }

    public List<Auction> findAllAuctionsByUserId(long userId) {

        TypedQuery<Auction> namedQuery = entityManager
            .createQuery("select au from Auction au where au.userId = :userId", Auction.class);
        namedQuery.setParameter("userId", userId);
        return namedQuery.getResultList();
    }

}

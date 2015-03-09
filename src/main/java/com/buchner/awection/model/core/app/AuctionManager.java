package com.buchner.awection.model.core.app;

import com.buchner.awection.model.core.database.Transaction;
import com.buchner.awection.model.core.entity.Auction;
import com.buchner.awection.model.core.entity.AuctionType;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.math.BigDecimal;

public class AuctionManager {

    private EntityManager entityManager;

    protected AuctionManager() {

        entityManager = Persistence.createEntityManagerFactory("auction").createEntityManager();
    }

    @Transaction
    @SuppressWarnings("JpaQlInspection")
    public void setDutchAuctionPrice() {


        TypedQuery<Auction> namedQuery = entityManager
            .createQuery("select au from Auction au where au.auctionType = :auctionType",
                Auction.class);
        namedQuery.setParameter("auctionType", AuctionType.DUTCH);

        namedQuery.getResultList().stream().filter(auction -> auction.getBidder().size() >= 1)
            .forEach(auction -> {
                auction.setPrice(
                    new BigDecimal(String.valueOf(auction.getPrice().subtract(new BigDecimal(2)))));
                entityManager.persist(auction);
            });

    }
}

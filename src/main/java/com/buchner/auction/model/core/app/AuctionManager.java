package com.buchner.auction.model.core.app;

import com.buchner.auction.model.core.database.Transaction;
import com.buchner.auction.model.core.entity.Auction;
import com.buchner.auction.model.core.entity.AuctionType;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.math.BigDecimal;
import java.util.List;

public class AuctionManager {

    private EntityManager entityManager;

    protected AuctionManager() {

        entityManager = Persistence.createEntityManagerFactory("auction").createEntityManager();
    }

    @Transaction
    @SuppressWarnings("JpaQlInspection")
    public void checkDutchAuctionPrices() {


        TypedQuery<Auction> namedQuery = entityManager
            .createQuery("select au from Auction au where au.auctionType = :auctionType",
                Auction.class);
        namedQuery.setParameter("auctionType", AuctionType.DUTCH);

        List<Auction> resultList = namedQuery.getResultList();
        resultList.stream().filter(auction -> auction.getBidder().size() > 1)
            .forEach(auction -> {
                auction.setPrice(
                    new BigDecimal(String.valueOf(auction.getPrice().subtract(new BigDecimal(2)))));
                entityManager.persist(auction);
            });

    }
}

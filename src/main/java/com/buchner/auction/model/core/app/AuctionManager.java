package com.buchner.auction.model.core.app;

import com.buchner.auction.model.core.entity.Auction;
import com.buchner.auction.model.core.entity.AuctionType;
import com.buchner.auction.model.core.entity.Bidder;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.math.BigDecimal;
import java.util.List;

public class AuctionManager {

    private EntityManager entityManager;
    private BigDecimal dutchAuctionSubtrahent;

    private static AuctionManager instance;

    private AuctionManager() {

        entityManager = Persistence.createEntityManagerFactory("auction").createEntityManager();
        dutchAuctionSubtrahent = new BigDecimal("2");
    }

    public static AuctionManager getInstance() {

        if (null == instance) {
            instance = new AuctionManager();
        }
        return instance;
    }

    @SuppressWarnings("JpaQlInspection")
    public void checkDutchAuctionPrices() {


        entityManager.getTransaction().begin();
        TypedQuery<Auction> namedQuery = entityManager
            .createQuery("select au from Auction au where au.auctionType = :auctionType",
                Auction.class);
        namedQuery.setParameter("auctionType", AuctionType.DUTCH);

        List<Auction> resultList = namedQuery.getResultList();
        for (Auction auction : resultList) {

            List<Bidder> bidder = auction.getBidder();
            if (bidder.size() > 1 && auction.getPrice().subtract(dutchAuctionSubtrahent).intValue() > 0) {
                auction.setPrice(
                    new BigDecimal(String.valueOf(auction.getPrice().subtract(dutchAuctionSubtrahent))));
                entityManager.getTransaction().commit();
            }
        }
        entityManager.close();
    }
}

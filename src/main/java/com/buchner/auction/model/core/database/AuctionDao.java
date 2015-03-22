package com.buchner.auction.model.core.database;

import com.buchner.auction.model.core.entity.Auction;
import com.buchner.auction.model.core.entity.AuctionType;
import com.buchner.auction.model.core.entity.Bidder;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * DAO to access and persist auctions in a database.
 */
@RequestScoped
public class AuctionDao {

    @Inject
    private EntityManager entityManager;

    protected AuctionDao() {

    }

    public void save(Auction auction) {

        entityManager.persist(auction);
    }

    public Auction findById(int id) {

        return entityManager.find(Auction.class, id);
    }

    public Auction findByArticle(int articleId) {

        TypedQuery<Auction> namedQuery = entityManager
            .createNamedQuery("Auction.findByArticle",
                Auction.class);
        namedQuery.setParameter("articleId", articleId);
        return namedQuery.getSingleResult();
    }

    public List<Auction> findAuctionFromBidderAndType(long userId, AuctionType auctionType) {

        TypedQuery<Auction> namedQuery = entityManager
            .createNamedQuery("Auction.fromBidderAndType", Auction.class);
        namedQuery.setParameter("auctionType", auctionType);
        namedQuery.setParameter("userId", userId);
        return namedQuery.getResultList();
    }

}

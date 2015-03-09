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

    public Auction findById(int id) {

        return entityManager.find(Auction.class, id);
    }

    public Auction findByArticle(int articleId) {

        TypedQuery<Auction> namedQuery = entityManager
            .createQuery("select au from Auction au where au.article.id = :articleId",
                Auction.class);
        namedQuery.setParameter("articleId", articleId);
        return namedQuery.getSingleResult();
    }

    public List<Auction> findByBidder(long userId) {

        TypedQuery<Bidder> namedQuery = entityManager
            .createQuery("select b from Bidder b where b.userId = :userId", Bidder.class);
        namedQuery.setParameter("userId", userId);
        List<Bidder> resultList = namedQuery.getResultList();

        List<Auction> userAuctions = new ArrayList<>();
        for (Bidder bidder : resultList) {
            userAuctions.addAll(bidder.getAuctions().stream().collect(Collectors.toList()));
        }
        return userAuctions;
    }

    public List<Auction> findAuctionFromBidderAndType(long userId, AuctionType auctionType) {

        TypedQuery<Bidder> namedQuery = entityManager
            .createQuery(
                "select b from Bidder b where b.userId = :userId",
                Bidder.class);
        namedQuery.setParameter("userId", userId);
        List<Bidder> resultList = namedQuery.getResultList();

        List<Auction> auctionList = new ArrayList<>();
        for (Bidder bidder : resultList) {

            auctionList.addAll(bidder.getAuctions().stream().filter(
                auction -> auctionType.equals(auction.getAuctionType()) && auction.isRunning())
                .collect(Collectors.toList()));
        }

        return auctionList;
    }

}

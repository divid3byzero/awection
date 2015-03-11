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
public class BidderDao extends GenericDao<Bidder> {

    public BidderDao() {

    }

    @Inject
    protected BidderDao(EntityManager entityManager) {
        super(entityManager, Bidder.class);
    }

    public List<Auction> findByBidder(long userId) {

        TypedQuery<Bidder> namedQuery = entityManager
            .createQuery("select b from Bidder b where b.userId = :userId", entityClass);
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
                entityClass);
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

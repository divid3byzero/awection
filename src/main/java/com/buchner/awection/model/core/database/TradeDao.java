package com.buchner.awection.model.core.database;

import com.buchner.awection.model.core.entity.Auction;
import com.buchner.awection.model.core.entity.AuctionType;
import com.buchner.awection.model.core.entity.Bidder;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.stream.Collectors;

public class TradeDao {

    @Inject
    private EntityManager entityManager;

    public TradeDao() {

    }

    public void save(Object entity) {

        entityManager.persist(entity);
    }

    public List<Auction> findAuctionFromBidderAndType(long userId, AuctionType auctionType) {

        TypedQuery<Bidder> namedQuery = entityManager
            .createQuery(
                "select b from Bidder b where b.userId = :userId and b.auction.auctionType = :auctionType",
                Bidder.class);
        namedQuery.setParameter("userId", userId);
        namedQuery.setParameter("auctionType", auctionType);
        List<Bidder> bidderList = namedQuery.getResultList();

        List<Auction> collect = bidderList.stream()
            .filter(bidder -> bidder.getAuction().getAuctionType().equals(auctionType))
            .collect(Collectors.toList())
            .stream()
            .filter(filteredBidder -> filteredBidder.getAuction().isRunning())
            .map(Bidder::getAuction)
            .collect(Collectors.toList());
        return collect;
    }


}

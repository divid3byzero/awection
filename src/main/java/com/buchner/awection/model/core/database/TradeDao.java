package com.buchner.awection.model.core.database;

import com.buchner.awection.model.core.entity.Auction;
import com.buchner.awection.model.core.entity.AuctionType;
import com.buchner.awection.model.core.entity.Bidder;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.stream.Collectors;

@RequestScoped
public class TradeDao {

    @Inject
    private EntityManager entityManager;

    protected TradeDao() {

    }

    public void save(Object entity) {

        entityManager.persist(entity);
    }

    public List<Auction> findAuctionFromBidderAndType(long userId, AuctionType auctionType) {

        TypedQuery<Bidder> namedQuery = entityManager
            .createQuery(
                "select b from Bidder b where b.userId = :userId",
                Bidder.class);
        namedQuery.setParameter("userId", userId);
        Bidder bidder = namedQuery.getSingleResult();

        return bidder.getAuctions().stream()
            .filter(auction -> auctionType.equals(auction.getAuctionType()) && auction.isRunning())
            .collect(Collectors.toList());
    }
}

package com.buchner.auction.model.core.database;

import com.buchner.auction.model.core.entity.Auction;
import com.buchner.auction.model.core.entity.AuctionType;
import com.buchner.auction.model.core.entity.Bid;
import com.buchner.auction.model.core.entity.Bidder;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
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
}

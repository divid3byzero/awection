package com.buchner.auction.model.core.database;

import com.buchner.auction.model.core.entity.AuctionResult;
import com.buchner.auction.model.core.entity.AuctionType;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.stream.Collectors;

@RequestScoped
public class AuctionResultDao {

    @Inject
    private EntityManager entityManager;

    protected AuctionResultDao() {

    }

    public void save(AuctionResult auctionResult) {

        entityManager.persist(auctionResult);
    }

    @SuppressWarnings("JpaQlInspection")
    public List<AuctionResult> findAuctionResultsByType(AuctionType auctionType) {

        TypedQuery<AuctionResult> query = entityManager
            .createQuery("select ar from AuctionResult ar",
                AuctionResult.class);
        List<AuctionResult> resultList = query.getResultList();
        return resultList.stream().filter(a -> a.getAuctionType().ordinal() == auctionType.ordinal()).collect(
            Collectors.toList());
    }
}

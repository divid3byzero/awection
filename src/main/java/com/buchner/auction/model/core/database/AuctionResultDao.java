package com.buchner.auction.model.core.database;

import com.buchner.auction.model.core.entity.AuctionResult;
import com.buchner.auction.model.core.entity.AuctionType;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

@RequestScoped
public class AuctionResultDao extends GenericDao<AuctionResult> {

    protected AuctionResultDao() {

    }

    @Inject
    public AuctionResultDao(EntityManager entityManager) {

        super(entityManager, AuctionResult.class);
    }

    @SuppressWarnings("JpaQlInspection")
    public List<AuctionResult> findAuctionResultsByType(AuctionType auctionType) {

        TypedQuery<AuctionResult> query = entityManager
            .createQuery("select ar from AuctionResult ar where ar.auctionType = :auctionType",
                AuctionResult.class);
        query.setParameter("auctionType", auctionType);
        return query.getResultList();
    }
}

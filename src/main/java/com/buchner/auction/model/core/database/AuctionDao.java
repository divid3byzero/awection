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
public class AuctionDao extends GenericDao<Auction> {

    protected AuctionDao() {

    }

    @Inject
    protected AuctionDao(EntityManager entityManager) {

        super(entityManager, Auction.class);
    }


    public Auction findByArticle(int articleId) {

        TypedQuery<Auction> namedQuery = entityManager
            .createQuery("select au from Auction au where au.article.id = :articleId",
                entityClass);
        namedQuery.setParameter("articleId", articleId);
        return namedQuery.getSingleResult();
    }

}

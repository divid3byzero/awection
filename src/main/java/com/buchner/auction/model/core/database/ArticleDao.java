package com.buchner.auction.model.core.database;

import com.buchner.auction.model.core.entity.Article;
import com.buchner.auction.model.core.entity.Auction;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.util.List;

/**
 * DAO to access and persist articles in a database.
 */
@RequestScoped
public class ArticleDao {

    @Inject
    private EntityManager entityManager;

    protected ArticleDao() {

    }

    public void save(Article article) {

        entityManager.persist(article);
    }

    public Article findById(int id) {

        return entityManager.find(Article.class, id);
    }

    @SuppressWarnings("JpaQueryApiInspection")
    public List<Article> findByName(String description) {

        TypedQuery<Article> namedQuery =
            entityManager.createNamedQuery("Article.byDescription", Article.class);
        namedQuery.setParameter("description", "%" + description + "%");
        return namedQuery.getResultList();
    }

    @SuppressWarnings("JpaQueryApiInspection")
    public List<Article> getArticelsByUserId(long userId) {

        TypedQuery<Article> namedQuery = entityManager
            .createNamedQuery("Article.byUserId", Article.class);
        namedQuery.setParameter("userId", userId);
        return namedQuery.getResultList();
    }
}

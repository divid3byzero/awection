package com.buchner.auction.model.core.database;

import com.buchner.auction.model.core.entity.Article;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.util.List;

@RequestScoped
public class ArticleDao extends GenericDao<Article> {

    public ArticleDao() {

    }

    @Inject
    protected ArticleDao(EntityManager entityManager) {

        super(entityManager, Article.class);
    }

    public List<Article> findByName(String description) {

        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Article> query = cb.createQuery(entityClass);
        Root<Article> article = query.from(entityClass);

        ParameterExpression<String> descParam =
            cb.parameter(String.class, "description");

        Predicate or = cb.or(cb.like(article.get("shortDesc"), descParam),
            cb.like(article.get("longDesc"), descParam));
        query.select(article).where(or);

        TypedQuery<Article> typedQuery = entityManager.createQuery(query);
        typedQuery.setParameter("description", "%" + description + "%");

        return typedQuery.getResultList();
    }

    public List<Article> getArticelsByUserId(long userId) {

        TypedQuery<Article> namedQuery = entityManager
            .createQuery("select a from Article a where a.userId = :userId", entityClass);
        namedQuery.setParameter("userId", userId);
        return namedQuery.getResultList();
    }
}

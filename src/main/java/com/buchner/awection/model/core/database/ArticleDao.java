package com.buchner.awection.model.core.database;

import com.buchner.awection.model.core.entity.Article;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

@RequestScoped
public class ArticleDao {

    @Inject
    private EntityManager entityManager;

    protected ArticleDao() {

    }

    public void save(Article article) {

        entityManager.persist(article);
    }

    public List<Article> getArticelsByUserId(long userId) {

        TypedQuery<Article> namedQuery = entityManager
            .createQuery("select a from Article a where a.userId = :userId", Article.class);
        namedQuery.setParameter("userId", userId);
        return namedQuery.getResultList();
    }
}

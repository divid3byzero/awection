package com.buchner.auction.model.core.database;

import com.buchner.auction.model.core.entity.Article;

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

    public Article findById(int id) {

        TypedQuery<Article> namedQuery = entityManager
            .createQuery("select a from Article a where a.id = :id", Article.class);
        namedQuery.setParameter("id", id);
        return namedQuery.getSingleResult();
    }

    public List<Article> findByName(String description) {

        TypedQuery<Article> namedQuery = entityManager
            .createQuery(
                "select a from Article a where a.shortDesc like :description or a.longDesc like :description",
                Article.class);
        namedQuery.setParameter("description", "%" + description + "%");
        return namedQuery.getResultList();
    }

    public List<Article> getArticelsByUserId(long userId) {

        TypedQuery<Article> namedQuery = entityManager
            .createQuery("select a from Article a where a.userId = :userId", Article.class);
        namedQuery.setParameter("userId", userId);
        return namedQuery.getResultList();
    }
}

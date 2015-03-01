package com.buchner.awection.database;

import com.buchner.awection.model.internal.entity.Article;
import com.buchner.awection.model.internal.entity.ArticleEntity;

import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;

public class EntityService {

    private EntityManager entityManager;

    public EntityService() {

        this.entityManager = Persistence.createEntityManagerFactory("auction").createEntityManager();
    }


    @Produces
    @ArticleEntity
    public AwectionDao<Article> produceArticleDao() {

        return new AwectionDao<>(entityManager, Article.class);
    }

}

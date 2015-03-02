package com.buchner.awection.model.core.database;

import com.buchner.awection.model.core.entity.Article;
import com.buchner.awection.model.core.entity.ArticleDao;

import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;

public class EntityService {

    private EntityManager entityManager;

    public EntityService() {

        this.entityManager = Persistence.createEntityManagerFactory("auction").createEntityManager();
    }


    @Produces
    @ArticleDao
    public AwectionDao<Article> produceArticleDao() {

        return new AwectionDao<>(entityManager, Article.class);
    }

}

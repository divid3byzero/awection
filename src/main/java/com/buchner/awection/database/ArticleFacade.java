package com.buchner.awection.database;

import com.buchner.awection.model.internal.entity.Article;
import com.buchner.awection.model.internal.entity.ArticleEntity;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

@RequestScoped
public class ArticleFacade {

    @Inject
    @ArticleEntity
    private AwectionDao<Article> articleDao;

    public ArticleFacade() {

    }

    public void createArticle(Article article) {

        articleDao.save(article);
    }
}

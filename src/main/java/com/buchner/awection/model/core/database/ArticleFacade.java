package com.buchner.awection.model.core.database;

import com.buchner.awection.model.core.entity.Article;
import com.buchner.awection.model.core.entity.ArticleDao;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

@RequestScoped
public class ArticleFacade {

    @Inject
    @ArticleDao
    private AwectionDao<Article> articleDao;

    public ArticleFacade() {

    }

    public void createArticle(Article article) {

        articleDao.save(article);
    }
}

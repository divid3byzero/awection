package com.buchner.awection.controller.article;

import com.buchner.awection.model.article.ArticleSearchView;
import com.buchner.awection.model.article.AddArticleView;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@ApplicationScoped
public class ArticleController {

    @Inject
    private AddArticleView addArticleView;

    @Inject
    private ArticleSearchView articleSearchView;

    public ArticleController() {

    }

    public void saveArticle() {

        addArticleView.buildAuctionWithArticle();
    }

    public void searchArticlesByName() {

        articleSearchView.getArticlesByName();
    }
}

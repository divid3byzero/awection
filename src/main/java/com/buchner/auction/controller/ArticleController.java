package com.buchner.auction.controller;

import com.buchner.auction.model.article.ArticleSearchForm;
import com.buchner.auction.model.article.AddArticleForm;
import com.buchner.auction.model.core.app.AuctionSession;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;


/**
 * Controller class to save articles and find them per name
 */
@Named
@ApplicationScoped
public class ArticleController extends AbstractBaseController {

    @Inject
    private AddArticleForm addArticleView;

    @Inject
    private ArticleSearchForm articleSearchView;

    @Inject
    private AuctionSession auctionSession;

    protected ArticleController() {

    }

    public void saveArticle() {

        addArticleView.buildAuctionWithArticle();

        viewMessage("Article added");
    }

    public void searchArticlesByName() {

        articleSearchView.getArticlesByName();
    }

}

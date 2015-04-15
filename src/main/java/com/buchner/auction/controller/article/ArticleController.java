package com.buchner.auction.controller.article;

import com.buchner.auction.model.article.ArticleSearchView;
import com.buchner.auction.model.article.AddArticleView;
import com.buchner.auction.model.core.app.AuctionSession;
import com.buchner.auction.model.core.app.TradingType;

import javax.enterprise.context.ApplicationScoped;
import javax.faces.event.ValueChangeEvent;
import javax.inject.Inject;
import javax.inject.Named;


/**
 * Controller class to save articles and find them per name
 */
@Named
@ApplicationScoped
public class ArticleController {

    @Inject
    private AddArticleView addArticleView;

    @Inject
    private ArticleSearchView articleSearchView;

    @Inject
    private AuctionSession auctionSession;

    public ArticleController() {

    }

    public void saveArticle() {

        addArticleView.buildAuctionWithArticle();
    }

    public void searchArticlesByName() {

        articleSearchView.getArticlesByName();
    }

    public void onTradingTypeSelect() {

        TradingType tradingType = addArticleView.getTradingType();
        auctionSession.setTradingType(tradingType);
    }
}

package com.buchner.awection.controller.auction;

import com.buchner.awection.model.auction.ArticleSearchView;
import com.buchner.awection.model.auction.AuctionView;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@ApplicationScoped
public class AuctionController {

    @Inject
    private AuctionView auctionView;

    @Inject
    private ArticleSearchView articleSearchView;

    public AuctionController() {

    }

    public void saveAuction() {

        auctionView.buildAuctionWithArticle();
    }

    public void searchArticlesByName() {

        articleSearchView.getArticlesByName();
    }

}

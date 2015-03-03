package com.buchner.awection.model.core.database;

import com.buchner.awection.model.auction.ArticleBean;
import com.buchner.awection.model.auction.AuctionBean;
import com.buchner.awection.model.core.entity.Article;
import com.buchner.awection.model.core.entity.Auction;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Transaction
@RequestScoped
public class AuctionFacade {

    @Inject
    private AuctionDao auctionDao;

    @Inject
    private ArticleDao articleDao;

    public AuctionFacade() {

    }

    public void createAuction(Auction auction) {

        articleDao.save(auction.getArticle());
        auctionDao.save(auction);
    }

    public List<AuctionBean> getUserOwnedAuctions(long userId) {

        List<Auction> allAuctionsByUserId = auctionDao.findAllAuctionsByUserId(userId);
        return buildAuctionBeans(allAuctionsByUserId);
    }

    public List<ArticleBean> getUserArticles(long userId) {

        List<Article> articlesByUserId = articleDao.getArticelsByUserId(userId);
        return buildArticleBeans(articlesByUserId);
    }

    public List<ArticleBean> getArticlesByDescription(String description) {

        List<Article> articlesByDescription = articleDao.findByName(description);
        return buildArticleBeans(articlesByDescription);
    }

    public Auction getAuctionFromArticle(int articleId) {

        return auctionDao.findByArticle(articleId);
    }

    public Article getSingleArticle(int id) {

        return articleDao.findById(id);
    }

    private List<ArticleBean> buildArticleBeans(List<Article> articleEntities) {

        return articleEntities.stream().map(
            article -> new ArticleBean(article.getId(), article.getImage(), article.getShortDesc(),
                article.getCategory(),
                article.getPrice())).collect(Collectors.toList());
    }

    private List<AuctionBean> buildAuctionBeans(List<Auction> allAuctionsByUserId) {

        return allAuctionsByUserId.stream().map(
            auction -> new AuctionBean(auction.getAuctionType(),
                auction.getArticle().getShortDesc(),
                auction.getPrice(), auction.isRunning())).collect(Collectors.toList());
    }
}

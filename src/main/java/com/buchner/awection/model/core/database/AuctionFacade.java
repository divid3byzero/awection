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
        List<AuctionBean> auctionBeans = new ArrayList<>();
        for (Auction auction : allAuctionsByUserId) {
            auctionBeans.add(
                new AuctionBean(auction.getAuctionType(), auction.getArticle().getShortDesc(),
                    auction.getPrice(), auction.isRunning()));
        }
        return auctionBeans;
    }

    public List<ArticleBean> getUserArticles(long userId) {

        List<Article> articelsByUserId = articleDao.getArticelsByUserId(userId);
        List<ArticleBean> articleBeans = new ArrayList<>();
        for (Article article : articelsByUserId) {
            articleBeans.add(
                new ArticleBean(article.getId(), article.getImage(), article.getShortDesc(), article.getCategory(),
                    article.getPrice()));
        }
        return articleBeans;
    }

}

package com.buchner.awection.model.core.database;

import com.buchner.awection.model.core.bean.ArticleBean;
import com.buchner.awection.model.core.bean.AuctionBean;
import com.buchner.awection.model.core.app.BeanService;
import com.buchner.awection.model.core.entity.Article;
import com.buchner.awection.model.core.entity.Auction;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import java.util.List;

@Transaction
@RequestScoped
public class AuctionFacade {

    @Inject
    private AuctionDao auctionDao;

    @Inject
    private ArticleDao articleDao;

    @Inject
    private BeanService beanService;

    public AuctionFacade() {

    }

    public void createAuction(Auction auction) {

        articleDao.save(auction.getArticle());
        auctionDao.save(auction);
    }

    public List<AuctionBean> getUserOwnedAuctions(long userId) {

        List<Auction> allAuctionsByUserId = auctionDao.findAllAuctionsByUserId(userId);
        return beanService.buildAuctionBeans(allAuctionsByUserId);
    }

    public List<ArticleBean> getUserArticles(long userId) {

        List<Article> articlesByUserId = articleDao.getArticelsByUserId(userId);
        return beanService.buildArticleBeans(articlesByUserId);
    }

    public List<ArticleBean> getArticlesByDescription(String description) {

        List<Article> articlesByDescription = articleDao.findByName(description);
        return beanService.buildArticleBeans(articlesByDescription);
    }

    public Auction getAuctionFromArticle(int articleId) {

        return auctionDao.findByArticle(articleId);
    }

    public Article getSingleArticle(int id) {

        return articleDao.findById(id);
    }
}

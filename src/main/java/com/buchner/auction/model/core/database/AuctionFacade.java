package com.buchner.auction.model.core.database;

import com.buchner.auction.model.core.bean.ArticleBean;
import com.buchner.auction.model.core.bean.AuctionBean;
import com.buchner.auction.model.core.app.BeanService;
import com.buchner.auction.model.core.entity.Article;
import com.buchner.auction.model.core.entity.Auction;
import com.buchner.auction.model.core.entity.Bidder;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Facade class that represents a "border" between view code
 * and domain logic. Due to its @Transaction annotation it is
 * able to behave almost like an EJB class. This annotation activates an interceptor
 * that will open and close transactions before respectively close them after method
 * calls. This class deals with all requests concerning the retrieval and creation of auctions. It also converts entity classes
 * to bean classes. It holds references via @Inject of all domain classes that offer services that have to
 * be executed in a transaction.
 */
@Transaction
@RequestScoped
public class AuctionFacade {

    @Inject
    private AuctionDao auctionDao;

    @Inject
    private ArticleDao articleDao;

    @Inject
    private BidderDao bidderDao;

    @Inject
    private BeanService beanService;

    public AuctionFacade() {

    }

    public void createAuction(Auction auction) {

        articleDao.save(auction.getArticle());
        auctionDao.save(auction);
    }

    public Auction findById(int auctionId) {

        return auctionDao.findById(auctionId);
    }

    public List<AuctionBean> getUserBidderAuctions(long userId) {

        List<Auction> allAuctionsByUserId = bidderDao.findByBidder(userId);
        return beanService.buildAuctionBeans(allAuctionsByUserId);
    }

    public List<ArticleBean> getUserArticles(long userId) {

        List<Article> articlesByUserId = articleDao.getArticelsByUserId(userId);
        return beanService.buildArticleBeans(articlesByUserId);
    }

    public List<ArticleBean> getArticlesByDescription(String description, long userId) {

        List<Article> articlesByDescription = articleDao.findByName(description, userId);
        List<Article> filteredArticles = new ArrayList<>();
        for (Article article : articlesByDescription) {

            List<Bidder> bidder = article.getAuction().getBidder();
            if (bidder.size() == 0) {
                filteredArticles.add(article);
                continue;
            }

            filteredArticles.addAll(
                    bidder.stream().filter(bidderElem -> userId != bidderElem.getUserId())
                            .map(bidderElem -> article).collect(Collectors.toList()));
        }
        return beanService.buildArticleBeans(filteredArticles);
    }

    public Auction getAuctionFromArticle(int articleId) {

        return auctionDao.findByArticle(articleId);
    }

    public Article getSingleArticle(int id) {

        return articleDao.findById(id);
    }
}

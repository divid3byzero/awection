package com.buchner.awection.model.auction;

import com.buchner.awection.model.core.database.AuctionFacade;
import com.buchner.awection.model.core.entity.Article;
import com.buchner.awection.model.core.entity.Auction;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;

@Named
@RequestScoped
public class ArticleSearchView {

    @Inject
    private AuctionFacade auctionFacade;

    private String articleName;
    private List<ArticleBean> articleBeans;

    protected ArticleSearchView() {

    }

    public void getArticlesByName() {

        articleBeans = auctionFacade.getArticlesByDescription(articleName);
    }

    public String getArticleName() {

        return articleName;
    }

    public void setArticleName(String articleName) {

        this.articleName = articleName;
    }

    public List<ArticleBean> getArticleBeans() {

        return articleBeans;
    }

    public Auction getAuctionFromArticle(int articleId) {

        Auction auctionFromArticle = auctionFacade.getAuctionFromArticle(articleId);
        return auctionFromArticle;
    }

    public void setArticleBeans(List<ArticleBean> articleBeans) {

        this.articleBeans = articleBeans;
    }
}

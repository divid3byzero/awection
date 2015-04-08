package com.buchner.auction.model.core.app;

import com.buchner.auction.model.core.bean.ArticleBean;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

/**
 * Session bean to hold search results after a user has searched for
 * an article.
 */
@Named
@SessionScoped
public class AuctionSession implements Serializable {

    private List<ArticleBean> articleSearchResult;

    protected AuctionSession() {

    }

    public List<ArticleBean> getArticleSearchResult() {

        return articleSearchResult;
    }

    public void setArticleSearchResult(List<ArticleBean> articleSearchResult) {

        this.articleSearchResult = articleSearchResult;
    }

    public void clearSearchResults() {

        articleSearchResult.clear();
    }
}

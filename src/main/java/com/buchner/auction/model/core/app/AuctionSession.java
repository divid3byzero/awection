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

    private CdaOrderType cdaOrderType;

    private TradingType tradingType;

    protected AuctionSession() {

    }

    public List<ArticleBean> getArticleSearchResult() {

        return articleSearchResult;
    }

    public void setArticleSearchResult(List<ArticleBean> articleSearchResult) {

        this.articleSearchResult = articleSearchResult;
    }

    public CdaOrderType getCdaOrderType() {

        return cdaOrderType;
    }

    public void setCdaOrderType(CdaOrderType cdaOrderType) {

        this.cdaOrderType = cdaOrderType;
    }

    public TradingType getTradingType() {

        return tradingType;
    }

    public void setTradingType(TradingType tradingType) {

        this.tradingType = tradingType;
    }

    public void clearSearchResults() {

        articleSearchResult.clear();
    }
}

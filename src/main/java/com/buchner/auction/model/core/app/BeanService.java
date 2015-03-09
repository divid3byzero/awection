package com.buchner.auction.model.core.app;

import com.buchner.auction.model.core.bean.ArticleBean;
import com.buchner.auction.model.core.bean.AuctionBean;
import com.buchner.auction.model.core.entity.Article;
import com.buchner.auction.model.core.entity.Auction;

import javax.enterprise.context.ApplicationScoped;
import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
public class BeanService {

    protected BeanService() {

    }

    public List<ArticleBean> buildArticleBeans(List<Article> articleEntities) {

        return articleEntities.stream().map(
            article -> new ArticleBean(article.getId(), article.getImage(), article.getShortDesc(),
                article.getCategory(),
                article.getPrice())).collect(Collectors.toList());
    }

    public ArticleBean buildAricleBean(Article articleEntity) {

        return new ArticleBean(articleEntity.getId(), articleEntity.getImage(), articleEntity.getShortDesc(),
            articleEntity.getCategory(),
            articleEntity.getPrice());
    }

    public List<AuctionBean> buildAuctionBeans(List<Auction> allAuctionsByUserId) {

        return allAuctionsByUserId.stream().map(
            this::buildAuctionBean).collect(Collectors.toList());
    }

    public AuctionBean buildAuctionBean(Auction auctionEntity) {

        return new AuctionBean(auctionEntity.getId(), auctionEntity.getArticle().getId(), auctionEntity.getAuctionType(),
            auctionEntity.getArticle().getShortDesc(),
            auctionEntity.getPrice(), auctionEntity.isRunning());
    }

}

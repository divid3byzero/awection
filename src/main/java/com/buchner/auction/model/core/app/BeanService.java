package com.buchner.auction.model.core.app;

import com.buchner.auction.model.core.bean.ArticleBean;
import com.buchner.auction.model.core.bean.AuctionBean;
import com.buchner.auction.model.core.bean.BidBean;
import com.buchner.auction.model.core.entity.Article;
import com.buchner.auction.model.core.entity.Auction;
import com.buchner.auction.model.core.entity.AuctionResult;
import com.buchner.auction.model.core.entity.Bid;

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

        return new ArticleBean(articleEntity.getId(), articleEntity.getImage(),
                articleEntity.getShortDesc(),
                articleEntity.getCategory(),
                articleEntity.getPrice());
    }

    public List<AuctionBean> buildAuctionBeans(List<Auction> allAuctionsByUserId) {

        return allAuctionsByUserId.stream().map(
                this::buildAuctionBean).collect(Collectors.toList());
    }

    public List<TradeResponse> buildAuctionResultBeans(List<AuctionResult> auctionResults) {

        return auctionResults.stream().map(
                this::buildAuctionResultBean).collect(Collectors.toList());
    }

    public AuctionBean buildAuctionBean(Auction auctionEntity) {

        return new AuctionBean(auctionEntity.getId(), auctionEntity.getArticle().getId(),
                auctionEntity.getAuctionType(),
                auctionEntity.getArticle().getShortDesc(),
                auctionEntity.getPrice(), auctionEntity.isRunning());
    }

    public TradeResponse buildAuctionResultBean(AuctionResult auctionResult) {

        TradeResponse tradeResponse = new TradeResponse();
        tradeResponse.setDescription(auctionResult.getDescription());
        tradeResponse.setPrice(auctionResult.getPrice());
        tradeResponse.setFirstName(auctionResult.getFirstName());
        tradeResponse.setSurname(auctionResult.getSurname());
        tradeResponse.setMail(auctionResult.getMail());
        return tradeResponse;
    }

    public List<BidBean> buildBidBeans(List<Bid> bids) {

        return bids.stream().map(this::buildBidBean).collect(Collectors.toList());
    }

    public BidBean buildBidBean(Bid bid) {

        return new BidBean(bid.getId(), bid.getAuctionId(), bid.getAmount());
    }

}

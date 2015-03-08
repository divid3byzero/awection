package com.buchner.awection.model.article;

import com.buchner.awection.model.core.app.AuctionSession;
import com.buchner.awection.model.core.bean.ArticleBean;
import com.buchner.awection.model.core.database.AuctionFacade;
import com.buchner.awection.model.core.entity.Auction;
import com.liferay.portal.model.User;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;

@Named
@RequestScoped
public class ArticleSearchView {

    @Inject
    private AuctionFacade auctionFacade;

    @Inject
    private AuctionSession auctionSession;

    @Inject
    private User currentUser;

    private String articleName;

    protected ArticleSearchView() {

    }

    public void getArticlesByName() {

        List<ArticleBean> articlesByDescription =
            auctionFacade.getArticlesByDescription(articleName, currentUser.getUserId());
        auctionSession.setArticleSearchResult(articlesByDescription);
    }

    public String getArticleName() {

        return articleName;
    }

    public void setArticleName(String articleName) {

        this.articleName = articleName;
    }

    public Auction getAuctionFromArticle(int articleId) {

        return auctionFacade.getAuctionFromArticle(articleId);
    }
}

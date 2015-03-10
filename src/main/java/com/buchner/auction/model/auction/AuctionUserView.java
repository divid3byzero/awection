package com.buchner.auction.model.auction;

import com.buchner.auction.model.core.bean.ArticleBean;
import com.buchner.auction.model.core.bean.AuctionBean;
import com.buchner.auction.model.core.database.AuctionFacade;
import com.liferay.portal.model.User;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;

@Named
@RequestScoped
public class AuctionUserView {

    @Inject
    private User currentUser;

    @Inject
    private AuctionFacade auctionFacade;

    public AuctionUserView() {

    }

    public List<AuctionBean> getUserOwnedAuctions() {

        return auctionFacade.getUserBidderAuctions(currentUser.getUserId());
    }

    public List<ArticleBean> getUserArticles() {

        return auctionFacade.getUserArticles(currentUser.getUserId());
    }
}
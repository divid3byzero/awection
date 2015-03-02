package com.buchner.awection.model.auction;

import com.buchner.awection.model.core.database.AuctionFacade;
import com.buchner.awection.model.core.entity.Auction;
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

        return auctionFacade.getUserOwnedAuctions(currentUser.getUserId());
    }

    public List<ArticleBean> getUserArticles() {

        return auctionFacade.getUserArticles(currentUser.getUserId());
    }
}

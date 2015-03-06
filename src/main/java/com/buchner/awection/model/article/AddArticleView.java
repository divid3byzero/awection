package com.buchner.awection.model.article;

import com.buchner.awection.model.core.entity.AuctionType;
import com.buchner.awection.model.core.database.AuctionFacade;
import com.buchner.awection.model.core.entity.Article;
import com.buchner.awection.model.core.entity.Auction;
import com.liferay.portal.model.User;
import org.primefaces.model.UploadedFile;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.math.BigDecimal;

@Named
@RequestScoped
public class AddArticleView {

    private AuctionType auctionType;
    private String articleCategory;
    private String shortDescription;
    private String longDescription;
    private UploadedFile uploadedFile;
    private String startPrice;

    @Inject
    private AuctionFacade auctionFacade;

    @Inject
    private User currentUser;

    public AddArticleView() {

    }

    public void buildAuctionWithArticle() {

        Auction auction = createAuction();
        auctionFacade.createAuction(auction);
    }

    public String getAuctionType() {

        if (null != auctionType) {
            return auctionType.getName();
        }
        return "";
    }

    public void setAuctionType(String auctionType) {

        this.auctionType = findAuctionTypeFromName(auctionType);
    }

    public String getArticleCategory() {

        return articleCategory;
    }

    public void setArticleCategory(String articleCategory) {

        this.articleCategory = articleCategory;
    }

    public String getShortDescription() {

        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {

        this.shortDescription = shortDescription;
    }

    public String getLongDescription() {

        return longDescription;
    }

    public void setLongDescription(String longDescription) {

        this.longDescription = longDescription;
    }

    public UploadedFile getUploadedFile() {

        return uploadedFile;
    }

    public void setUploadedFile(UploadedFile uploadedFile) {

        this.uploadedFile = uploadedFile;
    }

    public String getStartPrice() {

        return startPrice;
    }

    public void setStartPrice(String startPrice) {

        this.startPrice = startPrice;
    }

    public User getCurrenLrayUser() {

        return currentUser;
    }

    private Auction createAuction() {

        Article article = new Article();
        article.setUserId(currentUser.getUserId());
        article.setCategory(articleCategory);
        article.setShortDesc(shortDescription);
        article.setLongDesc(longDescription);
        article.setImage(uploadedFile.getContents());
        article.setPrice(new BigDecimal(startPrice));

        Auction auction = new Auction();
        auction.setUserId(currentUser.getUserId());
        auction.setAuctionType(auctionType);
        auction.setPrice(article.getPrice());
        auction.setRunning(true);

        auction.setArticle(article);
        article.setAuction(auction);
        return auction;
    }

    private AuctionType findAuctionTypeFromName(String name) {

        for (AuctionType auctionType : AuctionType.values()) {
            if (name.equals(auctionType.getName())) {
                return auctionType;
            }
        }
        return null;
    }
}

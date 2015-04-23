package com.buchner.auction.model.article;

import com.buchner.auction.model.core.app.AuctionForm;
import com.buchner.auction.model.core.app.TradingType;
import com.buchner.auction.model.core.entity.AuctionType;
import com.buchner.auction.model.core.database.AuctionFacade;
import com.buchner.auction.model.core.entity.Article;
import com.buchner.auction.model.core.entity.Auction;
import com.liferay.portal.model.User;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.primefaces.model.UploadedFile;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.math.BigDecimal;

/**
 * View model for the add article portlet
 */
@Named
@RequestScoped
public class AddArticleForm implements AuctionForm {

    private AuctionType auctionType;
    private String articleCategory;
    private String shortDescription;
    private String longDescription;
    private UploadedFile uploadedFile;
    private String startPrice;
    private int daysAuctionActive;
    private TradingType tradingType;

    @Inject
    private AuctionFacade auctionFacade;

    @Inject
    private User currentUser;

    public AddArticleForm() {

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


    public int getDaysAuctionActive() {

        return daysAuctionActive;
    }

    public void setDaysAuctionActive(int daysAuctionActive) {

        this.daysAuctionActive = daysAuctionActive;
    }

    public TradingType getTradingType() {

        return tradingType;
    }

    public void setTradingType(TradingType tradingType) {

        this.tradingType = tradingType;
    }

    /**
     * Creates an article with corresponding auction.
     *
     * @return Auction class object ready for persistence.
     */
    private Auction createAuction() {

        Article article = new Article();
        article.setUserId(currentUser.getUserId());
        article.setCategory(articleCategory);
        article.setShortDesc(shortDescription);
        article.setLongDesc(longDescription);
        article.setImage(uploadedFile.getContents());
        article.setPrice(new BigDecimal(startPrice));

        DateTime dateTime = new DateTime(DateTimeZone.forID("Europe/Berlin"));
        Auction auction = new Auction();
        auction.setUserId(currentUser.getUserId());
        auction.setAuctionType(auctionType);
        auction.setPrice(article.getPrice());
        auction.setRunning(true);
        auction.setEndTime(dateTime.plusDays(daysAuctionActive).toDate());

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

    @Override
    public void clearView() {

        // No need to implement this method as the article view is sent via
        // full non-ajax request clearing the view due to re-rendering of the complete page.
    }
}

package com.buchner.awection.model.article;

import com.buchner.awection.model.internal.entity.Article;
import com.liferay.portal.model.User;
import org.primefaces.model.UploadedFile;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.math.BigDecimal;

@Named
@RequestScoped
public class ArticleView {

    private String auctionType;
    private String articleCategory;
    private String shortDescription;
    private String longDescription;
    private UploadedFile uploadedFile;
    private String startPrice;

    @Inject
    private User currentUser;

    public ArticleView() {

    }

    public Article getArticle() {

        Article article = new Article();
        article.setCategory(articleCategory);
        article.setShortDesc(shortDescription);
        article.setLongDesc(longDescription);
        article.setImage(uploadedFile.getContents());
        article.setPrice(new BigDecimal(startPrice));
        article.setUserId(currentUser.getUserId());
        return article;
    }

    public String getAuctionType() {

        return auctionType;
    }

    public void setAuctionType(String auctionType) {

        this.auctionType = auctionType;
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
}

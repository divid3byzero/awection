package com.buchner.auction.model.core.entity;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "articles")
@NamedQueries({
    @NamedQuery(name = "Article.byUserId", query = "select a from Article a where a.userId = :userId"),
    @NamedQuery(name = "Article.byDescription", query = "select a from Article a where a.shortDesc like :description or a.longDesc like :description")
})
public class Article {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Lob
    private byte[] image;

    @OneToOne(mappedBy = "article", fetch = FetchType.LAZY)
    private Auction auction;

    private long userId;
    private String shortDesc;
    private String longDesc;
    private String category;
    private BigDecimal price;

    public int getId() {

        return id;
    }

    public byte[] getImage() {

        return image;
    }

    public void setImage(byte[] image) {

        this.image = image;
    }

    public String getShortDesc() {

        return shortDesc;
    }

    public void setShortDesc(String shortDesc) {

        this.shortDesc = shortDesc;
    }

    public String getLongDesc() {

        return longDesc;
    }

    public void setLongDesc(String longDesc) {

        this.longDesc = longDesc;
    }

    public String getCategory() {

        return category;
    }

    public void setCategory(String category) {

        this.category = category;
    }

    public Auction getAuction() {

        return auction;
    }

    public void setAuction(Auction auction) {

        this.auction = auction;
    }

    public long getUserId() {

        return userId;
    }

    public void setUserId(long userId) {

        this.userId = userId;
    }

    public BigDecimal getPrice() {

        return price;
    }

    public void setPrice(BigDecimal price) {

        this.price = price;
    }
}

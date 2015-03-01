package com.buchner.awection.model.internal.entity;

import com.buchner.awection.model.internal.AuctionType;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "articles")
public class Article {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Lob
    private byte[] image;

    private String shortDesc;
    private String longDesc;
    private String category;

    @Enumerated(EnumType.STRING)
    private AuctionType auctionType;

    private long userId;
    private BigDecimal price;

    public int getId() {

        return id;
    }

    public void setId(int id) {

        this.id = id;
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

    public AuctionType getAuctionType() {

        return auctionType;
    }

    public void setAuctionType(AuctionType auctionType) {

        this.auctionType = auctionType;
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

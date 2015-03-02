package com.buchner.awection.model.core.entity;

import com.buchner.awection.model.core.AuctionType;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Set;

@Entity
@Table(name = "articles")
public class Article {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Lob
    private byte[] image;

    @Enumerated(EnumType.STRING)
    private AuctionType auctionType;

    @JoinColumn(name = "id", referencedColumnName = "lrayId")
    @ManyToOne(fetch = FetchType.LAZY)
    private Set<Long> userIds;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id", referencedColumnName = "id", updatable = false, insertable = false)
    private Auction auction;

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

    public AuctionType getAuctionType() {

        return auctionType;
    }

    public void setAuctionType(AuctionType auctionType) {

        this.auctionType = auctionType;
    }

    public Set<Long> getUserIds() {

        return userIds;
    }

    public void setUserIds(Set<Long> userIds) {

        this.userIds = userIds;
    }

    public BigDecimal getPrice() {

        return price;
    }

    public void setPrice(BigDecimal price) {

        this.price = price;
    }
}

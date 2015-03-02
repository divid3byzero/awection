package com.buchner.awection.model.auction;

import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

import java.io.ByteArrayInputStream;
import java.math.BigDecimal;

public class ArticleBean {

    private int id;
    private byte[] imageData;
    private String shorDesc;
    private String category;
    private BigDecimal price;

    public ArticleBean(int id, byte[] imageData, String shorDesc, String category,
        BigDecimal price) {

        this.id = id;
        this.imageData = imageData;
        this.shorDesc = shorDesc;
        this.category = category;
        this.price = price;
    }

    public int getId() {

        return id;
    }

    public StreamedContent getImageData() {

        return new DefaultStreamedContent(new ByteArrayInputStream(imageData));
    }

    public String getShorDesc() {

        return shorDesc;
    }

    public String getCategory() {

        return category;
    }

    public BigDecimal getPrice() {

        return price;
    }
}

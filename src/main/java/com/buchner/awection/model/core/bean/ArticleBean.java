package com.buchner.awection.model.core.bean;

import java.math.BigDecimal;

public class ArticleBean {

    private int id;
    private String shorDesc;
    private String category;
    private BigDecimal price;

    public ArticleBean(int id, byte[] imageData, String shorDesc, String category,
        BigDecimal price) {

        this.id = id;
        this.shorDesc = shorDesc;
        this.category = category;
        this.price = price;
    }

    public int getId() {

        return id;
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

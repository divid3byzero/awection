package com.buchner.auction.model.core.entity;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "stock")
public class Stock {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "stock_generator")
    @TableGenerator(name = "stock_generator")
    private int id;

    private long userId;
    private String article;
    private int amount;
    private BigDecimal unitPrice;

    public Stock(long userId, String article, int amount, BigDecimal unitPrice) {

        this.userId = userId;
        this.article = article;
        this.amount = amount;
        this.unitPrice = unitPrice;
    }

    public int getId() {

        return id;
    }

    public long getUserId() {

        return userId;
    }

    public String getArticle() {

        return article;
    }

    public int getAmount() {

        return amount;
    }

    public BigDecimal getUnitPrice() {

        return unitPrice;
    }
}

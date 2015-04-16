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
    private int amount;
    private BigDecimal unitPrice;

    public Stock(long userId, int amount, BigDecimal unitPrice) {

        this.userId = userId;
        this.amount = amount;
        this.unitPrice = unitPrice;
    }

    public int getId() {

        return id;
    }

    public long getUserId() {

        return userId;
    }

    public int getAmount() {

        return amount;
    }

    public BigDecimal getUnitPrice() {

        return unitPrice;
    }
}

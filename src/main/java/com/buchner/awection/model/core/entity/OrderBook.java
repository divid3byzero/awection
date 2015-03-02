package com.buchner.awection.model.core.entity;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "orderbook")
public class OrderBook {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private boolean sell;
    private boolean buy;
    private BigDecimal value;
    private int auctionId;
    private int userId;

    public OrderBook() {

    }

    public int getId() {

        return id;
    }

    public void setId(int id) {

        this.id = id;
    }

    public boolean isSell() {

        return sell;
    }

    public void setSell(boolean sell) {

        this.sell = sell;
    }

    public boolean isBuy() {

        return buy;
    }

    public void setBuy(boolean buy) {

        this.buy = buy;
    }

    public BigDecimal getValue() {

        return value;
    }

    public void setValue(BigDecimal value) {

        this.value = value;
    }

    public int getAuctionId() {

        return auctionId;
    }

    public void setAuctionId(int auctionId) {

        this.auctionId = auctionId;
    }

    public int getUserId() {

        return userId;
    }

    public void setUserId(int userId) {

        this.userId = userId;
    }
}

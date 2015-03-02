package com.buchner.awection.model.core.entity;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "orderbook_buyer")
public class OrderBookBuyer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private BigDecimal value;
    private int auctionId;
    private int userId;

    public OrderBookBuyer() {

    }

    public int getId() {

        return id;
    }

    public void setId(int id) {

        this.id = id;
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

package com.buchner.auction.model.stock;

import com.buchner.auction.model.core.database.StockFacade;
import com.buchner.auction.model.core.entity.Stock;
import com.liferay.portal.model.User;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.math.BigDecimal;

@Named
@RequestScoped
public class StockView {

    private int amount;
    private BigDecimal unitPrice;

    @Inject
    private StockFacade stockFacade;

    @Inject
    private User currenUser;

    protected StockView() {

    }

    public void createStockElement() {

        Stock stock = new Stock(currenUser.getUserId(), amount, unitPrice);
        stockFacade.saveStock(stock);
    }

    public int getAmount() {

        return amount;
    }

    public void setAmount(int amount) {

        this.amount = amount;
    }

    public BigDecimal getUnitPrice() {

        return unitPrice;
    }

    public void setUnitPrice(BigDecimal unitPrice) {

        this.unitPrice = unitPrice;
    }

}

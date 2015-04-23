package com.buchner.auction.model.stock;

import com.buchner.auction.model.core.app.AuctionForm;
import com.buchner.auction.model.core.database.StockFacade;
import com.buchner.auction.model.core.entity.Stock;
import com.liferay.portal.model.User;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.math.BigDecimal;

@Named
@RequestScoped
public class StockForm implements AuctionForm {

    private String article;
    private int amount;
    private BigDecimal unitPrice;

    @Inject
    private StockFacade stockFacade;

    @Inject
    private User currentUser;

    protected StockForm() {

    }

    public void createStockElement() {

        Stock stock = new Stock(currentUser.getUserId(), article, amount, unitPrice);
        stockFacade.saveStock(stock);
    }

    public String getArticle() {

        return article;
    }

    public void setArticle(String article) {

        this.article = article;
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

    public void clearView() {

        article = "";
        amount = 0;
        unitPrice = null;
    }
}

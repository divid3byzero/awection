package com.buchner.auction.controller;

import com.buchner.auction.model.stock.StockForm;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@ApplicationScoped
public class StockController extends AbstractBaseController {

    @Inject
    private StockForm stockView;

    protected StockController() {

    }

    public void saveStock() {

        stockView.createStockElement();
        clearView(stockView);
        viewMessage("Article added.");
    }
}

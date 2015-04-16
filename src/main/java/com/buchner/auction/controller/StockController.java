package com.buchner.auction.controller;

import com.buchner.auction.model.stock.StockView;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@ApplicationScoped
public class StockController {

    @Inject
    private StockView stockView;

    protected StockController() {

    }

    public void saveStock() {

        stockView.createStockElement();
    }
}

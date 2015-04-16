package com.buchner.auction.model.core.database;

import com.buchner.auction.model.core.entity.Stock;

import javax.inject.Inject;

@Transaction
public class StockFacade {

    @Inject
    private StockDao stockDao;

    protected StockFacade() {

    }

    public void saveStock(Stock stock) {

        stockDao.save(stock);
    }
}

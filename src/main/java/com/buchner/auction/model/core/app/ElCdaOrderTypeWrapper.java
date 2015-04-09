package com.buchner.auction.model.core.app;

import javax.inject.Named;

@Named
public class ElCdaOrderTypeWrapper {

    private CdaOrderType marketOrder;
    private CdaOrderType limitOrder;

    public ElCdaOrderTypeWrapper() {

        marketOrder = CdaOrderType.MARKET_ORDER;
        limitOrder = CdaOrderType.LIMIT_ORDER;
    }

    public CdaOrderType getMarketOrder() {

        return marketOrder;
    }

    public CdaOrderType getLimitOrder() {

        return limitOrder;
    }
}

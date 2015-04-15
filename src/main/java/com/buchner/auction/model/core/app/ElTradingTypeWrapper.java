package com.buchner.auction.model.core.app;


import javax.inject.Named;

@Named
public class ElTradingTypeWrapper {

    private TradingType classic;
    private TradingType xchange;

    public ElTradingTypeWrapper() {

        this.classic = TradingType.CLASSIC;
        this.xchange = TradingType.XCHANGE;
    }

    public TradingType getClassic() {

        return classic;
    }

    public TradingType getXchange() {

        return xchange;
    }
}

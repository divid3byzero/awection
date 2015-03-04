package com.buchner.awection.model.auction;

import com.buchner.awection.model.core.bean.AuctionBean;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named
@SessionScoped
public class TradeViewState implements Serializable {

    private AuctionBean selectedAuctionBean;
    private List<AuctionBean> tradeingSession;

    protected TradeViewState() {

    }

    public AuctionBean getSelectedAuctionBean() {

        return selectedAuctionBean;
    }

    public void setSelectedAuctionBean(AuctionBean selectedAuctionBean) {

        this.selectedAuctionBean = selectedAuctionBean;
    }

}

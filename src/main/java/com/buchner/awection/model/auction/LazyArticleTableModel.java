package com.buchner.awection.model.auction;


import com.buchner.awection.model.core.database.AuctionFacade;
import com.liferay.portal.model.User;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import javax.inject.Inject;
import java.util.List;
import java.util.Map;

public class LazyArticleTableModel extends LazyDataModel<ArticleBean> {

    @Inject
    private AuctionFacade auctionFacade;

    @Inject
    private User currentUser;

    private List<ArticleBean> articleBeans;

    public List<ArticleBean> load(int first, int pageSize, String sortField, SortOrder sortOrder,
        Map<String, Object> filters) {

        articleBeans = auctionFacade.getUserArticles(currentUser.getUserId());
        setRowCount(articleBeans.size());
        return articleBeans;
    }


    public ArticleBean getRowData(String rowKey) {

        Integer key = Integer.parseInt(rowKey);
        for (ArticleBean articleBean : articleBeans) {
            if (articleBean.getId() == key) {
                return articleBean;
            }
        }
        return null;
    }

    public Object getRowKey(ArticleBean articleBean) {

        return articleBean.getId();
    }


}

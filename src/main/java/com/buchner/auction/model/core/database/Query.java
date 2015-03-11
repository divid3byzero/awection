package com.buchner.auction.model.core.database;

import java.util.ArrayList;
import java.util.List;

public class Query {

    public static class QueryBuilder {

        private String select;
        private List<String> where;

        public QueryBuilder() {

            this.where = new ArrayList<>();
        }

        public QueryBuilder select(String selectClass) {

            select = "select " + getIdentifier(
                selectClass) + " from " + selectClass + " " + getIdentifier(
                selectClass);
            return this;
        }

        public QueryBuilder select(String selectClass, List<String> properties) {

            String tempSelect = "select ";
            for (int i = 0; i < properties.size(); i++) {

                if (i == properties.size() - 1) {
                    tempSelect += getIdentifier(selectClass) + "." + properties.get(i) + " ";
                    break;
                }

                tempSelect += getIdentifier(selectClass) + "." + properties.get(i) + ", ";
            }

            tempSelect += "from" + " " +  selectClass + " " + getIdentifier(selectClass);
            this.select = tempSelect;
            return this;
        }

        public QueryBuilder where(WhereQuery whereQuery) {
            where.add(" where " + whereQuery.getWhereClause());
            return this;
        }

        public QueryBuilder where(List<WhereQuery> whereQueries) {

            where.add(" where ");
            for (int i = 0; i < whereQueries.size(); i++) {

                WhereQuery currentWhereQuery = whereQueries.get(i);
                if (i == whereQueries.size() - 1) {
                    this.where.add(currentWhereQuery.getWhereClause());
                    break;
                }

                String concatenate = currentWhereQuery.isAnd() ? " and " : " or ";
                this.where.add(currentWhereQuery.getWhereClause() + concatenate);
            }

            return this;
        }

        public Query build() {

            return new Query(this);
        }

        private String getIdentifier(String selectClass) {

            return selectClass.substring(0, 2).toLowerCase();
        }
    }

    private QueryBuilder queryBuilder;

    public Query(QueryBuilder queryBuilder) {

        this.queryBuilder = queryBuilder;
    }

    public String getQueryString() {

        return buildQueryString();
    }

    private String buildQueryString() {

        String queryString = queryBuilder.select;
        List<String> where;
        if (null != (where = queryBuilder.where)) {
            for (String whereClaus : queryBuilder.where) {
                queryString += whereClaus;
            }
        }
        return queryString;
    }
}

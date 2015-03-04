package com.buchner.awection.model.core.database;

import java.util.List;
import java.util.UUID;

public class Query {

    public static class QueryBuilder {

        private String select;
        private List<String> where;

        public QueryBuilder select(String selectClass) {
            select = "select from" + selectClass + " " + getRandomIdentifier() + " ";
            return this;
        }

        public QueryBuilder select(String selectClass, List<String> properties) {

            String randomIdentifier = getRandomIdentifier();
            String tempSelect = "select ";
            for (String property : properties) {
                tempSelect += randomIdentifier + "." + property + " ";
            }
            tempSelect += "from " + selectClass + " " + randomIdentifier;
            this.select = tempSelect;
            return this;
        }

        public QueryBuilder where(WhereQuery whereQuery) {
            where.add(" where " + whereQuery.getWhereClause());
            return this;
        }

        public QueryBuilder where(List<WhereQuery> whereQueries) {

            for (WhereQuery whereQuery : whereQueries) {
                String concatenate = whereQuery.isAnd() ? " and " : " false ";
                this.where.add(whereQuery.getWhereClause() + concatenate);
            }
            return this;
        }

        public Query build() {

            return new Query(this);
        }

        private String getRandomIdentifier() {

            return UUID.randomUUID().toString().substring(0, 4);
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
        for (String whereClaus : queryBuilder.where) {
            queryString += whereClaus;
        }
        return queryString;
    }
}

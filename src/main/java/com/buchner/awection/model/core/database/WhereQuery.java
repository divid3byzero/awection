package com.buchner.awection.model.core.database;

public class WhereQuery {

    private String whereClause;
    private boolean and;

    public WhereQuery(String whereClause, boolean and) {

        this.whereClause = whereClause;
        this.and = and;
    }

    public WhereQuery(String whereClause) {

        this.whereClause = whereClause;
    }

    public String getWhereClause() {

        return whereClause;
    }

    public boolean isAnd() {

        return and;
    }
}

package com.buchner.auction;

import com.buchner.auction.model.core.database.Query;
import com.buchner.auction.model.core.database.WhereQuery;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class DbQueryBuilderTest {

    @Test
    public void testSelect() {

        String testQuery = "select te from Test te";
        String queryBuilderString =
            new Query.QueryBuilder().select("Test").build().getQueryString();
        Assert.assertNotNull(queryBuilderString);
        Assert.assertEquals(testQuery, queryBuilderString);
    }

    @Test
    public void testSelectProperties() {

        String testQuery = "select te.id from Test te";
        List<String> propertiesSingle = new ArrayList<>();
        propertiesSingle.add("id");
        String queryBuilderString =
            new Query.QueryBuilder().select("Test", propertiesSingle).build().getQueryString();

        Assert.assertNotNull(queryBuilderString);
        Assert.assertEquals(testQuery, queryBuilderString);

        String testQueryMultiple = "select te.id, te.prop1, te.prop2, te.prop3 from Test te";
        List<String> propertiesMultiple = new ArrayList<>();
        propertiesMultiple.add("id");
        propertiesMultiple.add("prop1");
        propertiesMultiple.add("prop2");
        propertiesMultiple.add("prop3");
        String queryBuilderStringMultiple =
            new Query.QueryBuilder().select("Test", propertiesMultiple).build().getQueryString();

        Assert.assertNotNull(queryBuilderStringMultiple);
        Assert.assertEquals(testQueryMultiple, queryBuilderStringMultiple);
    }

    @Test
    public void testSingleWhere() {

        String testQuery = "select te.prop1 from Test te where te.prop1 < 5";
        WhereQuery whereQuery = new WhereQuery("te.prop1 < 5");
        List<String> properties = new ArrayList<>();
        properties.add("prop1");

        String queryBuilderString =
            new Query.QueryBuilder()
                .select("Test", properties)
                .where(whereQuery)
                .build()
                .getQueryString();

        Assert.assertNotNull(queryBuilderString);
        Assert.assertEquals(testQuery, queryBuilderString);
        System.out.print(queryBuilderString);
    }

    @Test
    public void testMultipleWhere() {

        String testQuery =
            "select te.prop0 from Test te where te.prop1 < 5 and te.prop2 like '%foo%' and te.prop3 = 4 or te.prop4 > 10";

        List<WhereQuery> whereQueries = new ArrayList<>();
        whereQueries.add(new WhereQuery("te.prop1 < 5", true));
        whereQueries.add(new WhereQuery("te.prop2 like '%foo%'", true));
        whereQueries.add(new WhereQuery("te.prop3 = 4", false));
        whereQueries.add(new WhereQuery("te.prop4 > 10"));

        List<String> properties = new ArrayList<>();
        properties.add("prop0");
        String queryBuilderString =
            new Query.QueryBuilder()
                .select("Test", properties)
                .where(whereQueries)
                .build()
                .getQueryString();

        Assert.assertNotNull(queryBuilderString);
        Assert.assertEquals(testQuery, queryBuilderString);
        System.out.print(queryBuilderString);
    }
}

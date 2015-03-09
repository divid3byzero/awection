package com.buchner.auction.model.core.database;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

public class GenericDao<T> {

    private EntityManager entityManager;
    private Class<T> entityClass;

    protected GenericDao(EntityManager entityManager, Class<T> entityClass) {

        this.entityManager = entityManager;
        this.entityClass = entityClass;
    }

    public T findById(int id) {

        return entityManager.find(entityClass, id);
    }

    public T findByUserId(long userId) {

        String queryString = new Query.QueryBuilder()
            .select(this.entityClass.getName())
            .where(new WhereQuery(":id"))
            .build().getQueryString();

        TypedQuery<T> namedQuery = entityManager.createNamedQuery(queryString, entityClass);
        namedQuery.setParameter("id", userId);
        return namedQuery.getSingleResult();
    }
}

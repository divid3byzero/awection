package com.buchner.auction.model.core.database;

import javax.persistence.EntityManager;

public class GenericDao<T> {

    protected EntityManager entityManager;
    protected Class<T> entityClass;

    protected GenericDao() {

    }

    protected GenericDao(EntityManager entityManager, Class<T> entityClass) {

        this.entityManager = entityManager;
        this.entityClass = entityClass;
    }


    public void save(T entity) {

        entityManager.persist(entity);
    }

    public T findById(int id) {

        return entityManager.find(entityClass, id);
    }

}

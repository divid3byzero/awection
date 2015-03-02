package com.buchner.awection.model.core.database;

import javax.inject.Inject;
import javax.persistence.EntityManager;

public class AuctionDao {

    @Inject
    private EntityManager entityManager;

    // Needed for CDI
    protected AuctionDao() {

    }

    public void save(Object entity) {

        entityManager.persist(entity);
    }

    public Object findById(int id, Class entityClass) {

        return entityManager.find(entityClass, id);
    }

    public Object findByAttribute(Object attribute, Class entityClass) {

        return entityManager.find(entityClass, attribute);
    }

    public void transaction() {

        entityManager.getTransaction().begin();
    }

    public void commit() {

        entityManager.getTransaction().commit();
    }

    public void rollback() {

        entityManager.getTransaction().rollback();
    }

    public void close() {

        entityManager.close();
    }


}

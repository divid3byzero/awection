package com.buchner.awection.database;

import javax.persistence.EntityManager;

public class AwectionDao<T> {

    private EntityManager entityManager;
    private Class<T> entityClass;

    // Needed for CDI
    protected AwectionDao() {

    }

    public AwectionDao(EntityManager entityManager, Class<T> entityClass) {

        this.entityManager = entityManager;
        this.entityClass = entityClass;
    }

    public void save(T entity) {

        startTransaction();
        entityManager.persist(entity);
        commitAndCloseTransaction();
    }

    public T findById(int id) {

        startTransaction();
        T entity = entityManager.find(entityClass, id);
        closeTransaction();
        return entity;
    }

    private void startTransaction() {

        entityManager.getTransaction().begin();
    }

    private void commitAndCloseTransaction() {

        entityManager.getTransaction().commit();
        closeTransaction();
    }

    private void rollbackTransaction() {

        entityManager.getTransaction().rollback();
    }

    private void closeTransaction() {

        entityManager.close();
    }

}

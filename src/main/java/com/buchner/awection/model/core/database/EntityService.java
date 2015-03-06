package com.buchner.awection.model.core.database;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class EntityService {

    protected EntityService() {

    }

    @Produces
    @RequestScoped
    public EntityManager produceEntityManager() {

        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("auction");
        return entityManagerFactory.createEntityManager();
    }
}

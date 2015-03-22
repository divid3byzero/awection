package com.buchner.auction.model.core.database;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * Model class to create access to the entity manager via @Inject
 */
public class EntityService {

    protected EntityService() {

    }

    /**
     * Producer method that creates an entity manager reference per each request.
     * @return
     */
    @Produces
    @RequestScoped
    public EntityManager produceEntityManager() {

        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("auction");
        return entityManagerFactory.createEntityManager();
    }
}

package com.buchner.awection.model.core.database;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;

public class EntityService {

    // Needed for CDI
    protected EntityService() {

    }

    @Produces
    @RequestScoped
    public EntityManager produceEntityManager() {

        return Persistence.createEntityManagerFactory("auction").createEntityManager();
    }

}

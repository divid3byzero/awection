package com.buchner.awection.model.core.database;

import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;
import javax.persistence.EntityManager;

@Transaction
@Interceptor
public class TransactionInterceptor {

    @Inject
    private EntityManager entityManager;

    @AroundInvoke
    public void handleTransaction(InvocationContext invocationContext) {

        entityManager.getTransaction().begin();
        try {
            invocationContext.proceed();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
        }
        entityManager.getTransaction().commit();
    }
}

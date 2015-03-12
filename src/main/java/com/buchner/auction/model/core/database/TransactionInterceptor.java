package com.buchner.auction.model.core.database;

import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

@Transaction
@Interceptor
public class TransactionInterceptor {

    @Inject
    private EntityManager entityManager;

    @AroundInvoke
    public Object handleTransaction(InvocationContext invocationContext) {

        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        try {

            Object proceed = invocationContext.proceed();
            transaction.commit();

            return proceed;

        } catch (Exception e) {
            transaction.rollback();
        }
        return null;
    }
}

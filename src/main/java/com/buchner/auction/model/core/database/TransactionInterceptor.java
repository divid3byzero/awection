package com.buchner.auction.model.core.database;

import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

/**
 * CDI interceptor class. The method annotated with @AroundInvoke will be executed
 * if the CDI framework finds the mapped annotation.
 */

// The annotation @Transaction is mapped to the interceptor
@Transaction
// Declare class as interceptor
@Interceptor
public class TransactionInterceptor {

    /* Entity manager is needed to start/end transaction */
    @Inject
    private EntityManager entityManager;

    @AroundInvoke
    public Object handleTransaction(InvocationContext invocationContext) {

        // Get and start transaction.
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        try {

            // This call continues the defined flow of the intercepted method
            Object proceed = invocationContext.proceed();
            // After the method returns, close transaction.
            transaction.commit();

            // Return the defined return values of the intercepted method.
            return proceed;

        } catch (Exception e) {
            transaction.rollback();
        }
        return null;
    }
}

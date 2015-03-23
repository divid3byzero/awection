package com.buchner.auction.model.core.database;

import javax.interceptor.InterceptorBinding;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Annotation used as a signal to trigger the CDI interceptor.
 */
// Renders annotation available as an interceptor signal
@InterceptorBinding
// Keep this annotation available during runtime.
@Retention(RetentionPolicy.RUNTIME)
// Define elements where this annotation can be used.
@Target({ElementType.TYPE, ElementType.METHOD})
public @interface Transaction {
}

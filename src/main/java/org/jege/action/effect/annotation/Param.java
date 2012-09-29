package org.jege.action.effect.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface Param {
    String name();
    Class clazz();
    boolean required();
}

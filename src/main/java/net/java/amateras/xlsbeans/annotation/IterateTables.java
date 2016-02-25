package net.java.amateras.xlsbeans.annotation;

import java.lang.annotation.*;

/**
 * @author Mitsuyoshi Hasegawa
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.FIELD})
@Documented
public @interface IterateTables {

    String tableLabel();

    Class<?> tableClass();

    int bottom() default -1;

    boolean optional() default false;
}

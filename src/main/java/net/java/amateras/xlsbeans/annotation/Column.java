package net.java.amateras.xlsbeans.annotation;

import java.lang.annotation.*;

/**
 * @author Naoki Takezoe
 */
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Column {

    String columnName();

    boolean merged() default false;

    int headerMerged() default 0;

    boolean optional() default false;

}

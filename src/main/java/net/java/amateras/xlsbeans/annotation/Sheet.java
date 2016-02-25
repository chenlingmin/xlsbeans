package net.java.amateras.xlsbeans.annotation;

import java.lang.annotation.*;

/**
 * @author Naoki Takezoe
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Sheet {

    /**
     * Returns the mapped sheet name.
     *
     * @return the sheet name
     */
    String name() default "";

    /**
     * Returns the regular expression to map sheet name.
     *
     * @return the regular expression
     */
    String regex() default "";

    /**
     * Returns the mapped sheet number.
     *
     * @return the sheet number
     */
    int number() default -1;
}

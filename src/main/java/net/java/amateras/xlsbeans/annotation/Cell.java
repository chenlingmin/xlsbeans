package net.java.amateras.xlsbeans.annotation;

import java.lang.annotation.*;

/**
 * @author Naoki Takezoe
 */
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Cell {

    int row();

    int column();

}

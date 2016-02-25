package net.java.amateras.xlsbeans.annotation;

import java.lang.annotation.*;

/**
 * The method which are added this annotation are invoked
 * after sheet loading.
 *
 * @author Naoki Takezoe
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface PostProcess {

}

package net.java.amateras.xlsbeans.annotation;

import java.lang.annotation.*;

/**
 * @author Naoki Takezoe
 */
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface LabelledCell {

    boolean optional() default false;

    int range() default 1;

    int labelColumn() default -1;

    int labelRow() default -1;

    LabelledCellType type();

    String label() default "";

    String headerLabel() default "";

    int skip() default 0;

}

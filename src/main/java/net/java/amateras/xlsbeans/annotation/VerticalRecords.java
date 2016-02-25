package net.java.amateras.xlsbeans.annotation;

import java.lang.annotation.*;

/**
 * An annotation for the property which is mapped to the vertical table records.
 * <p/>
 * TODO Is this necessary?
 *
 * @author Naoki Takezoe
 */
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface VerticalRecords {

    boolean optional() default false;

    String tableLabel() default "";

    String terminateLabel() default "";

    int headerColumn() default -1;

    int headerRow() default -1;

    Class<?> recordClass();

    RecordTerminal terminal() default RecordTerminal.Empty;

    int range() default 1;

    int headerLimit() default 0;
}

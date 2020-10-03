package net.apolloclient.module.bus;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Populates the annotated field with the module instance create by {@link ModuleFactory}
 *
 * @author Icovid | Icovid#3888
 * @since b0.2
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface Instance {
}

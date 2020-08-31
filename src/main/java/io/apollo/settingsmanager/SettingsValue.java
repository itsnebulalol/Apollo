package io.apollo.settingsmanager;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/** Annotation use to call collection of data.
 * @author Icovid | Icovid#3888
 * @since 1.0.0 **/
@Retention(value = RetentionPolicy.RUNTIME)
@Target(value = ElementType.FIELD)
public @interface SettingsValue {
    String key() default "";
    String description() default "Awesome Setting the Developer Offered no Description for!";
}

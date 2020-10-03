package net.apolloclient.module.bus;

import net.apolloclient.event.Priority;
import net.apolloclient.module.bus.event.*;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Marks a method to handle Minecraft startup and Module initiation Events
 * The method must have a single parameter, one of the following types.
 * <ul>
 * <li> {@link InitializationEvent} : runs when module is constructed.</li>
 * <li> {@link PostInitializationEvent} : runs after all modules have been constructed.</li>
 * <li> {@link EnableEvent} : runs each time module is enabled.</li>
 * <li> {@link DisableEvent} : runs each time module is disabled.</li>
 * <li> TODO: {@link ShutDownEvent} : runs when game is closed. </li>
 * </ul>
 *
 * @author Icovid | Icovid#3888
 * @since b0.2
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface EventHandler {

    /**
     * Used to call event from another module by inputting the module name.
     */
    String[] target() default "";

    /**
     * Priority over method over other methods in class.
     */
    Priority priority() default Priority.NORMAL;
}

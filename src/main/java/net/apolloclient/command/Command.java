package net.apolloclient.command;

import net.apolloclient.event.Priority;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Any method found with this annotation will be registered in
 * {@link CommandBus} and will be called on chat message.
 *
 * @author Icovid | Icovid#3888
 * @since b0.2
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Command {

    /**
     * @return arguments after {@code .} to trigger command on.
     */
    String[] args();

    /**
     * @return description of command purpose
     */
    String description();

    /**
     * @return if chat message should still show when command triggered
     */
    boolean deleteMessage() default true;

    /**
     * @return weather command bus requires same case
     */
    boolean ignoreCase() default true;

    /**
     * Priority of the command.
     */
    Priority priority() default Priority.NORMAL;
}

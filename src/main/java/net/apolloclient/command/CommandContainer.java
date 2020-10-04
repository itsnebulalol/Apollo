package net.apolloclient.command;

import net.apolloclient.event.Priority;

import java.lang.reflect.Method;

public class CommandContainer {

    public final Object instance;
    public final Method method;
    public final String[] args;
    public final String description;
    public final boolean deleteMessage;
    public final boolean ignoreCase;
    private final Priority priority;

    /**
     * @param instance instance of object to invoke
     * @param method method to invoke
     * @param args args command can be triggered by
     * @param description description of command purpose
     * @param deleteMessage if chat message should still show when command triggered
     * @param ignoreCase must be same case
     * @param priority priority of method
     */
    public CommandContainer(Object instance, Method method, String[] args, String description, boolean deleteMessage, boolean ignoreCase, Priority priority) {
        this.instance = instance;
        this.method = method;
        this.args = args;
        this.description = description;
        this.deleteMessage = deleteMessage;
        this.ignoreCase = ignoreCase;
        this.priority = priority;
    }

    /**
     * Invoke method using instance.
     *
     * @param args for invoking.
     */
    public void invoke(Object... args) {
        try {
            this.method.invoke(instance, args);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * @return priority of method
     */
    public Priority getPriority() { return priority; }
}

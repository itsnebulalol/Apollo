package net.apolloclient.command;

import net.apolloclient.Apollo;
import net.apolloclient.event.Priority;
import net.apolloclient.event.bus.SubscribeEvent;
import net.apolloclient.event.impl.player.PlayerChatEvent;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Comparator;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Creates commands of type {@link CommandContainer} by
 * registering objects with {@link #register(Object)}
 *
 * @author Icovid | Icovid#3888
 * @since b0.2
 */
public class CommandBus {

    private final CopyOnWriteArrayList<CommandContainer> commands = new CopyOnWriteArrayList<>();

    public static String PREFIX = ".";

    /**
     * Registers an object to the {@link CommandBus}. All methods annotated with the {@link
     * Command} annotation will be added.
     *
     * @param any instance of the object you want to register
     */
    public void register(Object any) {
        for (Method method : any.getClass().getDeclaredMethods()) {
            for (Annotation annotation : method.getAnnotationsByType(Command.class)) {

                if (method.getParameterTypes().length == 1 && String.class.isAssignableFrom(method.getParameterTypes()[0])) {

                    method.setAccessible(true);
                    Command command = method.getAnnotation(Command.class);

                    commands.add(new CommandContainer(any, method, command.args(), command.description(), command.deleteMessage(), command.ignoreCase(), command.priority()));

                    Apollo.log("[COMMAND-BUS] Registered method " + method.getName().toUpperCase() + " at " + any.getClass().getCanonicalName());

                } else {
                    Apollo.error("[COMMAND-BUS] Method " + method.getName().toUpperCase() + " has invalid parameters!");
                }
            }
        }
        commands.sort(Comparator.comparingInt(listener -> listener.getPriority().id));
    }

    /**
     * Unregisters an object from the EventBus.
     *
     * @param any object to be unregistered from the EventBus
     */
    public void unregister(Object any) { commands.removeIf(listener -> listener.instance == any); }

    /**
     * Post command to each cached method.
     * @param event chat event that's cancelable
     */
    @SubscribeEvent(priority = Priority.LOW, cancelable = false)
    public void onChat(PlayerChatEvent event) {
        if (event.message.startsWith(CommandBus.PREFIX)) {
            event.setCanceled(true);
            String[] args = event.message.split(" ", 2);
            for (CommandContainer container : this.commands) {
                if (Arrays.stream(container.args).anyMatch(container.ignoreCase ? args[0].replaceFirst(".", "")::equalsIgnoreCase : args[0].replaceFirst(".", "")::equals)) {
                    if (args.length > 1) container.invoke(args[1]);
                    event.setCanceled(container.deleteMessage);
                }
            }
        }
    }

}

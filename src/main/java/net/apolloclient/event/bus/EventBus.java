/*⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤
 Copyright (C) 2020-2021 developed by Icovid and Apollo Development Team.

 This program is free software: you can redistribute it and/or modify
 it under the terms of the GNU Affero General Public License as published
 by the Free Software Foundation, either version 3 of the License, or
 (at your option) any later version.
 This program is distributed in the hope that it will be useful,
 but WITHOUT ANY WARRANTY; without even the implied warranty of
 MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 GNU Affero General Public License for more details.
 You should have received a copy of the GNU Affero General Public License
 along with this program.  If not, see https://www.gnu.org/licenses/.

 Contact: Icovid#3888 @ https://discord.com
 ⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤*/

package net.apolloclient.event.bus;

import net.apolloclient.Apollo;
import net.apolloclient.event.Event;
import net.apolloclient.event.EventCancelable;
import net.apolloclient.module.bus.EventHandler;
import net.apolloclient.module.bus.ModContainer;
import net.apolloclient.module.bus.event.ModuleEvent;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Comparator;
import java.util.HashMap;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Class for holding event subscribers. The main implementation can be found in {@link Apollo}
 *
 * <p>You can register an object with the {@code register} method.
 *
 * @author Icovid | Icovid#3888
 * @since b0.2
 */
public class EventBus {

    private final HashMap<Class<? extends Event>, CopyOnWriteArrayList<SubscribeEventContainer>> listeners = new HashMap<>();

    /**
     * Register mod container instance to receive {@link ModuleEvent}s using
     * the {@link EventHandler} annotation.
     *
     * @param modContainer container class of module
     */
    @SuppressWarnings("unchecked")
    public void register(ModContainer modContainer) {
        for (Method method : modContainer.getInstance().getClass().getDeclaredMethods()) {
            for (Annotation annotation : method.getAnnotationsByType(SubscribeEvent.class)) {

                if (method.getParameterTypes().length == 1 && Event.class.isAssignableFrom(method.getParameterTypes()[0])) {

                    method.setAccessible(true);
                    Class<? extends Event> event = (Class<? extends Event>) method.getParameterTypes()[0];

                    if (!modContainer.getEvents().containsKey(event))
                        modContainer.getEvents().put(event, new CopyOnWriteArrayList<>());

                    modContainer.getEvents().get(event).add(new SubscribeEventContainer(modContainer.getInstance(), method, method.getAnnotation(SubscribeEvent.class).priority(), method.getAnnotation(SubscribeEvent.class).cancelable()));

                    Apollo.log("[" + modContainer.getName() + "] [EVENT] Registered method " + method.getName().toUpperCase() + " with " + method.getParameterTypes()[0].getCanonicalName() + " event.");

                    modContainer.getEvents().get(event).sort(Comparator.comparingInt(listener -> listener.getPriority().id));
                } else {
                    Apollo.error("[" + modContainer.getName() + "] [EVENT] Event method " + method.getName().toUpperCase() + " has invalid parameters!");
                }

            }
        }
    }

    /**
     * Unregisters an container from the EventBus.
     *
     * @param modContainer container to be unregistered from the EventBus *
     */
    public void unregister(ModContainer modContainer) { modContainer.getEvents().clear(); }

    /**
     * Registers an object to the {@link EventBus}. All methods annotated with the {@link
     * SubscribeEvent} annotation will be called when that Event is posted.
     *
     * @param any instance of the object you want to register
     */
    @SuppressWarnings("unchecked")
    public void register(Object any) {
        for (Method method : any.getClass().getDeclaredMethods()) {
            for (Annotation annotation : method.getAnnotationsByType(SubscribeEvent.class)) {

                if (method.getParameterTypes().length == 1 && Event.class.isAssignableFrom(method.getParameterTypes()[0])) {

                    method.setAccessible(true);
                    Class<? extends Event> event = (Class<? extends Event>) method.getParameterTypes()[0];

                    if (!listeners.containsKey(event))
                        listeners.put(event, new CopyOnWriteArrayList<>());

                    listeners.get(event).add(new SubscribeEventContainer(any, method, method.getAnnotation(SubscribeEvent.class).priority(), method.getAnnotation(SubscribeEvent.class).cancelable()));

                    Apollo.log("[" + any.getClass().getSimpleName() + "] [EVENT] Registered method " + method.getName().toUpperCase() + " with " + method.getParameterTypes()[0].getCanonicalName() + " event.");

                    listeners.get(event).sort(Comparator.comparingInt(listener -> listener.getPriority().id));
                } else {
                    Apollo.error("[" + any.getClass().getSimpleName()+ "] [EVENT] Event method " + method.getName().toUpperCase() + " has invalid parameters!");
                }

            }
        }
    }

    /**
     * Unregisters an object from the EventBus.
     *
     * @param any object to be unregistered from the EventBus
     */
    public void unregister(Object any) { listeners.values().forEach(it -> it.removeIf(listener -> listener.instance == any)); }

    /**
     * Posts an event to the {@link EventBus}. This calls every method that is listening for the event
     * in question.
     *
     * @param event event to post
     */
    public void post(Event event) {
        boolean cancelable = event instanceof EventCancelable;
        for (ModContainer container : Apollo.MODULE_FACTORY.modules) {
            for (SubscribeEventContainer eventContainer : container.getEvents().getOrDefault(event.getClass(), new CopyOnWriteArrayList<>())) {
                if (cancelable && ((EventCancelable) event).isCanceled() && eventContainer.cancelable) return;
                eventContainer.invoke(event);
            }
        }
        for (SubscribeEventContainer container : this.listeners.getOrDefault(event.getClass(), new CopyOnWriteArrayList<>())) {
            if (cancelable && ((EventCancelable) event).isCanceled() && container.cancelable) return;
            container.invoke(event);
        }
    }
}

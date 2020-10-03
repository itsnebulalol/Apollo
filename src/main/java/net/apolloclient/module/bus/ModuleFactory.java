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

package net.apolloclient.module.bus;

import net.apolloclient.Apollo;
import net.apolloclient.event.bus.EventContainer;
import net.apolloclient.module.DraggableModuleContainer;
import net.apolloclient.module.ModuleContainer;
import net.apolloclient.module.bus.event.InitializationEvent;
import net.apolloclient.module.bus.event.ModuleEvent;
import org.reflections.Reflections;

import java.io.File;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Creates modules of type {@link ModuleContainer} or {@link DraggableModuleContainer} by
 * searching a class path or mods folder.
 *
 * <p>Events and instance registering is handled here and this acts as a helper
 * class to {@link } so module events are triggered according to there priority.</p>
 *
 * @author Icovid | Icovid#3888
 * @since b0.2
 */
public class ModuleFactory {

    public final CopyOnWriteArrayList<ModContainer> modules = new CopyOnWriteArrayList<>();

    /**
     * @param localPath path to local modules
     * @param modsFolder folder for external modules / unused
     */
    public ModuleFactory(String localPath, File modsFolder) {
        Reflections reflections = new Reflections(localPath);

        HashMap<ModContainer, CopyOnWriteArrayList<Method>> sharedMethods = new HashMap<>();

        CopyOnWriteArrayList<Class<?>> classes = new CopyOnWriteArrayList<>(reflections.getTypesAnnotatedWith(Module.class));
        classes.sort(Comparator.comparingInt(module -> module.getAnnotation(Module.class).priority()));

        for (Class<?> clazz : classes) {
            try {
                ModContainer container = new ModuleContainer(clazz.getAnnotation(Module.class), clazz.newInstance());
                this.handleInstance(container);
                sharedMethods.put(container, new CopyOnWriteArrayList<>());
                sharedMethods.get(container).addAll(this.register(container));
                container.post(new InitializationEvent(container));
                modules.add(container);
            }
            catch (Exception e) {
                Apollo.error("Unable to create module instance of " + clazz.getCanonicalName());
                e.printStackTrace();
            }
        }

        classes = new CopyOnWriteArrayList<>(reflections.getTypesAnnotatedWith(DraggableModule.class));
        classes.sort(Comparator.comparingInt(module -> module.getAnnotation(DraggableModule.class).priority()));

        for (Class<?> clazz : classes) {
            try {
                ModContainer container = new DraggableModuleContainer(clazz.getAnnotation(DraggableModule.class), clazz.newInstance());
                this.handleInstance(container);
                sharedMethods.put(container, new CopyOnWriteArrayList<>());
                sharedMethods.get(container).addAll(this.register(container));
                container.post(new InitializationEvent(container));
                modules.add(container);
            }
            catch (Exception e) {
                Apollo.error("Unable to create module instance of " + clazz.getCanonicalName());
                e.printStackTrace();
            }
        }

        // TODO: EXTERNAL MOD LOADING!

        this.registerSharedListeners(sharedMethods);
    }

    /**
     * Get {@link ModContainer} by its name
     *
     * @param name name of container
     * @return {@link ModContainer}
     */
    public ModContainer getModContainerByName(String name) {
        for (ModContainer modContainer : modules) {
            if (modContainer.getName().equals(name)) return modContainer;
        }
        return null;
    }

    /**
     * Sorts modules list by priority.
     */
    public void sortModules() {
        modules.sort(Comparator.comparingInt(ModContainer::getPriority));
    }

    /**
     * Register mod container instance to receive {@link ModuleEvent}s using
     * the {@link EventHandler} annotation.
     *
     * @param modContainer container class of module
     * @return list of methods containing targets.
     */
    @SuppressWarnings("unchecked")
    public ArrayList<Method> register(ModContainer modContainer) {
        ArrayList<Method> methods = new ArrayList<>();
        for (Method method : modContainer.getInstance().getClass().getDeclaredMethods()) {
            for (Annotation annotation : method.getAnnotationsByType(EventHandler.class)) {

                if (method.getParameterTypes().length == 1 && ModuleEvent.class.isAssignableFrom(method.getParameterTypes()[0])) {

                    method.setAccessible(true);
                    Class<? extends ModuleEvent> moduleEvent = (Class<? extends ModuleEvent>) method.getParameterTypes()[0];

                    if (!method.getAnnotation(EventHandler.class).target().equals(""))
                        methods.add(method);

                    if (!modContainer.getHandlers().containsKey(moduleEvent))
                        modContainer.getHandlers().put(moduleEvent, new CopyOnWriteArrayList<>());

                    modContainer.getHandlers().get(moduleEvent).add(new EventContainer(modContainer.getInstance(), method, method.getAnnotation(EventHandler.class).priority()));

                    Apollo.log("[" + modContainer.getName() + "] [HANDLE] Registered method " + method.getName().toUpperCase() + " with " + method.getParameterTypes()[0].getCanonicalName() + " event.");

                    modContainer.getHandlers().get(moduleEvent).sort(Comparator.comparingInt(listener -> listener.getPriority().id));
                } else {
                    Apollo.error("[" + modContainer.getName() + "] [HANDLE] Event method " + method.getName().toUpperCase() + " has invalid parameters!");
                }

            }
        }
        return methods;
    }

    /**
     * Register any {@link EventHandler} targets in there corresponding Module
     *
     * @param sharedListeners hashmap of methods containing targets.
     */
    public void registerSharedListeners(HashMap<ModContainer, CopyOnWriteArrayList<Method>> sharedListeners) {
        for (ModContainer modContainer : sharedListeners.keySet()) {
            for (Method method : sharedListeners.get(modContainer)) {

                method.setAccessible(true);
                Class<? extends ModuleEvent> moduleEvent = (Class<? extends ModuleEvent>) method.getParameterTypes()[0];

                for (String name : method.getAnnotation(EventHandler.class).target()) {
                    if (getModContainerByName(name) != null) {
                        ModContainer module = getModContainerByName(name);

                        if (!modContainer.getHandlers().containsKey(moduleEvent))
                            modContainer.getHandlers().put(moduleEvent, new CopyOnWriteArrayList<>());

                        modContainer.getHandlers().get(moduleEvent).add(new EventContainer(module.getInstance(), method,  method.getAnnotation(EventHandler.class).priority()));

                        Apollo.log("[" + modContainer.getName() + "] [EVENT-" + module.getName().toUpperCase() + "] Registered method " + method.getName().toUpperCase() + " with " + method.getParameterTypes()[0].getCanonicalName() + " event.");

                        modContainer.getHandlers().get(moduleEvent).sort(Comparator.comparingInt(listener -> listener.getPriority().id));
                    }
                }
            }
        }
    }

    /**
     * Sets any fields marked with the {@link Instance} to
     * the class instance created for {@link ModContainer}
     *
     * <p>All fields will attempt to change based on static modifier.</p>
     *
     * @param modContainer container class for module
     */
    public void handleInstance(ModContainer modContainer) {
        try {
            Field modifiersField = Field.class.getDeclaredField("modifiers");

            for (Field field : modContainer.getInstance().getClass().getDeclaredFields()) {
                for (Annotation annotation : field.getDeclaredAnnotations()) {
                    if (annotation.annotationType().equals(Instance.class)) {
                        modifiersField.setAccessible(true);
                        modifiersField.setInt(field, field.getModifiers() & ~Modifier.FINAL);
                        field.set(null, modContainer.getInstance());
                        Apollo.log("[" + modContainer.getName() + "] [FIELD] Set field " + field.getName().toUpperCase() + " to " + modContainer.getName() + " instance at " + modContainer.getClass().getCanonicalName());
                    }
                }
            }

        } catch (Exception e) {
            Apollo.error("[" + modContainer.getName() + "] [FIELD] Could not set instance Field : " + e.getMessage());
        }
    }
}

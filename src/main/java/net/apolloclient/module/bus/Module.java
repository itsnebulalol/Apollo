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

import net.apolloclient.event.Priority;
import net.apolloclient.module.Category;
import net.apolloclient.module.bus.event.*;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * This defines a module to be displayed in gui.
 *
 *  <p>Any class found with this annotation applied will be loaded as a module and the instance that is
 * loaded will receive all event calls from as {@link ModuleFactory} as long as the module is enabled when
 * the event is posted. Settings can be added to the module with annotation</p>
 *
 * <ul>
 * <li> {@link Instance } : Track instance of module created by {@link ModuleFactory}</li>
 * <li> {@link EventHandler } : Called on Module Specific functions such as {@code init} or {@code enable}</li>
 * </ul>
 *
 * @author Icovid | Icovid#3888
 * @since b0.2
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface Module {

    /**
     * Name of module to displayed in gui list.
     * <p>Used to define settings in file / must be unique to module</p>
     */
    String name();

    /**
     * Description of module displayed in gui list.
     */
    String description();

    /**
     * Category used to section modules.
     */
    Category category() default Category.UTIL;

    /**
     * Aliases are search terms people can type instead of module
     * name to find the module.
     */
    String[] aliases() default "";

    /**
     * Priority of modules events compared to other modules
     */
    int priority() default 5;

    /**
     * Author of module to be displayed in credits.
     */
    String author() default "Apollo Development Team";

    /**
     * If module should be enabled by default will first opening client.
     */
    boolean enabled() default false;

    /**
     * List of servers module is compatible with.
     * <p>Use this if module is dependant on certain aspects of a server such as chat formatting
     * or scoreboard information.</p>
     */
    String[] recommendedServersIP() default "";

    /**
     * List of servers module is not allowed on.
     * <p>Use this if module should always be disabled to follow server guidelines.</p>
     */
    String[] disallowedServersIP() default "";

    /**
     * Marks a method to handle Minecraft startup and Module initiation Events
     * The method must have a single parameter, one of the following types.
     * <ul>
     * <li> {@link InitializationEvent} : runs when module is constructed.</li>
     * <li> {@link PostInitializationEvent} : runs after all modules have been constructed.</li>
     * <li> {@link EnableEvent} : runs each time module is enabled.</li>
     * <li> {@link DisableEvent} : runs each time module is disabled.</li>
     * <li> {@link ShutDownEvent} : runs when game is closed.</li>
     * </ul>
     *
     * @author Icovid | Icovid#3888
     * @since b0.2
     */
    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.METHOD)
    @interface EventHandler {

        /**
         * Used to call event from another module by inputting the module name.
         */
        String[] target() default "";

        /**
         * Priority over method over other methods in class.
         */
        Priority priority() default Priority.NORMAL;
    }

    /**
     * Populates the annotated field with the module instance create by {@link ModuleFactory}
     *
     * @author Icovid | Icovid#3888
     * @since b0.2
     */
    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.FIELD)
    @interface Instance {
    }
}

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

import net.apolloclient.event.Event;
import net.apolloclient.event.bus.EventContainer;
import net.apolloclient.event.bus.SubscribeEvent;
import net.apolloclient.event.bus.SubscribeEventContainer;
import net.apolloclient.module.Category;
import net.apolloclient.module.ModuleContainer;
import net.apolloclient.module.bus.event.ModuleEvent;

import java.util.HashMap;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Container class to hold basic module information
 * to be displayed in gui and functions for module enabling / disabling
 *
 * <p>Done this way so {@link ModuleContainer} and {@link DraggableModule} can
 * be handled differently within constructor.</p>
 *
 * @author Icovid | Icovid#3888
 * @since b0.2
 */
public interface ModContainer {

    /**
     * Name of module to displayed in gui list.
     * <p>Used to define settings in file / must be unique to module</p>
     */
    String getName();

    /**
     * Description of module displayed in gui list.
     */
    String getDescription();

    /**
     * Category used to section modules.
     */
    Category getCategory();

    /**
     * Aliases are search terms people can type instead of module
     * name to find the module, split aliases with {@code :}.
     */
    String[] getAliases();

    /**
     * Priority of modules events compared to other modules
     */
    int getPriority();

    /**
     * Author of module to be displayed in credits.
     */
    String getAuthor();

    /**
     * If module is currently enabled.
     */
    boolean isEnabled();

    /**
     * List of servers module is compatible with split by {@code :}.
     * <p>Use this if module is dependant on certain aspects of a server such as chat formatting
     * or scoreboard information.</p>
     */
    String[] getRecommendedServersIPs();

    /**
     * List of servers module is not allowed on split by {@code :}.
     * <p>Use this if module should always be disabled to follow server guidelines.</p>
     */
    String[] getDisallowedServersIPs();

    /**
     * Get the actual module class object
     */
    Object getInstance();

    /**
     * Toggle module enabled state
     */
    void toggle();

    /**
     * Set module enabled state to certain value.
     */
    void setEnabled(boolean enabled);

    /**
     * Sets priority of current module and sort {@link ModuleFactory} again.
     *
     * @param priority to set module too.
     */
    void setPriority(int priority);

    /**
     * Tracks all methods annotated with {@link EventHandler}
     *
     * @return HashMap of {@link ModuleEvent} with a list of {@link EventContainer}
     */
    HashMap<Class<? extends ModuleEvent>, CopyOnWriteArrayList<EventContainer>> getHandlers();

    /**
     * Tracks all methods annotated with {@link SubscribeEvent}
     *
     * @return HashMap of {@link Event} with a list of {@link EventContainer}
     */
    HashMap<Class<? extends Event>, CopyOnWriteArrayList<SubscribeEventContainer>> getEvents();

    /**
     * Post an event to module and any module requesting its events
     *
     * @param moduleEvent event to be posted.
     */
    void post(ModuleEvent moduleEvent);
}

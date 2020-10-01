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

package net.apolloclient.module.bus.event;

import net.apolloclient.module.bus.ModContainer;
import net.apolloclient.module.bus.Module.EventHandler;

/**
 * Container class for all module specific event.
 *
 * <p>Any class extending this can be called with
 * the {@link EventHandler} annotation</p>
 *
 * @author Icovid | Icovid#3888
 * @since b0.2
 */
public class ModuleEvent {

    public final ModContainer module;

    /**
     * @param module module event is being called by, really only useful
     *               when different target is set
     */
    public ModuleEvent(ModContainer module) {
        this.module = module;
    }

    /**
     * Get name of the event being called; Used mostly for logs.
     *
     * @return name of the event class
     */
    public final String getEventName() {
        return getClass().getSimpleName();
    }

    /**
     * Get link to event documentation hosted on https://docs.apolloclient.net .
     *
     * @return class name formatted in url
     */
    public final String getEventDocs() {
        return "https://docs.apolloclient.net/EventHandler/" + getEventName();
    }
}

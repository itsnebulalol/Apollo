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

import net.apolloclient.event.Priority;

import java.lang.reflect.Method;

/**
 * Internal data class for storing event listener data.
 *
 * @author Icovid | Icovid#3888
 * @since b0.2
 */
public class EventContainer {

    public final Object instance;
    public final Method method;
    private final Priority priority;

    /**
     * @param instance instance of object to invoke
     * @param method method to invoke
     * @param priority priority of method
     */
    public EventContainer(Object instance, Method method, Priority priority) {
        this.instance = instance;
        this.method = method;
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

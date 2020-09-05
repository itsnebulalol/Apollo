/*⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤
 Copyright (C) 2020-2021 developed by Icovid and Apollo Development Team
 
 This program is free software: you can redistribute it and/or modify
 it under the terms of the GNU Lesser General Public License as published
 by the Free Software Foundation, either version 3 of the License, or
 (at your option) any later version.
  
 This program is distributed in the hope that it will be useful,
 but WITHOUT ANY WARRANTY; without even the implied warranty of
 MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 GNU Lesser General Public License for more details.
  
 You should have received a copy of the GNU Lesser General Public License
 along with this program.  If not, see http://www.gnu.org/licenses/ .
 
 Contact: Icovid#3888 @ https://discord.com
 ⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤*/

package io.apollo.events.bus;

import lombok.Getter;

/** Enum for storing the order in which event listeners are fired.
 * <p>A listener with a {@code HIGH} priority will be fired
 * before a listener with a {@code NORMAL} priority.</p>
 * <p>The priority of a listener is set in the {@link EventSubscriber},
 * but it can be changed at any time with the {@code setPriorityOf}
 * method in {@link EventBus}.</p>
 * @author Nora Cos | Nora#0001
 * @since 1.0.0 **/
public enum Priority {

    HIGH(0),
    NORMAL(1),
    LOW(2);

    @Getter private final int id;
    Priority(int id) {
        this.id = id;
    }
}
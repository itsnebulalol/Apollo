/*⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤
 Copyright (C) 2020-2021 developed by Icovid and Apollo Development Team

 Priority.java is part of Apollo Client. 9/3/20, 8:54 PM
 
 Priority.java can not be copied and/or distributed without the express
 permission of Icovid
 
 Contact: Icovid#3888 @ https://discord.com
 ⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤*/

package io.apollo.events.bus;

import lombok.Getter;

/** Enum for storing the order in which event listeners are fired.
 * <p>A listener with a {@code HIGH} priority will be fired
 * before a listener with a @{@code NORMAL} priority.</p>
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
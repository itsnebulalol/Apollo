/*
 * ⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤
 * Copyright (C) 2020-2021 developed by Icovid and Apollo Development Team
 *
 * Event.java is part of Apollo Client. 2020-09-03, 1:49 p.m.
 *
 * Event.java can not be copied and/or distributed without the express
 * permission of Icovid
 *
 * Contact: Icovid#3888 @ https://discord.com
 * ⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤
 */

package io.apollo.events;

import io.apollo.Apollo;

/**
 * Basic event class.
 * <p>All events will extend this class in some way.</p>
 *
 * @since 1.0.0
 * @author Nora Cos | Nora#0001
 */
public class Event {
    /**
     * Posts the event to the EventBus.
     */
    public void post() {
        Apollo.EVENT_BUS.post(this);
    }
}

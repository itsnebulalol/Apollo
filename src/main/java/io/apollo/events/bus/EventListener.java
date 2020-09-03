/*
 * ⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤
 * Copyright (C) 2020-2021 developed by Icovid and Apollo Development Team
 *
 * EventListener.java is part of Apollo Client. 2020-09-03, 2:00 p.m.
 *
 * EventListener.java can not be copied and/or distributed without the express
 * permission of Icovid
 *
 * Contact: Icovid#3888 @ https://discord.com
 * ⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤
 */

package io.apollo.events.bus;

import io.apollo.Apollo;
import io.apollo.events.Event;
import lombok.Getter;
import lombok.Setter;

import java.lang.reflect.Method;

/**
 * Internal data class for storing event listener data.
 *
 * @since 1.0.0
 * @author Nora Cos | Nora#0001
 */
class EventListener {
    @Getter
    private final Object instance;
    @Getter @Setter
    private Method method;
    @Getter
    private Priority eventPriority;

    public EventListener(Object listener, Method method, Priority eventPriority) {
        instance = listener;
        this.method = method;
        this.eventPriority = eventPriority;
    }

    /**
     * Sets the event priority and sorts the event listeners.
     *
     * @param eventPriority new event priority
     * @param classToSort event class
     */
    void setEventPriority(Priority eventPriority, Class<? extends Event> classToSort) {
        this.eventPriority = eventPriority;
        if (classToSort != null) {
            Apollo.EVENT_BUS.sort(classToSort);
        }
    }
}
/*
 * ⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤
 * Copyright (C) 2020-2021 developed by Icovid and Apollo Development Team
 *
 * ChatSentEvent.java is part of Apollo Client. 2020-09-03, 6:14 p.m.
 *
 * ChatSentEvent.java can not be copied and/or distributed without the express
 * permission of Icovid
 *
 * Contact: Icovid#3888 @ https://discord.com
 * ⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤
 */

package io.apollo.events.impl.chat;

import io.apollo.events.CancelableEvent;
import lombok.Getter;
import lombok.Setter;

/**
 * Fired when the client sends a message to the server.
 *
 * @since 1.0.0
 * @author Nora Cos | Nora#0001
 */
public class PlayerChatEvent extends CancelableEvent {
    @Getter @Setter
    private String message;

    /**
     * @param chatMessage message being sent to the server
     */
    public PlayerChatEvent(String chatMessage) {
        message = chatMessage;
    }
}

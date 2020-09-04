/*⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤
 Copyright (C) 2020-2021 developed by Icovid and Apollo Development Team

 PlayerChatEvent.java is part of Apollo Client. 9/3/20, 8:54 PM

 PlayerChatEvent.java can not be copied and/or distributed without the express
 permission of Icovid

 Contact: Icovid#3888 @ https://discord.com
 ⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤*/

package io.apollo.events.impl.chat;

import io.apollo.events.CancelableEvent;
import lombok.Getter;
import lombok.Setter;

/** Fired when the client sends a message to the server.
 * @author Nora Cos | Nora#0001
 * @since 1.0.0 **/
public class PlayerChatEvent extends CancelableEvent {

    @Getter @Setter private String message;

    /** @param chatMessage message being sent to the server **/
    public PlayerChatEvent(String chatMessage) {
        message = chatMessage;
    }
}

/*⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤
 Copyright (C) 2020-2021 developed by Icovid and Apollo Development Team

 KeyPressedEvent.java is part of Apollo Client. 9/4/20, 9:05 PM

 KeyPressedEvent.java can not be copied and/or distributed without the express
 permission of Icovid

 Contact: Icovid#3888 @ https://discord.com
 ⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤*/

package io.apollo.events.impl.client.input;

import io.apollo.events.Event;
import lombok.Getter;

/** Fired when key is pressed.
 * @author Icovid | Icovid#3888
 * @since 1.0.0 **/
public class KeyPressedEvent extends Event {

    @Getter private final boolean repeatedKey;
    @Getter private final int keyCode;

    /** @param repeatedKey is key event same as last key event
     * @param keyCode int code of key **/
    public KeyPressedEvent(boolean repeatedKey, int keyCode) {
        this.repeatedKey = repeatedKey;
        this.keyCode = keyCode;
    }
}

/*⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤
 Copyright (C) 2020-2021 developed by Icovid and Apollo Development Team

 CancelableEvent.java is part of Apollo Client. 9/3/20, 8:54 PM

 CancelableEvent.java can not be copied and/or distributed without the express
 permission of Icovid

 Contact: Icovid#3888 @ https://discord.com
 ⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤*/

package io.apollo.events;

import lombok.Getter;

/** Cancelable event class.
 * <p>Events that extend this class will be cancelable.
 * This means that if {@code cancel} is called,
 * the action that triggered the event will not happen.</p>
 * @author Nora Cos | Nora#0001
 * @since 1.0.0 **/
public class CancelableEvent extends Event {
    @Getter private boolean canceled = false;

    /** Cancels the event. **/
    public void cancel() {
        canceled = true;
    }
}

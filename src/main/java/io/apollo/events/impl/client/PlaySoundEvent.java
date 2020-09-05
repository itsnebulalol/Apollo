/*⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤
 Copyright (C) 2020-2021 developed by Icovid and Apollo Development Team

 PlaySoundEvent.java is part of Apollo Client. 9/4/20, 8:40 PM

 PlaySoundEvent.java can not be copied and/or distributed without the express
 permission of Icovid

 Contact: Icovid#3888 @ https://discord.com
 ⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤*/

package io.apollo.events.impl.client;

import io.apollo.events.CancelableEvent;
import lombok.Getter;
import net.minecraft.client.audio.ISound;

/** Fired sound is played on client.
 * @author Icovid | Icovid#3888
 * @since 1.0.0 **/
public class PlaySoundEvent extends CancelableEvent {

    @Getter private final ISound sound;

    /** @param sound sound played **/
    public PlaySoundEvent(ISound sound) {
        this.sound = sound;
    }
}

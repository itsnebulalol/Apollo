/*⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤
 Copyright (C) 2020-2021 developed by Icovid and Apollo Development Team

 LoadWorldEvent.java is part of Apollo Client. 9/4/20, 5:20 PM

 LoadWorldEvent.java can not be copied and/or distributed without the express
 permission of Icovid

 Contact: Icovid#3888 @ https://discord.com
 ⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤*/

package io.apollo.events.impl.world;

import io.apollo.events.Event;
import lombok.Getter;
import net.minecraft.client.multiplayer.WorldClient;

/** Fired when player world is changed.
 * @author Icovid | Icovid#3888
 * @since 1.0.0 **/
public class LoadWorldEvent extends Event {

    @Getter private final WorldClient worldClient;

    /** @param worldClient new world client loaded */
    public LoadWorldEvent(WorldClient worldClient) {
        this.worldClient = worldClient;
    }
}

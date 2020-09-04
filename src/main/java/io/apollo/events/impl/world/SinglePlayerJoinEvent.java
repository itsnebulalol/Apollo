/*⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤
 Copyright (C) 2020-2021 developed by Icovid and Apollo Development Team

 SinglePlayerJoinEvent.java is part of Apollo Client. 9/4/20, 2:40 PM

 SinglePlayerJoinEvent.java can not be copied and/or distributed without the express
 permission of Icovid

 Contact: Icovid#3888 @ https://discord.com
 ⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤*/

package io.apollo.events.impl.world;

import io.apollo.events.Event;
import lombok.Getter;
import net.minecraft.world.WorldSettings;

/** Fired when player joins single player world.
 * @author Icovid | Icovid#3888
 * @since 1.0.0 **/
public class SinglePlayerJoinEvent extends Event {

    @Getter private final String folderName;
    @Getter private final String worldName;
    @Getter private final WorldSettings worldSettings;

    /**@param folderName name of folder world file is located in.
     * @param worldName name of world
     * @param worldSettings settings of world **/
    public SinglePlayerJoinEvent(String folderName, String worldName, WorldSettings worldSettings) {
        this.folderName = folderName;
        this.worldName = worldName;
        this.worldSettings = worldSettings;
    }
}

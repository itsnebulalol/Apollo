/*⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤
 Copyright (C) 2020-2021 developed by Icovid and Apollo Development Team
 
 This program is free software: you can redistribute it and/or modify
 it under the terms of the GNU Affero General Public License as published
 by the Free Software Foundation, either version 3 of the License, or
 (at your option) any later version.
 
 This program is distributed in the hope that it will be useful,
 but WITHOUT ANY WARRANTY; without even the implied warranty of
 MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 GNU Affero General Public License for more details.
 
 You should have received a copy of the GNU Affero General Public License
 along with this program.  If not, see https://www.gnu.org/licenses/.
 
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

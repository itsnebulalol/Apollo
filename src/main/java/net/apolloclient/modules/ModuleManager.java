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

package net.apolloclient.modules;

import net.apolloclient.modules.impl.util.WindowedFullscreenModule;

import java.util.ArrayList;

/** Holds all modules and sets up module settings.
 * @author Icovid | Icovid#3888
 * @since 1.0.0 **/
public class ModuleManager {

    // Settings file used to save setting states.
    public final ArrayList<Module> modules = new ArrayList<>();

    // Public module instances.
//    public final DiscordRPModule discordRPModule = new DiscordRPModule(); TODO: FIX ERRORS
    public final WindowedFullscreenModule windowedFullscreenModule = new WindowedFullscreenModule();

    // Add all modules here.
    public final void preInitialisation() {
//        this.modules.add(discordRPModule);
        this.modules.add(windowedFullscreenModule);
    }

    /** Constructor to created ModuleManager. **/
    public ModuleManager() {
        this.preInitialisation();
    }

    /** Called when game is closed.
     * <p>used to shutdown modules</p>
     * @return number of modules shutdown. **/
    public int shutdown() {
        for (Module module : this.modules)
            module.shutdown();
        return this.modules.size();
    }
}

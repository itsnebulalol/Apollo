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

import net.apolloclient.modules.impl.gameplay.AutoGGModule;
import net.apolloclient.modules.impl.util.DiscordRPModule;
import net.apolloclient.modules.impl.util.QuickplayModule;

import java.util.ArrayList;

/** Holds all modules and sets up module settings.
 * @author Icovid | Icovid#3888
 * @since 1.0.0 **/
public class ModuleManager {

    // Settings file used to save setting states.
    public final ArrayList<Module> modules = new ArrayList<>();

    // Public module instances.
    public final AutoGGModule autoGG = new AutoGGModule();
    public final QuickplayModule quickplay = new QuickplayModule();
    public final DiscordRPModule discordRP = new DiscordRPModule();

    // Add all modules here.
    public final int preInitialisation() {
        this.modules.add(autoGG);
        this.modules.add(quickplay);
        this.modules.add(discordRP);
        return (int) modules.stream().filter(Module::isEnabled).count();
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

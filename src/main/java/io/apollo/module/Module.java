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

package io.apollo.module;

import io.apollo.Apollo;
import io.apollo.settings.Setting;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

/** Module constructor for all mods.
 * @author Icovid | Icovid#3888
 * @since 1.0.0 **/
public class Module {

    @Getter private final String name;
    @Getter private final String description;
    @Getter private final Category category;
    @Setter private int priority = 5;
    @Setter @Getter private boolean enabled;
    public final ArrayList<Setting> settings = new ArrayList<>();

    public Module(String name, String description, Category category) { this(name, description, category, false); }
    /** Main constructor used for all modules.
     * @param name name of module
     * @param description description of module
     * @param category category of module
     * @param enabled is module toggled **/
    public Module(String name, String description, Category category, boolean enabled) {
        this.name = name;
        this.description = description;
        this.category = category;
        this.enabled = enabled;
        this.moduleSetup();
    }

    /** Get module stats from file and log creation to console **/
    public final void moduleSetup() {
        Apollo.log("❖ " + name + " module initiated with a category of " + category.toString().toLowerCase() + " and a toggle state of " + this.enabled);
        this.setupModule();
    }

    /** Set enabled state opposite of what it is currently.
     * @return Boolean it was set too **/
    public final Boolean toggle() {
        this.setEnabled(!enabled);
        if (enabled) { this.onModuleEnable(); return true; }
        else { this.onModuleDisable(); return false; }
    }

    /** Called when module is enabled and registers the event manager **/
    public final void onModuleEnable() { this.onEnabled(); }
    /** Called when module is disabled and unregisters the event manager **/
    public final void onModuleDisable() { this.onDisable(); }

    /** Called when module is enabled.
     * @see #toggle() **/
    public void onEnabled() {}
    /** Called when module is disabled.
     * @see #toggle() **/
    public void onDisable() {}
    /** Called on startup
     * @see #moduleSetup() **/
    public void setupModule() {}
}

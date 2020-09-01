/*⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤
 Copyright (C) 2020-2021 developed by Icovid and Apollo Development Team
 All Contributors can be found in the README.md

 Module.java is part of Apollo Client. 8/31/20, 8:51 PM

 Module.java can not be copied and/or distributed without the express
 permission of Icovid

 Contact: Icovid#3888 @ https://discord.com
 ⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤*/

package io.apollo.modulemanager;

import io.apollo.Apollo;
import io.apollo.settingsmanager.Setting;

import java.util.ArrayList;

/** Module constructor for all mods.
 * @author Icovid | Icovid#3888
 * @since 1.0.0 **/
public class Module {

    public final String name;
    public final String description;
    public final Category category;
    private boolean enabled;
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

    /** Weather or not module is enabled.
     * @return boolean is enabled **/
    public boolean isEnabled() { return enabled; }

    /** Sets module enabled stats to value.
     * @param enabled value it will be set **/
    public void setEnabled(Boolean enabled) { this.enabled = enabled; }

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

package io.apollo.modulemanager;

import io.apollo.Apollo;

import java.util.ArrayList;

public class Module {

    /* Initiate Values in Private Variable for Constructor */
    public final String name;
    public final String description;
    public final Category category;
    private int keybind;
    private Boolean enabled;
//    public ArrayList<Setting> settings;

    public Module(String name, String description, Category category, Boolean enabled, int keybind) {
        this.name = name;
        this.description = description;
        this.category = category;
        this.enabled = enabled;
        this.keybind = keybind;
//        this.settings = new ArrayList<>();

        this.setupModule();
        this.moduleSetup();
    }

    /* Called When Module in Constructed */
    public void setupModule() {
        // TODO: LOAD MODULE STATE
//        if (this.isEnabled()) Apollo.EVENT_BUS.register(this);
        Apollo.log("Initialized Module : [NAME] " + this.name + " [CATEGORY] " + this.category.toString().toUpperCase() + " [ENABLED] " + this.enabled);
    }

    public final void setEnabled(boolean enabled) { this.enabled = enabled;}
    public final boolean isEnabled() { return enabled; }
    public final void toggle() {
        this.onToggle();
        this.setEnabled(!this.enabled);
//        if (this.isEnabled()) this.onEnable();
//        else this.onDisable();
    }

    /* Called when Module Enabled / Disabled */
//    public final void onEnable() { Apollo.EVENT_BUS.register(this); this.onModEnable(); }
//    public final void onDisable() { Apollo.EVENT_BUS.unregister(this); this.onModDisable(); }

    /* Add or Remove Setting from Settings List */
//    public final void addSetting(Setting setting) { this.settings.add(setting); this.onAddSetting(setting); }
//    public final void removeSetting(Setting setting) { this.settings.remove(setting); this.onRemoveSetting(setting); }
    // Get Setting from List by Name
//    public final Setting getSettingbyName(String name) { for (Setting setting : settings) { if (setting.name.equalsIgnoreCase(name)) { return setting; } }return null; }

    /* Hooks for Overrides to Avoid Damaging System */
    public void onModEnable() {}
    public void onModDisable() {}
    public void onToggle() {}
//    public void onAddSetting(Setting setting) {}
//    public void onRemoveSetting(Setting setting) {}
    public void moduleSetup() { }
}

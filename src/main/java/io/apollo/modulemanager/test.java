package io.apollo.modulemanager;

import io.apollo.settingsmanager.SettingsValue;

public class test extends Module {

    public test(String name, String description, Category category, Boolean enabled, int keybind) {
        super(name, description, category, enabled, keybind);
    }

    @SettingsValue(description = "Adjust Box Outline Width", key = "box") public float boxOutlineWidth = 0.6f;

}

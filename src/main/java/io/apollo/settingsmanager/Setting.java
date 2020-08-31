package io.apollo.settingsmanager;

import java.lang.reflect.Field;

public class Setting {

    public final String name;
    public final String description;
    public final String jsonPath;
    public final Field field;

    public Setting(Field field, String name, String description, String jsonPath) {
        this.field = field;
        this.name = name;
        this.description = description;
        this.jsonPath = jsonPath;
    }
}

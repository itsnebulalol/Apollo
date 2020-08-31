package io.apollo.settingsmanager;

import com.google.common.reflect.TypeToken;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.HashMap;

public class SettingsManager {

    public static final HashMap<String, Field> settingObjectHashMap = new HashMap<>();

    public static void registerSettings(Object object) {
        for (Class<?> clazz : new Class<?>[] { object.getClass(), object.getClass().getSuperclass() }) {
            for (Class<?> classObject : TypeToken.of(clazz).getTypes().rawTypes()) {
                for (Field field : classObject.getDeclaredFields()) {
                    if (field.getAnnotation(SettingsValue.class) != null) {
                        if (!field.getAnnotation(SettingsValue.class).key().equals("null") && object instanceof Module) {
                            settingObjectHashMap.put(((Module) object).name + "." + field.getAnnotation(SettingsValue.class).key() + "." + field.getName(), field); }
                        else if (!field.getAnnotation(SettingsValue.class).key().equals("null")) {
                        settingObjectHashMap.put(field.getAnnotation(SettingsValue.class).key() + "." + field.getName(), field);
                        } else if (object instanceof Module) {
                            settingObjectHashMap.put(((Module) object).name + "." + field.getName(), field);
                        } else { settingObjectHashMap.put(field.getName(), field); }
                    }
                }
            }
        }
    }

    public static void saveSettingToFile(String jsonPath, Field field) throws IOException, ParseException {

    }

    public static void loadSettingFromFile(String jsonPath, Field field) throws IOException, ParseException {

    }
}

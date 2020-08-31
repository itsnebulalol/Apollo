package io.apollo.settingsmanager;

import com.google.common.reflect.TypeToken;
import io.apollo.Apollo;
import io.apollo.modulemanager.Module;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;

public class SettingsManager {

    public static final ArrayList<Setting> settings = new ArrayList<>();

    public static void registerSettings(Object object) {
        for (Class<?> clazz : new Class<?>[] { object.getClass(), object.getClass().getSuperclass() }) {
            for (Class<?> classObject : TypeToken.of(clazz).getTypes().rawTypes()) {
                for (Field field : classObject.getDeclaredFields()) {
                    if (field.getAnnotation(SettingsValue.class) != null) {
                    StringBuilder jsonPathBuilder = new StringBuilder("");
                    if (object instanceof Module) jsonPathBuilder.append(((Module) object).name + ".");
                    jsonPathBuilder.append(field.getAnnotation(SettingsValue.class).key() + ".");
                    jsonPathBuilder.append(field.getName());
                    Setting setting = new Setting(field, field.getName(), field.getAnnotation(SettingsValue.class).description(), jsonPathBuilder.toString());
                    settings.add(setting);
                    Apollo.log(setting.name + " : " + setting.description + " : " + setting.jsonPath); }
                }
            }
        }
    }

    public static void saveSettingToFile(String jsonPath, Field field) throws IOException, ParseException {

    }

    public static void loadSettingFromFile(String jsonPath, Field field) throws IOException, ParseException {

    }
}

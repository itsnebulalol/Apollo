/*⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤
 Copyright (C) 2020-2021 developed by Icovid and Apollo Development Team

 Save.java is part of Apollo Client. 9/3/20, 8:54 PM

 Save.java can not be copied and/or distributed without the express
 permission of Icovid

 Contact: Icovid#3888 @ https://discord.com
 ⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤*/
package io.apollo.utils.saving.OtherWay;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.stream.JsonWriter;
import io.apollo.Apollo;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/** Saving booleans to a json (and other things prob)
 * @author Pinkulu | pinkulu#6260 **/
public class Save {

    /** run this whenever a change is made **/
    public static void saveConfig() {
        try {
            File file = new File(Apollo.apolloDirectory, "toggle.json");
            if (!file.exists()) {
                file.getParentFile().mkdirs();
                file.createNewFile();
            }
            JsonWriter writer = new JsonWriter(new FileWriter(file, false));
            writeJson(writer);
            writer.close();
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }

    /** run this when the game is Initializing **/
    private void loadConfig() {
        try {
            File file = new File(Apollo.apolloDirectory, "toggle.json");
            if (file.exists())
                readJson(file);
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }

    /** add the booleans from example **/
    public void readJson(File file) throws Throwable {
        JsonParser parser = new JsonParser();
        JsonObject json = parser.parse(new FileReader(file)).getAsJsonObject();
        json = json.getAsJsonObject("Toggle");

        //io.apollo.path.to.Class.ThenTheBoolean = json.get("name").getAsBoolean();

    }

    /** add the booleans from example **/
    public static void writeJson(JsonWriter writer) throws IOException {
        writer.setIndent(" ");
        writer.beginObject();
        writer.name("First thing");
        writer.beginObject();
        // writer.name("name").value(io.apollo.path.to.Class.ThenTheBoolean);
        writer.endObject();
        writer.endObject();
    }
}

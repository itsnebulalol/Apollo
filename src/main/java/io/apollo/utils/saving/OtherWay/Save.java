package io.apollo.utils.saving.OtherWay;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.stream.JsonWriter;
import io.apollo.Apollo;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Save {
    /** Saving booleans to a json (and other things prob)
     * @author Pinkulu
     * This will save it all in 1 file
     * **/

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

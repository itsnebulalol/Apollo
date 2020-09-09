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
package io.apollo.utils.saving.OtherWay;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.stream.JsonWriter;
import io.apollo.Apollo;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Saving booleans to a json (and other things prob)
 *
 * @author Pinkulu | pinkulu#6260 *
 */
public class Save {

  /** run this whenever a change is made * */
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

  /** run this when the game is Initializing * */
  public static void loadConfig() {
    try {
      File file = new File(Apollo.apolloDirectory, "toggle.json");
      if (file.exists()) readJson(file);
    } catch (Throwable e) {
      e.printStackTrace();
    }
  }

  /** add the booleans from example * */
  public static void readJson(File file) throws Throwable {
    JsonParser parser = new JsonParser();
    JsonObject json = parser.parse(new FileReader(file)).getAsJsonObject();
    json = json.getAsJsonObject("Toggle");

    // io.apollo.path.to.Class.ThenTheBoolean = json.get("name").getAsBoolean();

  }

  /** add the booleans from example * */
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

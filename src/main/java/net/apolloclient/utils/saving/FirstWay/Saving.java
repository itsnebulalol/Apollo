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
package net.apolloclient.utils.saving.FirstWay;

import java.io.File;

public class Saving {
  public Saving() {
    Saving.saveIsEnabledToFile("ExampleMod", false);
  }

  /**
   * Saving booleans to a json
   *
   * @author Pinkulu | pinkulu#6260
   * @since v1.0.0 this will make a folder for each mod, and add a toggle.json in each folder (not
   *     sure if it will work as this is taken from my old client and will prob need some
   *     modifiying, cant test it out rn bc there arnt mods yet)
   *     <p>run this whenever a change is made *
   */
  public static File getFolder(String mod) {
    File file = new File(FileManager.getModsDirectory(), mod);
    file.mkdirs();
    return file;
  }

  public static void saveIsEnabledToFile(String mod, Boolean b) {
    FileManager.writeJsonToFile(new File(getFolder(mod), "toggle.json"), b);
  }

  public static Boolean loadEnabledFromFile(String mod) {
    Boolean b = FileManager.readFromJson(new File(getFolder(mod), "toggle.json"), Boolean.class);
    if (b == null) {
      b = false;
      saveIsEnabledToFile(mod, b);
    }
    return b;
  }
  /**
   * @example is the manual/mod class do: public void setEnabled(boolean isEnabled) {
   *     super.setEnabled(Saving.loadEnabledFromFile("ExampleMod"));
   *     Saving.saveIsEnabledToFile("ExampleMod", isEnabled); }
   */
}

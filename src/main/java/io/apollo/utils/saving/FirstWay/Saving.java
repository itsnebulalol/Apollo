/*⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤
 Copyright (C) 2020-2021 developed by Icovid and Apollo Development Team

 Saving.java is part of Apollo Client. 9/3/20, 8:54 PM

 Saving.java can not be copied and/or distributed without the express
 permission of Icovid

 Contact: Icovid#3888 @ https://discord.com
 ⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤*/
package io.apollo.utils.saving.FirstWay;

import java.io.File;

public class Saving {
    /** Saving booleans to a json
     * @author Pinkulu | pinkulu#6260
     * @since v1.0.0
     * this will make a folder for each mod, and add a toggle.json in each folder
     * (not sure if it will work as this is taken from my old client and will prob need some modifiying, cant test it out rn bc there arnt mods yet)

     run this whenever a change is made **/
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
    public Saving(){
        Saving.saveIsEnabledToFile("ExampleMod", false);
    }
    /**
    * @example
     *  is the manual/mod class do:
     *      public void setEnabled(boolean isEnabled) {
     *         super.setEnabled(Saving.loadEnabledFromFile("ExampleMod"));
     *         Saving.saveIsEnabledToFile("ExampleMod", isEnabled);
     *     }
    * */
}

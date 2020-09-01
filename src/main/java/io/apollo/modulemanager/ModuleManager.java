/*⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤
 Copyright (C) 2020-2021 developed by Icovid and Apollo Development Team
 All Contributors can be found in the README.md

 ModuleManager.java is part of Apollo Client. 8/31/20, 9:16 PM

 ModuleManager.java can not be copied and/or distributed without the express
 permission of Icovid

 Contact: Icovid#3888 @ https://discord.com
 ⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤*/

package io.apollo.modulemanager;

import io.apollo.Apollo;

import java.io.*;
import java.util.ArrayList;

/** Holds all modules and sets up module settings.
 * @author Icovid | Icovid#3888
 * @since 1.0.0 **/
public class ModuleManager {

    // Settings file used to save setting states
    public final File settingsFile;
    public final ArrayList<Module> modules = new ArrayList<>();

    /** Constructor to created ModuleManager.
     * @param settingsFile File to save module stats to **/
    public ModuleManager(File settingsFile) {
        this.settingsFile = settingsFile;
        this.preInitialisation();
        try {
            if (!settingsFile.exists()) settingsFile.createNewFile();
        }
        catch (IOException exception) { Apollo.log("Unable to Create Settings File! Aborting Module States"); }
    }

    public final void preInitialisation() {
        this.modules.add(new Module("test", "lol", Category.TEST, 0, true));
    }
}

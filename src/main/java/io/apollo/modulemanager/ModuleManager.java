/*⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤
 Copyright (C) 2020-2021 developed by Icovid and Apollo Development Team

 ModuleManager.java is part of Apollo Client. 9/3/20, 8:54 PM

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
    // TODO: setup module objects in file
    public ModuleManager(File settingsFile) {
        this.settingsFile = settingsFile;
        this.preInitialisation(); }

    public final void preInitialisation() {
        this.modules.add(new Module("test", "lol", Category.TEST, true));
    }
}

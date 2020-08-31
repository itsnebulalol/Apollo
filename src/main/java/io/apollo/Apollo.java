/*
 * ****************************************************************
 *  Copyright (C) 2020-2021 developed by Icovid
 *
 *  Apollo.java is part of Apollo Client. [8/28/20, 2:36 PM]
 *
 *  Apollo.java can not be copied and/or distributed without the express
 *  permission of Icovid
 *
 *  Contact: Icovid#3888
 * ****************************************************************
 */

package io.apollo;

import io.apollo.modulemanager.Category;
import io.apollo.modulemanager.test;
import io.apollo.settingsmanager.SettingsManager;

import java.text.SimpleDateFormat;
import java.util.Date;

/** Main class for Apollo; holds all instances used through out client.
 * @author Icovid | Icovid#3888
 * @since 1.0.0 **/
public class Apollo {

    // Apollo information used for display title and credits info.
    public static final String NAME = "Apollo", VERSION = "b0.1", MCVERSION = "1.8.9";
    public static final String[] DEVELOPERS = {"Icovid", "Nora", "isXander"};

    // Public client instance used to retrieve any aspect of Apollo.
    public static final Apollo instance = new Apollo();

    // Main constructor used to instantiate all aspects of Apollo.
    public Apollo() {
        SettingsManager.registerSettings(new test("TEST", "idk", Category.PLAYER, true, 0));
    }

    /** Log Apollo instance stats after construction. **/
    public void postInitialisation() { log("Apollo Initiation Finished with 0 Modules and 0 Settings! ");
    SettingsManager.log();
    }

    /** Used to log Apollo messages to console.
     * @param message any string to be displayed in console. **/
    public static void log (String... message) { for (String out : message)  System.out.println("[" + new SimpleDateFormat("HH:mm:ss").format(new Date()) + "] [Apollo] " + out); }

}

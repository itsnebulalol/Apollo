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

import io.apollo.utils.ApolloFontRenderer;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

/* Main class for Apollo; holds all instances used through out client */
public class Apollo {

    // Apollo information used for display title and credits info.
    public static final String NAME = "Apollo", VERSION = "b0.1", MCVERSION = "1.8.9";
    public static final String[] DEVELOPERS = {"Icovid", "Nora", "isXander"};

    // Public client instance used to retrieve any aspect of Apollo.
    public static final Apollo instance = new Apollo();

    // Main constructor used to instanciate all aspects of Apollo.
    public Apollo() { }

    /** Log Apollo instance stats after construction. **/
    public void postInitialisation() { log("Apollo Initiation Finished with 0 Modules and 0 Settings!"); }

    /** Used to log Apollo messages to console
     * @param message any string to be displayed in console. **/
    public static void log (String... message) { for (String out : message)  System.out.println("[" + new SimpleDateFormat("HH:mm:ss").format(new Date()) + "] [Apollo] " + out); }

}

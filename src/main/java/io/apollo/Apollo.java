/*⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤
 Copyright (C) 2020-2021 developed by Icovid and Apollo Development Team
 All Contributors can be found in the README.md

 Apollo.java is part of Apollo Client. 8/31/20, 10:47 PM

 Apollo.java can not be copied and/or distributed without the express
 permission of Icovid

 Contact: Icovid#3888 @ https://discord.com
 ⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤*/

package io.apollo;

import io.apollo.modulemanager.Module;
import io.apollo.modulemanager.ModuleManager;
import io.apollo.utils.JsonUtils;
import org.json.simple.JSONObject;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

/** Main class for Apollo; holds all instances used through out client.
 * @author Icovid | Icovid#3888
 * @since 1.0.0 **/
public class Apollo {

    // Apollo information used for display title and credits info.
    public static final String NAME = "Apollo", VERSION = "b0.1", MCVERSION = "1.8.9";
    public static final String[] DEVELOPERS = {"Icovid", "Nora", "isXander"};

    public static final File apolloDirectory = new File(System.getProperty("user.dir") + "/" + NAME.toLowerCase());
    public static final File settingsFile = new File(System.getProperty("user.dir") + "/" + NAME.toLowerCase() + "/settings.json");

    // Public client instance used to retrieve any aspect of Apollo.
    public static final Apollo instance = new Apollo();
    public final ModuleManager moduleManager;

    // Main constructor used to instantiate all aspects of Apollo.
    public Apollo() {
        if (!apolloDirectory.exists()) { try { apolloDirectory.mkdirs();  } catch (Exception ignored) { } log("Created Apollo Directory: " + apolloDirectory.getAbsolutePath()); }
        this.moduleManager = new ModuleManager(settingsFile);
        Apollo.log("Testing Json Utils");
        JSONObject object = JsonUtils.readJSONFromFile(settingsFile);
        JsonUtils.getOrCreateJSONObject(object, "mod/test/1");
        JsonUtils.writeJSONtoFile(object, settingsFile);
    }

    /** Log Apollo instance stats after construction. **/
    public void postInitialisation() { log("Apollo Initiation Finished with 0 Modules and 0 Settings! "); }

    /** Used to log Apollo messages to console.
     * @param message any string to be displayed in console. **/
    public static void log (String... message) { for (String out : message)  System.out.println("[" + new SimpleDateFormat("HH:mm:ss").format(new Date()) + "] [Apollo] " + out); }

}

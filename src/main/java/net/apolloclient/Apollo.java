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

package net.apolloclient;

import net.apolloclient.events.bus.EventBus;
import net.apolloclient.events.bus.EventSubscriber;
import net.apolloclient.events.impl.client.input.KeyPressedEvent;
import net.apolloclient.hud.ModulesGui;
import net.apolloclient.hud.framework.objects.Rectangle;
import net.apolloclient.modules.ModuleManager;
import net.minecraft.client.Minecraft;
import org.lwjgl.input.Keyboard;

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
    public static final Apollo INSTANCE = new Apollo();
    public static final EventBus EVENT_BUS = new EventBus();
    public static final ModuleManager MODULE_MANAGER = new ModuleManager();

    public Rectangle rectangle = new Rectangle("test")
            .position(10, 10)
            .outline(15)
            .round(10).build();

    // Main constructor used to instantiate all aspects of Apollo.
    public Apollo() {
        log("Starting Client!");
        if (!apolloDirectory.exists()) {
            try { apolloDirectory.mkdirs();  } catch (Exception ignored) { }
            log("Created Apollo Directory: " + apolloDirectory.getAbsolutePath());
        }
        //log(rectangle.getName() + ":" + rectangle.getWidth() + ":" + rectangle.getHeight());
    }

    /** Log Apollo instance stats after construction. **/
    public void postInitialisation() {
        log("Apollo Initiation Finished with 0 Modules and 0 Settings! ");
        MODULE_MANAGER.preInitialisation();
        Apollo.EVENT_BUS.register(this);
    }

    // Called when game shuts down.
    public void shutdown() {
        log("Closing Client!");
        log("Shutdown " + MODULE_MANAGER.shutdown() + " modules!");
    }

    /** Used to log Apollo messages to console.
     * @param message any string to be displayed in console. **/
    public static void log (String... message) { for (String out : message) System.out.println("[" + new SimpleDateFormat("HH:mm:ss").format(new Date()) + "] [Apollo] " + out); }

    /** Used to log Apollo errors to console.
     * @param message any string to be displayed in console. **/
    public static void error (String... message) { for (String out : message) System.err.println("[" + new SimpleDateFormat("HH:mm:ss").format(new Date()) + "] [Apollo-Error] " + out); }


    // TEST
    @EventSubscriber
    public void onKeyDown (KeyPressedEvent event) {
        if (event.getKeyCode() == Keyboard.KEY_RSHIFT) Minecraft.getMinecraft().displayGuiScreen(new ModulesGui());
    }
}
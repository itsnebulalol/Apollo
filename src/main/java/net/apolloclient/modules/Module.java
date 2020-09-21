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

package net.apolloclient.modules;

import net.apolloclient.Apollo;
import net.apolloclient.settings.Setting;
import org.jetbrains.annotations.NotNull;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.stream.Collectors;

/** Module constructor for all mods.
 * @author Icovid | Icovid#3888
 * @since 1.0.0 **/
public class Module {

    private final String name;
    private final String description;
    private final Category category;
    // Priority module events will be triggered lowest to highest - default is 5.
    private int priority = 5;
    private boolean enabled;

    // TODO: settings system on per module basis
    private final ArrayList<Setting> settings = new ArrayList<>();

    // Errors thrown by module - logs events, startup, enable and disable.
    private final HashMap<String, Exception> errors = new HashMap<>();
    // Error type for HashMap above.
    public static enum ExceptionType{ STARTUP, ENABLE, DISABLE, EVENT, IO }
    // TODO: redo event manager to incorporate error handling

    /** @param name name of module
     * @param description description of module
     * @param category category of module
     * @implNote enabled defaulted to false. **/
    public Module(String name, String description, Category category) { this(name, description, category, false); }
    /** @param name name of module
     * @param description description of module
     * @param category category of module
     * @param enabled is module toggled **/
    public Module(String name, String description, Category category, boolean enabled) {
        this.name = name;
        this.description = description;
        this.category = category;
        setEnabled(enabled);
        this.moduleSetup();
    }

    /** Get module stats from file and log creation to console **/
    public final void moduleSetup() {
        Apollo.log("Initiating " + this.name.toUpperCase() + "!");
        try { this.setup(); } catch (Exception exception) { handleException(ExceptionType.STARTUP, exception); }
    }

    /** Set enabled state opposite of what it is currently.
     * @return boolean it was set too **/
    @NotNull public final Boolean toggle() {
        setEnabled(!isEnabled());
        return isEnabled();
    }

    /** Called when module is enabled and registers the event manager **/
    public final void onModuleEnable() { try { this.onEnabled(); } catch (Exception exception) { handleException(ExceptionType.ENABLE, exception); } }
    /** Called when module is disabled and unregisters the event manager **/
    public final void onModuleDisable() { try { this.onDisable(); } catch (Exception exception) { handleException(ExceptionType.DISABLE, exception); } }

    /** Getters and Setters used because i don't know how to make lombok final. **/
    public final String getName () { return name; }
    public final String getDescription () { return description; }
    public final Category getCategory () { return category; }
    public final int getPriority () { return priority; }
    public final boolean isEnabled () { return enabled; }
    public final ArrayList<Setting> getSettings () { return settings; }
    public final HashMap<String, Exception> getErrors () { return errors; }
    /** Set priority of module in event system **/
    public final void setPriority (int priority) { this.priority = priority; }

    /** Set toggle state of module
     * @param enabled toggle state
     * @return boolean it was set to */
    public boolean setEnabled (boolean enabled) {
        if (canBeEnabled() || !enabled) this.enabled = !enabled;
        if (this.isEnabled()) {
            Apollo.EVENT_BUS.register(this);
            this.onModuleEnable();
        } else {
            Apollo.EVENT_BUS.unregister(this);
            this.onModuleDisable();
        }
        return this.enabled;
    }

    /** Called when module is enabled.
     * @see #toggle() **/
    public void onEnabled() throws Exception {}
    /** Called when module is disabled.
     * @see #toggle() **/
    public void onDisable() throws Exception {}
    /** Called on startup
     * @see #moduleSetup() **/
    public void setup () throws Exception {}
    /** Called on Shutdown **/
    public void shutdown() {}
    /** Queried when enabled **/
    public boolean canBeEnabled() { return true; }

    /** Called when module encounters an exception
     * @param exception encountered **/
    public void handleException(@NotNull ExceptionType exceptionType, Exception exception) {
        this.errors.put(exceptionType.toString().toUpperCase() + "Exception - " + this.name, exception);
        Apollo.error("[" + this.name + "]" + " " + exceptionType.toString().toUpperCase() + " Exception - " + exception.toString());
    }

    /** Download data from data base.
     * @param filename name of file in database / local
     * @return string of file
     * @throws Exception any exception encountered  **/
    public final String getDataFromUrlOrLocal(String filename) throws Exception {
        try {
            URLConnection urlConnection = new URL("https://static.apolloclient.net/" + filename).openConnection();
            urlConnection.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 5.1; rv:19.0) Gecko/20100101 Firefox/19.0");
            urlConnection.connect();
            BufferedReader serverResponse = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
            String response = serverResponse.lines().collect(Collectors.joining());
            serverResponse.close();
            return response;
        } catch (Exception exception) {
            this.handleException(ExceptionType.IO, exception);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(Module.class.getResourceAsStream("/other/" + filename)));
            String response = bufferedReader.lines().collect(Collectors.joining());
            bufferedReader.close();
            return response;
        }
    }
}

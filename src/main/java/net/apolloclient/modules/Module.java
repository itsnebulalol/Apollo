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

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.stream.Collectors;
import net.apolloclient.Apollo;
import net.apolloclient.settings.Setting;
import org.jetbrains.annotations.NotNull;

/**
 * Module constructor for all mods.
 *
 * @author Icovid | Icovid#3888
 * @since 1.0.0
 */
public class Module {

  private final String name;
  private final String description;
  private final Category category;
  // Priority module events will be triggered lowest to highest - default is 5.
  private int priority = 5;
  private boolean enabled;
  // TODO: settings system on per module basis
  private final ArrayList<Setting> settings = new ArrayList<>();

  /**
   * @param name name of module
   * @param description description of module
   * @param category category of module
   * @implNote enabled defaulted to false.
   */
  public Module(String name, String description, Category category) {
    this(name, description, category, false);
  }
  /**
   * @param name name of module
   * @param description description of module
   * @param category category of module
   * @param enabled is module toggled
   */
  public Module(String name, String description, Category category, boolean enabled) {
    this.name = name;
    this.description = description;
    this.category = category;
    setEnabled(enabled);
    this.moduleSetup();
  }

  /** Get module stats from file and log creation to console */
  public final void moduleSetup() {
    Apollo.log("Initiating " + this.name.toUpperCase() + "!");
    this.setup();
  }

  /**
   * Set enabled state opposite of what it is currently.
   *
   * @return boolean it was set too
   */
  @NotNull
  public final Boolean toggle() {
    setEnabled(!isEnabled());
    return isEnabled();
  }

  /** Called when module is enabled and registers the event manager */
  public final void onModuleEnable() {
    this.onEnabled();
  }
  /** Called when module is disabled and unregisters the event manager */
  public final void onModuleDisable() {
    this.onDisable();
  }

  /** Get name of the module. */
  public final String getName() {
    return name;
  }
  /** Get description of the module. */
  public final String getDescription() {
    return description;
  }
  /** Get Category of the module. */
  public final Category getCategory() {
    return category;
  }
  /** Get priority of module. */
  public final int getPriority() {
    return priority;
  }
  /** If module is enabled / events are being called. */
  public final boolean isEnabled() {
    return enabled;
  }
  /** Get settings for the gui. */
  public final ArrayList<Setting> getSettings() {
    return settings;
  }
  /** Set priority of module in event system */
  public final void setPriority(int priority) {
    this.priority = priority;
  }

  /**
   * Set toggle state of module
   *
   * @param enabled toggle state
   * @return boolean it was set to
   */
  public boolean setEnabled(boolean enabled) {
    if (canBeEnabled() || !enabled) this.enabled = enabled;
    if (this.isEnabled()) {
      Apollo.EVENT_BUS.register(this);
      this.onModuleEnable();
    } else {
      Apollo.EVENT_BUS.unregister(this);
      this.onModuleDisable();
    }
    return this.enabled;
  }

  /** Called when module is enabled. */
  public void onEnabled() {}
  /** Called when module is disabled. */
  public void onDisable() {}
  /** Called on startup */
  public void setup() {}
  /** Called on Shutdown */
  public void shutdown() {}
  /** Queried when enabled */
  public boolean canBeEnabled() {
    return true;
  }

  /**
   * Download data from data base.
   *
   * @param filename name of file in database / local
   * @return string of file
   * @throws Exception any exception encountered
   */
  public final String getDataFromUrlOrLocal(String filename) throws Exception {
    try {
      URLConnection urlConnection =
          new URL("https://static.apolloclient.net/" + filename).openConnection();
      urlConnection.setRequestProperty(
          "User-Agent", "Mozilla/5.0 (Windows NT 5.1; rv:19.0) Gecko/20100101 Firefox/19.0");
      urlConnection.connect();
      BufferedReader serverResponse =
          new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
      String response = serverResponse.lines().collect(Collectors.joining());
      serverResponse.close();
      return response;
    } catch (Exception exception) {
      BufferedReader bufferedReader =
          new BufferedReader(
              new InputStreamReader(Module.class.getResourceAsStream("/other/" + filename)));
      String response = bufferedReader.lines().collect(Collectors.joining());
      bufferedReader.close();
      return response;
    }
  }
}

/*⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤
 Copyright (C) 2020-2021 developed by Icovid and Apollo Development Team

 Setting.java is part of Apollo Client. 9/3/20, 8:54 PM

 Setting.java can not be copied and/or distributed without the express
 permission of Icovid

 Contact: Icovid#3888 @ https://discord.com
 ⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤*/

package io.apollo.settingsmanager;

/** Basic setting class. All setting types will be extended from this.
 * @author Icovid | Icovid#3888
 * @since 1.0.0 **/
public abstract class Setting {

    public final String name;
    public final String description;

    /** Create Setting.
     * @param name name of setting
     * @param description description displayed in tooltip **/
    public Setting(String name, String description) {
        this.name = name;
        this.description = description;
    }
}

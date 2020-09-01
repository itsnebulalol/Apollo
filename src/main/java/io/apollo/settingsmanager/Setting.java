/*⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤
 Copyright (C) 2020-2021 developed by Icovid and Apollo Development Team
 All Contributors can be found in the README.md

 Setting.java is part of Apollo Client. 9/1/20, 12:39 PM

 Setting.java can not be copied and/or distributed without the express
 permission of Icovid

 Contact: Icovid#3888 @ https://discord.com
 ⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤*/

package io.apollo.settingsmanager;

import java.lang.reflect.Field;

public class Setting {

    public final String name;
    public final String description;
    public final String code;
    public final Field field;
    public final Boolean show;

    /** Create Setting.
     * @param name name of setting
     * @param description description displayed in tooltip
     * @param code code used to retrieve value from file **/
    public Setting(String name, String description, String code, Boolean show, Field field) {
        this.name = name;
        this.description = description;
        this.code = code;
        this.field = field;
        this.show = show;
    }
}

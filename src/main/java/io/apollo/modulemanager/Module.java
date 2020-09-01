/*
 * ****************************************************************
 *  Copyright (C) 2020-2021 developed by Icovid
 *
 *  Module.java is part of Apollo Client. [8/28/20, 9:51 PM]
 *
 *  Module.java can not be copied and/or distributed without the express
 *  permission of Icovid
 *
 *  Contact: Icovid#3888
 * ****************************************************************
 */

package io.apollo.modulemanager;

/** Module Constructor for all Mods
 * @author Icovid | Icovid#3888
 * @since 1.0.0 **/
public class Module {

    public final String name;
    public final String description;
    public final Category category;
    private boolean enabled;
    public int key;

    public Module(String name, String description, Category category) { this(name, description, category, 0, false); }
    public Module(String name, String description, Category category, Boolean enabled) { this(name, description, category, 0, enabled); }
    /** Main constructor used for all modules.
     * @param name name of module
     * @param description description of module
     * @param key key code to toggle module
     * @param enabled is module toggled **/
    public Module(String name, String description, Category category, int key, boolean enabled) {
        this.name = name;
        this.description = description;
        this.category = category;
        this.key = key;
        this.enabled = enabled;
    }
}

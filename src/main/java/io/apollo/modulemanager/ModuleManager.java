package io.apollo.modulemanager;

import java.util.ArrayList;
import java.util.List;

public class ModuleManager {

    public final List<Module> modules = new ArrayList<>();

    /* Module Instances */

    /* Instantiate Module Instances */
    private void initModules() {
    }

    /* Add Modules to Global List */
    public ModuleManager() {
        initModules();
    }

    /* Get Module by Name, Key, or Category */
    public final Module getModbyName(String name) { for (Module module : this.modules) { if (module.name.equalsIgnoreCase(name)) { return module; } }return null; }
    public final List<Module> getModsbyCategory(Category category) { List<Module> out = new ArrayList<>(); for (Module module : this.modules) { if (module.category == category) { out.add(module);}  }return out; }
    /* Get Count of all Modules */
    public final int getModCount() { return this.modules.size(); }
}

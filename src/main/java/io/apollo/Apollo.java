package io.apollo;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.lwjgl.opengl.Display;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Apollo {
    public static final String name = "Apollo", version = "b0.1";
    public static final String[] developers = {"Icovid", "Nora", "isXander"};
    public static final File apolloDirectory = new File(System.getProperty("user.dir") + "/" + name.toLowerCase());
    public static final File settingsFile = new File(System.getProperty("user.dir") + "/" + name.toLowerCase() + "/settings.json");

    public static final Apollo instance = new Apollo();
//    public static final EventManager eventManager = new EventManager();
//    public static final ModuleManager moduleManager = new ModuleManager();

    @SuppressWarnings("ResultOfMethodCallIgnored")
    public Apollo() {
        Display.setTitle(name + " " + version);
        if (!apolloDirectory.exists()) { try { apolloDirectory.mkdirs();  } catch (Exception ignored) { } log("Created Apollo Directory: " + apolloDirectory.getAbsolutePath()); }
        if (!settingsFile.exists()) { try { settingsFile.createNewFile(); } catch (Exception ignored) { } log("Created Settings File: " + settingsFile.getAbsolutePath()); }
    }

    public void postInitialisation() { log("Apollo Startup Finnish with " + 0+ " Modules!"); }
    public static void log (String... msg) { for (String log : msg)  System.out.println("[" + new SimpleDateFormat("HH:mm:ss").format(new Date()) + "] [Apollo] " + log); }
}

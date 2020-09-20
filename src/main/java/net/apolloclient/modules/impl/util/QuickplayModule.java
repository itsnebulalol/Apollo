package net.apolloclient.modules.impl.util;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import net.apolloclient.Apollo;
import net.apolloclient.events.bus.EventSubscriber;
import net.apolloclient.events.impl.client.input.KeyPressedEvent;
import net.apolloclient.modules.Category;
import net.apolloclient.modules.Module;
import net.minecraft.client.Minecraft;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.ChatStyle;
import net.minecraft.util.EnumChatFormatting;
import org.lwjgl.input.Keyboard;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

public class QuickplayModule extends Module {

    private JsonObject data;
    private List<Game> games = new ArrayList<>();

    public QuickplayModule() {
        super("Quickplay", "Choose a game to play in a good-looking menu.", Category.UTIL, true);
    }

    @Override
    public void setup() {
        new Thread("Quickplay Games Grabber") {
            @Override
            public void run() {
                Gson gson = new Gson();
                try {
                    URL url = new URL("https://static.apolloclient.net/quickplay-games.json");
                    URLConnection connection = url.openConnection();
                    connection.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 5.1; rv:19.0) Gecko/20100101 Firefox/19.0");
                    connection.connect();
                    BufferedReader serverResponse = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                    StringBuilder resp = new StringBuilder();
                    serverResponse.lines().forEach(line -> resp.append(line).append("\n"));
                    serverResponse.close();
                    data = gson.fromJson(resp.toString(), JsonObject.class);

                    JsonObject version = data.getAsJsonObject("version");
                    Apollo.log("[Quickplay] Using games list v" + version.get("version").getAsString() + " (" + version.get("date").getAsString() + ")");

                    data.getAsJsonArray("games").forEach(game -> games.add(gson.fromJson(game, Game.class)));
                } catch (Exception e) {
                    Apollo.error("[Quickplay] Failed to get games.");
                    // TODO: Hardcode default games?
                    JsonObject defaultData = new JsonObject();
                    JsonObject version = new JsonObject();
                    version.addProperty("date", "09/20/2020");
                    version.addProperty("version", "0");
                    version.addProperty("note", "Default Games (Failed to grab from Apollo servers)");
                    defaultData.add("version", version);
                    JsonArray games = new JsonArray();
                    JsonObject failed = new JsonObject();
                    failed.addProperty("name", "Error");
                    failed.addProperty("icon", "https://hypixel.net/styles/hypixel-uix/hypixel/game-icons/SkyBlock-64.png"); // TODO: Replace with error icon.
                    games.add(failed);
                    defaultData.add("games", games);
                    data = defaultData;
                    setEnabled(false);
                }
                data.getAsJsonArray("games").forEach(game -> games.add(gson.fromJson(game, Game.class)));
                try { join(); } catch (InterruptedException ignored) {}
            }
        }.start();
    }

    @EventSubscriber
    public void onKeyDown (KeyPressedEvent event) {
        if (event.getKeyCode() == Keyboard.KEY_LMENU) { // TODO: Make configurable.
            ChatComponentText text = new ChatComponentText("This is work in progress.");
            text.setChatStyle(new ChatStyle().setColor(EnumChatFormatting.RED));
            Minecraft.getMinecraft().ingameGUI.getChatGUI().printChatMessage(text);
            /*if (Minecraft.getMinecraft().currentScreen == null) {
                Minecraft.getMinecraft().displayGuiScreen(new ModulesGui()); // TODO: Create GUI.
            }*/
        }
    }

    private static class Game {
        private String name;
        private String icon;
        @Deprecated private List<Mode> modes;
        public String getName() {
            return name;
        }
        public String getIcon() {
            return icon;
        }
        @Deprecated public List<Mode> getModes() {
            return modes;
        }
    }

    private static class Mode {
        private String name;
        private String command;
        private boolean enabled;
        public String getName() {
            return name;
        }
        public String getCommand() {
            return command;
        }
        public boolean isEnabled() {
            return enabled;
        }
    }

}

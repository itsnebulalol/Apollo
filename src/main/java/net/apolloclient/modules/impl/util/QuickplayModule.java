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
    private List<Game> games;

    public QuickplayModule() {
        super("Quickplay", "Choose a game to play in a good-looking menu.", Category.UTIL, true);
    }

    @Override
    public void setup() throws Exception {
        games = new ArrayList<>();
        Gson gson = new Gson();
        data = gson.fromJson(this.getDataFromUrlOrLocal("quickplay-games.json"), JsonObject.class);

        JsonObject version = data.getAsJsonObject("version");
        Apollo.log("[Quickplay] Using games list v" + version.get("version").getAsString() + " (" + version.get("date").getAsString() + ")");

        data.getAsJsonArray("games").forEach(game -> games.add(gson.fromJson(game, Game.class)));

        data.getAsJsonArray("games").forEach(game -> games.add(gson.fromJson(game, Game.class)));
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

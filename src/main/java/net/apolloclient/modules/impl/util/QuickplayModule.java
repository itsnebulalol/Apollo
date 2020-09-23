package net.apolloclient.modules.impl.util;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import lombok.SneakyThrows;
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

import java.util.ArrayList;
import java.util.List;

public class QuickplayModule extends Module {

    private static List<Game> games;

    public QuickplayModule() {
        super("Quickplay", "Choose a game to play in a good-looking menu.", Category.UTIL, true);
    }

    @Override
    @SneakyThrows
    public void setup()  {
        games = new ArrayList<>();
        Gson gson = new Gson();
        JsonObject data = gson.fromJson(this.getDataFromUrlOrLocal("quickplay-games.json"), JsonObject.class);

        JsonObject version = data.getAsJsonObject("version");
        Apollo.log(
                "[Quickplay] Using games list v"
                        + version.get("version").getAsString()
                        + " ("
                        + version.get("date").getAsString()
                        + ")");


        data.getAsJsonArray("games").forEach(game -> games.add(new Game(game.getAsJsonObject())));
    }

    @EventSubscriber
    public void onKeyDown(KeyPressedEvent event) {
        if (event.getKeyCode() == Keyboard.KEY_LMENU) { // TODO: Make configurable.
            ChatComponentText text = new ChatComponentText("This is work in progress.");
            text.setChatStyle(new ChatStyle().setColor(EnumChatFormatting.RED));
            Minecraft.getMinecraft().ingameGUI.getChatGUI().printChatMessage(text);
      /*if (Minecraft.getMinecraft().currentScreen == null) {
          Minecraft.getMinecraft().displayGuiScreen(new ModulesGuiOLD()); // TODO: Create GUI.
      }*/
        }
    }

    public static List<Game> getGames() {
        return games;
    }

    private static class Game {
        private final String name;
        private final String icon;
        private final List<Mode> modes = new ArrayList<>();

        public Game(JsonObject data) {
            name = data.get("name").getAsString();
            icon = data.get("icon").getAsString();
            data.getAsJsonArray("modes").forEach(mode -> modes.add(new Mode(mode.getAsJsonObject())));
        }

        public String getName() {
            return name;
        }

        public String getIcon() {
            return icon;
        }

        public List<Mode> getModes() {
            return modes;
        }

        private static class Mode {
            private final String name;
            private final String command;
            private final boolean enabled;

            public Mode(JsonObject data) {
                name = data.get("name").getAsString();
                command = data.get("command").getAsString();
                enabled = data.get("enabled").getAsBoolean();
            }

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
}

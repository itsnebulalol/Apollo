package net.apolloclient.modules.impl.util;

import net.apolloclient.Apollo;
import net.apolloclient.events.bus.EventSubscriber;
import net.apolloclient.events.impl.client.GuiSwitchEvent;
import net.apolloclient.modules.Category;
import net.apolloclient.modules.Module;
import net.arikia.dev.drpc.DiscordEventHandlers;
import net.arikia.dev.drpc.DiscordRPC;
import net.arikia.dev.drpc.DiscordRichPresence;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiMainMenu;
import net.minecraft.client.gui.GuiMultiplayer;
import net.minecraft.client.gui.GuiSelectWorld;

public class DiscordRPModule extends Module {

    private static long currentTime;

    private static final String LOGO_WHITE = "apollowhite";
    private static final String LOGO_SQUARE = "apollologo2";
    private static final String LOGO_ROUND = "apollologo";

    private static DiscordEventHandlers eventHandlers;
    private static DiscordRichPresence discordRichPresence;

    public DiscordRPModule() {
        super("Discord Rich Presence", "Show what you are doing to everyone on Discord.", Category.UTIL, true);
    }

    @Override
    public void setupModule() {
        currentTime = System.currentTimeMillis();
        eventHandlers = new DiscordEventHandlers.Builder().setReadyEventHandler(discordUser -> {
            Apollo.log("[Discord Rich Presence] Found " + discordUser.username + "#" + discordUser.discriminator + "!");
            update("", "Launching...", LOGO_SQUARE);
        }).build();
        onEnabled();
        new Thread("Apollo DiscordRPC") {
            @Override
            public void run() {
                while(isEnabled())
                    DiscordRPC.discordRunCallbacks();
            }
        }.start();
    }

    @Override
    public void onDisable() {
        DiscordRPC.discordShutdown();
    }

    @Override
    public void shutdown() {
        onDisable();
    }

    @Override
    public void onEnabled() {
        // 757271929399803914 <-- old
        if (eventHandlers != null)
            DiscordRPC.discordInitialize("728315613893886011", eventHandlers, true);
        if (discordRichPresence != null)
            DiscordRPC.discordUpdatePresence(discordRichPresence);
    }

    public static void update(DiscordRichPresence drp) {
        discordRichPresence = drp;
        DiscordRPC.discordUpdatePresence(discordRichPresence);
    }

    public static void update(String title, String description, String bigLogo) {
        DiscordRichPresence discordRichPresence = new DiscordRichPresence.Builder(description)
                .setStartTimestamps(currentTime).build();
        discordRichPresence.writeField("details", title);
        discordRichPresence.writeField("largeImageKey", bigLogo);
        discordRichPresence.writeField("largeImageText", "Apollo Client");
        update(discordRichPresence);
    }

    public static void update(String title, String description, String bigLogo, String smallLogo) {
        DiscordRichPresence discordRichPresence = new DiscordRichPresence.Builder(description)
                .setStartTimestamps(currentTime).build();
        discordRichPresence.writeField("details", title);
        discordRichPresence.writeField("largeImageKey", bigLogo);
        discordRichPresence.writeField("largeImageText", "Apollo Client");
        discordRichPresence.writeField("smallImageKey", smallLogo);
        update(discordRichPresence);
    }

    public static void update(String title, String description, String bigLogo, String smallLogo, String smallLogoText) {
        DiscordRichPresence discordRichPresence = new DiscordRichPresence.Builder(description)
                .setStartTimestamps(currentTime).build();
        discordRichPresence.writeField("details", title);
        discordRichPresence.writeField("largeImageKey", bigLogo);
        discordRichPresence.writeField("largeImageText", "Apollo Client");
        discordRichPresence.writeField("smallImageKey", smallLogo);
        discordRichPresence.writeField("smallImageText", smallLogoText);
        update(discordRichPresence);
    }

    @EventSubscriber
    public void onGuiSwitch(GuiSwitchEvent event) {
        if (event.getGuiScreen() instanceof GuiMainMenu) {
            update("In the Main Menu", "IGN: " + Minecraft.getMinecraft().getSession().getUsername(), LOGO_SQUARE);
        } else if (event.getGuiScreen() instanceof GuiMultiplayer) {
            update("In the Multiplayer Menu", "IGN: " + Minecraft.getMinecraft().getSession().getUsername(), LOGO_SQUARE);
        } else if (event.getGuiScreen() instanceof GuiSelectWorld) {
            update("In the Singleplayer Menu", "IGN: " + Minecraft.getMinecraft().getSession().getUsername(), LOGO_SQUARE);
        } else if (event.getGuiScreen() == null) {
            if (Minecraft.getMinecraft().isIntegratedServerRunning()) {
                String world = Minecraft.getMinecraft().getIntegratedServer().getWorldName();
                update("Playing Singleplayer", world, LOGO_SQUARE);
            } else {
                update("Playing Multiplayer", "IGN: " + Minecraft.getMinecraft().getSession().getUsername(), LOGO_SQUARE);
            }
        }
    }

}

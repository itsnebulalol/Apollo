/*⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤
 Copyright (C) 2020-2021 developed by Icovid and Apollo Development Team.

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

package net.apolloclient.module.impl.util;

import net.apolloclient.Apollo;
import net.apolloclient.event.Priority;
import net.apolloclient.event.bus.SubscribeEvent;
import net.apolloclient.event.impl.client.GameLoopEvent;
import net.apolloclient.event.impl.hud.GuiSwitchEvent;
import net.apolloclient.module.bus.Module;
import net.apolloclient.module.bus.Module.EventHandler;
import net.apolloclient.module.bus.Module.Instance;
import net.apolloclient.module.bus.event.DisableEvent;
import net.apolloclient.module.bus.event.EnableEvent;
import net.apolloclient.module.bus.event.InitializationEvent;
import net.apolloclient.module.bus.event.ShutDownEvent;
import net.arikia.dev.drpc.DiscordEventHandlers;
import net.arikia.dev.drpc.DiscordRPC;
import net.arikia.dev.drpc.DiscordRichPresence;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiMainMenu;
import net.minecraft.client.multiplayer.GuiConnecting;
import net.minecraft.client.multiplayer.ServerData;

/**
 * Show what you are doing to everyone on Discord.
 *
 * @author SLLCoding
 * @since b0.2
 */
@Module(name = DiscordRP.NAME, description = DiscordRP.DESCRIPTION, author = DiscordRP.AUTHOR)
public class DiscordRP {

    public static final String NAME = "Discord Rich Presence";
    public static final String DESCRIPTION = "Show what you are doing to everyone on Discord.";
    public static final String AUTHOR = "SLLCoding";

    @Instance public static final DiscordRP instance = new DiscordRP();

    private static final String LOGO_WHITE = "apollowhite";
    private static final String LOGO_SQUARE = "apollologo2";
    private static final String LOGO_ROUND = "apollologo";

    private DiscordEventHandlers eventHandlers;
    private DiscordRichPresence discordRichPresence;

    private static long currentTime;

    @EventHandler(priority = Priority.HIGH)
    public void setup (InitializationEvent event) {

        eventHandlers = new DiscordEventHandlers.Builder()
                .setReadyEventHandler(
                                discordUser -> {
                                    Apollo.log("[Discord Rich Presence] Found " + discordUser.username + "#" + discordUser.discriminator + "!");
                                    update("In the Main Menu", "IGN: " + Minecraft.getMinecraft().getSession().getUsername(), LOGO_SQUARE);
                                })
                .setJoinGameEventHandler(
                                joinSecret -> {
                                    Apollo.log("[Discord Rich Presence] Joining game: " + joinSecret);
                                    String serverIp = joinSecret.replaceAll("=", "");

                                    Minecraft.getMinecraft()
                                            .addScheduledTask(
                                                    () -> {
                                                        if (Minecraft.getMinecraft().theWorld != null) {
                                                            Minecraft.getMinecraft().theWorld.sendQuittingDisconnectingPacket();
                                                            Minecraft.getMinecraft().loadWorld(null);
                                                        }
                                                        Minecraft.getMinecraft().displayGuiScreen(new GuiConnecting(new GuiMainMenu(), Minecraft.getMinecraft(), new ServerData(serverIp, serverIp, false)));
                                                    });
                                })
                .setJoinRequestEventHandler(request -> DiscordRPC.discordRespond(request.userId, DiscordRPC.DiscordReply.NO))
                .build();

        DiscordRPC.discordInitialize("728315613893886011", eventHandlers, true);
        DiscordRPC.discordUpdatePresence(discordRichPresence);
    }

    @EventHandler
    public void onDisable(DisableEvent event) {
        DiscordRPC.discordShutdown();
    }

    @EventHandler
    public void shutdown(ShutDownEvent event) {
        DiscordRPC.discordShutdown();
    }

    @EventHandler(priority = Priority.HIGH)
    public void onEnabled(EnableEvent event) {
        DiscordRPC.discordInitialize("728315613893886011", eventHandlers, true);
        DiscordRPC.discordUpdatePresence(discordRichPresence);
    }

    @SubscribeEvent(priority = Priority.LOW)
    public void tickEvent(GameLoopEvent event) {
        DiscordRPC.discordRunCallbacks();
    }

    @SubscribeEvent(priority = Priority.HIGH)
    public void onGuiSwitch(GuiSwitchEvent event) {
        if (event.guiScreen != null)
            update("In the " + event.guiScreen.getClass().getSimpleName().replace("Gui", ""),
                "IGN: " + Minecraft.getMinecraft().getSession().getUsername(), LOGO_SQUARE);
        else if (Minecraft.getMinecraft().isIntegratedServerRunning())
            update("Playing Singleplayer",  Minecraft.getMinecraft().getIntegratedServer().getWorldName(), LOGO_SQUARE);
        else {
            update("Playing Multiplayer", "IGN: " + Minecraft.getMinecraft().getSession().getUsername(), LOGO_SQUARE);
            ServerData currentServer = Minecraft.getMinecraft().getCurrentServerData();
            setJoinData(currentServer.serverIP);
        }
    }

    public void setJoinData(String servername, int players, int max) {
        getServerIp(servername);
        discordRichPresence.writeField("partySize", players);
        discordRichPresence.writeField("partyMax", max);
        DiscordRPC.discordUpdatePresence(discordRichPresence);
    }

    public void setJoinData(String servername) {
        getServerIp(servername);
        DiscordRPC.discordUpdatePresence(discordRichPresence);
    }

    private void getServerIp(String servername) {
        int serverLength = servername.length();
        StringBuilder id = new StringBuilder();
        id.append(servername);
        int times = 28 - serverLength;
        while (times != 0) {
            id.append("=");
            times--;
        }
        discordRichPresence.writeField("partyId", id.toString());
        discordRichPresence.writeField("joinSecret", servername);
    }

    public void update (DiscordRichPresence drp) {
        discordRichPresence = drp;
        DiscordRPC.discordUpdatePresence(discordRichPresence);
    }

    public void update (String title, String description, String bigLogo) {
        DiscordRichPresence discordRichPresence = new DiscordRichPresence.Builder(description).setStartTimestamps(currentTime).build();
        discordRichPresence.writeField("details", title);
        discordRichPresence.writeField("largeImageKey", bigLogo);
        discordRichPresence.writeField("largeImageText", "Apollo Client");
        update(discordRichPresence);
    }

    public void update (String title, String description, String bigLogo, String smallLogo) {
        DiscordRichPresence discordRichPresence = new DiscordRichPresence.Builder(description).setStartTimestamps(currentTime).build();
        discordRichPresence.writeField("details", title);
        discordRichPresence.writeField("largeImageKey", bigLogo);
        discordRichPresence.writeField("largeImageText", "Apollo Client");
        discordRichPresence.writeField("smallImageKey", smallLogo);
        update(discordRichPresence);
    }

    public void update (String title, String description, String bigLogo, String smallLogo, String smallLogoText) {
        DiscordRichPresence discordRichPresence = new DiscordRichPresence.Builder(description).setStartTimestamps(currentTime).build();
        discordRichPresence.writeField("details", title);
        discordRichPresence.writeField("largeImageKey", bigLogo);
        discordRichPresence.writeField("largeImageText", "Apollo Client");
        discordRichPresence.writeField("smallImageKey", smallLogo);
        discordRichPresence.writeField("smallImageText", smallLogoText);
        update(discordRichPresence);
    }
}

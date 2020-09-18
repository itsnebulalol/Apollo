/*⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤
 Copyright (C) 2020-2021 developed by Icovid and Apollo Development Team

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

package net.apolloclient.modules.impl.util;

import net.apolloclient.events.bus.EventSubscriber;
import net.apolloclient.events.impl.client.GameLoopEvent;
import net.apolloclient.events.impl.world.LoadWorldEvent;
import net.apolloclient.modules.Category;
import net.apolloclient.modules.Module;
import lombok.Getter;
import net.arikia.dev.drpc.DiscordEventHandlers;
import net.arikia.dev.drpc.DiscordRPC;
import net.arikia.dev.drpc.DiscordRichPresence;
import net.arikia.dev.drpc.DiscordUser;
import net.arikia.dev.drpc.callbacks.ReadyCallback;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiMainMenu;
import net.minecraft.client.gui.GuiMultiplayer;

public class DiscordRPModule extends Module {

    public DiscordRPModule () { super("DiscordRP", "Updates Discord Rich Presents based on what your doing", Category.UTIL, true); }

    @Getter private boolean running = false;
    @Getter private long created = 0;

    @Override public void onEnabled () {
        this.created = System.currentTimeMillis();
        DiscordEventHandlers handlers = new DiscordEventHandlers.Builder().setReadyEventHandler(new ReadyCallback() {
            @Override public void apply (DiscordUser user) {
                updateRP("booting up", "", "", "");
            }
        }).build();
        DiscordRPC.discordInitialize("728315613893886011", handlers, true);
        new Thread("Discord RPC Callback") {
            @Override public void run () {
                while (running) { DiscordRPC.discordRunCallbacks(); }
            }
        }.start();
    }

    @Override public void onDisable () { DiscordRPC.discordShutdown(); }
    @Override public void shutdown () { DiscordRPC.discordShutdown(); }

    @EventSubscriber public void onWorldLoad (LoadWorldEvent event) {
        if (!Minecraft.getMinecraft().isSingleplayer())
            this.updateRP("Multiplayer", "IP: " + Minecraft.getMinecraft().getCurrentServerData().serverIP, "", "");
        else this.updateRP("Singleplayer", "In Game", "", "");
    }

    @EventSubscriber public void onTick (GameLoopEvent event) {
        if (Minecraft.getMinecraft().currentScreen instanceof GuiMainMenu)
            this.updateRP("Main Menu", "In Game", "", "");
        else if (Minecraft.getMinecraft().currentScreen instanceof GuiMultiplayer)
            this.updateRP("Multiplayer Menu", "In Game", "", "");
    }

    /** Updates the Rich Presents
     * @param line1 sets the text for the first line of the Rich Presents
     * @param line2 sets the text for the second line of the Rich Presents
     * @param image sets a small image for the Rich Presents, leave blank if not needed
     * @param imageText sets text for the small image for the Rich Presents, leave blank if not needed **/
    public void updateRP (String line1, String line2, String image, String imageText) {
        DiscordRichPresence.Builder b = new DiscordRichPresence.Builder(line2);
        b.setBigImage("apollologo2", "Apollo Client");
        if (image != null) { b.setSmallImage(image, imageText); }
        b.setDetails(line1);
        b.setStartTimestamps(created);
        DiscordRPC.discordUpdatePresence(b.build());
    }
}

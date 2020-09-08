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
package io.apollo.discord;

import net.arikia.dev.drpc.DiscordEventHandlers;
import net.arikia.dev.drpc.DiscordRPC;
import net.arikia.dev.drpc.DiscordRichPresence;
import net.arikia.dev.drpc.DiscordUser;
import net.arikia.dev.drpc.callbacks.ReadyCallback;
import org.junit.Test;

import java.util.Optional;
import java.util.Scanner;

/**
 * @author Pinkulu | pinkulu#6260
 * @since v1.0.0
 * this class is used for Discord Rich Presents **/
public class DiscordRP {
    private boolean running = false;
    private long created = 0;
    public static String name = "";
    /** Call this when the game starts, starts Discord Rich Presents **/
    public void start() {

        this.created = System.currentTimeMillis();

        DiscordEventHandlers handlers = new DiscordEventHandlers.Builder().setReadyEventHandler(new ReadyCallback() {
            @Override
            public void apply(DiscordUser user) {
                update("booting up", "", "", "");
            }

        }).build();

        DiscordRPC.discordInitialize("728315613893886011", handlers, true);
        new Thread("Discord RPC Callback") {
            @Override
            public void run() {
                while(running) {
                    DiscordRPC.discordRunCallbacks();
                }
            }
        }.start();
    }
    /** Call this when the game stops, stops Discord Rich Presents **/
    public void shutdown() {
        running = false;
        DiscordRPC.discordShutdown();
    }
    /** Updates the Rich Presents
     * @param firstLine sets the text for the first line of the Rich Presents
     * @param secondLine sets the text for the second line of the Rich Presents
     * @param smallImage sets a small image for the Rich Presents, leave blank if not needed
     * @param smallText sets text for the small image for the Rich Presents, leave blank if not needed **/
    public void update(String firstLine, String secondLine, String smallImage, String smallText){
        DiscordRichPresence.Builder b = new DiscordRichPresence.Builder(secondLine);
        b.setBigImage("apollologo2", "Apollo Client");
        if(smallImage != null){
            b.setSmallImage(smallImage, smallText);
        }
        b.setDetails(firstLine);
        b.setStartTimestamps(created);

        DiscordRPC.discordUpdatePresence(b.build());
    }
}
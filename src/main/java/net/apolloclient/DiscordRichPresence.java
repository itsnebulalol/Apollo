package net.apolloclient;

import net.arikia.dev.drpc.DiscordEventHandlers;
import net.arikia.dev.drpc.DiscordRPC;
import net.arikia.dev.drpc.DiscordUser;
import net.arikia.dev.drpc.callbacks.ReadyCallback;

/** Basic DiscordRichPresence
 * @author MatthewTGM | MatthewTGM#4058
 */
public class DiscordRichPresence {

    public long currentTime;
    public boolean running = true;

    public void start() {

        currentTime = System.currentTimeMillis();
        DiscordEventHandlers eventHandlers = new DiscordEventHandlers.Builder().setReadyEventHandler(new ReadyCallback() {
            @Override
            public void apply(DiscordUser discordUser) {

                Apollo.log(discordUser.username + discordUser.discriminator + " has logged in!");
                net.arikia.dev.drpc.DiscordRichPresence discordRichPresence = new net.arikia.dev.drpc.DiscordRichPresence();
                discordRichPresence.writeField("state", "Test");
                DiscordRPC.discordUpdatePresence(discordRichPresence);
            }
        }).build();

        DiscordRPC.discordInitialize("757216281308168244", eventHandlers, true);

        new Thread("Apollo DiscordRPC") {

            @Override
            public void run() {

                while(running)
                    DiscordRPC.discordRunCallbacks();

            }
        }.start();

    }

    public void shutdown() {

        running = false;
        DiscordRPC.discordShutdown();

    }

    public void update(String firstLine, String secondLine, String bigImage) {

        net.arikia.dev.drpc.DiscordRichPresence.Builder b = new net.arikia.dev.drpc.DiscordRichPresence.Builder(secondLine);
        b.setBigImage(bigImage, "");
        b.setDetails(firstLine);
        b.setStartTimestamps(currentTime);

        DiscordRPC.discordUpdatePresence(b.build());
    }

}

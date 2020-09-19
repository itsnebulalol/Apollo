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

package net.apolloclient.modules.impl.gameplay;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import net.apolloclient.Apollo;
import net.apolloclient.events.bus.EventSubscriber;
import net.apolloclient.events.bus.Priority;
import net.apolloclient.events.impl.client.ChatReceivedEvent;
import net.apolloclient.events.impl.client.GameLoopEvent;
import net.apolloclient.modules.Category;
import net.apolloclient.modules.Module;
import net.minecraft.client.Minecraft;
import net.minecraft.util.IChatComponent;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

/**
 * @author SLLCoding
 * @since b0.2
 */
public class AutoGGModule extends Module {

    // Chat patters for isNormalMessage(...).
    private final Pattern chatPattern = Pattern.compile("(?<rank>\\.get(.+) )?(?<player>\\S{1,16}): (?<message>.*)");
    private final Pattern teamPattern = Pattern.compile("\\.get(TEAM) (?<rank>\\.get(.+) )?(?<player>\\S{1,16}): (?<message>.*)");
    private final Pattern guildPattern = Pattern.compile("Guild > (?<rank>\\.get(.+) )?(?<player>\\S{1,16}): (?<message>.*)");
    private final Pattern partyPattern = Pattern.compile("Party > (?<rank>\\.get(.+) )?(?<player>\\S{1,16}): (?<message>.*)");
    private final Pattern shoutPattern = Pattern.compile("\\.get(SHOUT) (?<rank>\\.get(.+) )?(?<player>\\S{1,16}): (?<message>.*)");
    private final Pattern spectatorPattern = Pattern.compile("\\.get(SPECTATOR) (?<rank>\\.get(.+) )?(?<player>\\S{1,16}): (?<message>.*)");

    // Patterns to match if the game is over.
    private final List<Pattern> wins = new ArrayList<>();

    // Patterns to match if an event has happened.
    private final List<Pattern> events = new ArrayList<>();

    // Patterns to match if its a normal message (Doesn't say GG)
    private final List<Pattern> normal = new ArrayList<>();

    private int tick = -1;

    /** Sets up triggers. **/
    public AutoGGModule() {
        super("AutoGG", "Automatically say GG at the end of a game.", Category.GAMEPLAY, true);
    }

    @Override public void setupModule () {
        new Thread("AutoGG Trigger Grabber") {
            @Override
            public void run() {
                try {
                    URL url = new URL("https://apolloclient.net/static/autogg-triggers.json");
                    URLConnection connection = url.openConnection();
                    connection.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 5.1; rv:19.0) Gecko/20100101 Firefox/19.0");
                    connection.connect();
                    BufferedReader serverResponse = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                    StringBuilder resp = new StringBuilder();
                    serverResponse.lines().forEach(line -> resp.append(line).append("\n"));
                    serverResponse.close();
                    JsonObject response = new Gson().fromJson(resp.toString(), JsonObject.class);

                    JsonObject version = response.getAsJsonObject("version");
                    Apollo.log("[AutoGG] Using triggers v" + version.get("version").getAsString() + " (" + version.get("date").getAsString() + ")");

                    response.getAsJsonArray("wins").forEach(trigger -> wins.add(Pattern.compile(trigger.getAsString())));
                    response.getAsJsonArray("events").forEach(trigger -> events.add(Pattern.compile(trigger.getAsString())));
                    response.getAsJsonArray("normal").forEach(trigger -> normal.add(Pattern.compile(trigger.getAsString())));
                } catch (Exception e) {
                    Apollo.error("[AutoGG] Failed to get latest triggers. Using hardcoded.");
                    List<String> endingStrings = Arrays.asList(
                            "^ +1st Killer - ?\\[?\\w*\\+*\\]? \\w+ - \\d+(?: Kills?)?$",
                            "^ *1st (?:Place ?)?(?:-|:)? ?\\[?\\w*\\+*\\]? \\w+(?: : \\d+| - \\d+(?: Points?)?| - \\d+(?: x .)?| \\(\\w+ .{1,6}\\) - \\d+ Kills?|: \\d+:\\d+| - \\d+ (?:Zombie )?(?:Kills?|Blocks? Destroyed)| - \\[LINK\\])?$",
                            "^ +Winn(?:er #1 \\(\\d+ Kills\\): \\w+ \\(\\w+\\)|er(?::| - )(?:Hiders|Seekers|Defenders|Attackers|PLAYERS?|MURDERERS?|Red|Blue|RED|BLU|\\w+)(?: Team)?|ers?: ?\\[?\\w*\\+*\\]? \\w+(?:, ?\\[?\\w*\\+*\\]? \\w+)?|Team ?[\\:-] (?:Animals|Hunters|Red|Green|Blue|Yellow|RED|BLU|Survivors|Vampires))$",
                            "^ +Alpha Infected: \\w+ \\(\\d+ infections?\\)$",
                            "^ +Murderer: \\w+ \\(\\d+ Kills?\\)$",
                            "^ +You survived \\d+ rounds!$",
                            "^ +(?:UHC|SkyWars|The Bridge|Sumo|Classic|OP|MegaWalls|Bow|NoDebuff|Blitz|Combo|Bow Spleef) (?:Duel|Doubles|Teams|Deathmatch|2v2v2v2|3v3v3v3)? - \\d+:\\d+$",
                            "^ +They captured all wools!$",
                            "^ +Game over!$",
                            "^ +[\\d\\.]+k?/[\\d\\.]+k? \\w+$",
                            "^ +(?:Criminal|Cop)s won the game!$",
                            "^ +\\[?\\w*\\+*\\]? \\w+ - \\d+ Final Kills$",
                            "^ +Zombies - \\d*:?\\d+:\\d+ \\(Round \\d+\\)$",
                            "^ +. YOUR STATISTICS .",
                            "^MINOR EVENT! .+ in .+ ended$");
                    for (String trigger : endingStrings) { wins.add(Pattern.compile(trigger)); }

                    List<String> eventStrings = Arrays.asList(
                            "^DRAGON EGG OVER! Earned [\\d,]+XP [\\d,]g clicking the egg \\d+ times$",
                            "^GIANT CAKE! Event ended! Cake's gone!$",
                            "^PIT EVENT ENDED: .+ \\[INFO\\]$",
                            "^\\[?\\w*\\+*\\]? ?\\w+ caught ?a?n? .+! .*$");
                    for (String trigger : eventStrings) { events.add(Pattern.compile(trigger)); }

                    List<String> normalStrings = Arrays.asList(
                            "(?<rank>\\.get(.+) )?(?<player>\\S{1,16}): (?<message>.*)",
                            "\\.get(TEAM) (?<rank>\\.get(.+) )?(?<player>\\S{1,16}): (?<message>.*)",
                            "Guild > (?<rank>\\.get(.+) )?(?<player>\\S{1,16}): (?<message>.*)",
                            "Party > (?<rank>\\.get(.+) )?(?<player>\\S{1,16}): (?<message>.*)",
                            "\\.get(SHOUT) (?<rank>\\.get(.+) )?(?<player>\\S{1,16}): (?<message>.*)",
                            "\\.get(SPECTATOR) (?<rank>\\.get(.+) )?(?<player>\\S{1,16}): (?<message>.*)");
                    for (String trigger : normalStrings) { normal.add(Pattern.compile(trigger)); }
                }
                // TODO: change to input stream json file
                try { join(); } catch (InterruptedException ignored) {}
            }
        }.start();
    }

    @EventSubscriber(priority = Priority.LOW)
    public void onChatReceived(ChatReceivedEvent event) {
        IChatComponent chatComponent = event.getChatComponent();
        String message = chatComponent.getUnformattedText();
        if (message.isEmpty()) {
            return;
        }
        try {
            if (!isEnabled() || isNormalMessage(message)) return;
            // TODO: Make delay configurable.
            if (isEndOfGame(message)) this.tick = 0; //getSettings().stream().filter(setting -> setting.name.equals("Delay")).findFirst().get()
        } catch (Exception ignored) {}
    }

    @EventSubscriber(priority = Priority.LOW)
    public void onGameTick(GameLoopEvent event) {
        if (this.tick == 0) {
            if (isEnabled())  {
                // TODO: Make command/message configurable.
                Minecraft.getMinecraft().thePlayer.sendChatMessage("/ac GG"); // getSettings().stream().filter(setting -> setting.name.equals("Message").findFirst().get())
            }
            this.tick = -1;
        } else {
            if (this.tick > 0) {
                this.tick--;
            }
        }
    }

    private boolean isNormalMessage(String message) {
        return this.normal.stream().anyMatch(s -> s.matcher(message).find());
    }

    private boolean isEndOfGame(String message) {
        return this.wins.stream().anyMatch(s -> s.matcher(message).find()) || this.events.stream().anyMatch(s -> s.matcher(message).find());
    }

}

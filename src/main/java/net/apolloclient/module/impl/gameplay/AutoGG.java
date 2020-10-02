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

package net.apolloclient.module.impl.gameplay;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import net.apolloclient.Apollo;
import net.apolloclient.event.Priority;
import net.apolloclient.event.bus.SubscribeEvent;
import net.apolloclient.event.impl.client.ChatReceivedEvent;
import net.apolloclient.module.Category;
import net.apolloclient.module.bus.Module;
import net.apolloclient.module.bus.Module.EventHandler;
import net.apolloclient.module.bus.Module.Instance;
import net.apolloclient.module.bus.event.InitializationEvent;
import net.apolloclient.utils.DataUtil;
import net.minecraft.client.Minecraft;

import java.util.ArrayList;
import java.util.regex.Pattern;

/**
 * Module to automatically say a phrase at the end of the game.
 *
 * @author SLLCoding
 * @since b0.2
 */
@Module(name = AutoGG.NAME, description = AutoGG.DESCRIPTION, author = AutoGG.AUTHOR, category = Category.GAMEPLAY, recommendedServersIP = AutoGG.RECOMMENDED_SERVERS, enabled = true)
public class AutoGG {

    public static final String NAME                = "AutoGG";
    public static final String DESCRIPTION         = "Automatically say a phrase at the end of the game.";
    public static final String AUTHOR              = "SLLCoding";
    public static final String RECOMMENDED_SERVERS = "hypixel.net";

    @Instance public static final AutoGG instance = new AutoGG();

    public final String endOfGameMessage = "/ac GG";

    // Patterns to match if the game is over.
    private final ArrayList<Pattern> wins = new ArrayList<>();
    // Patterns to match if an event has happened.
    private final ArrayList<Pattern> events = new ArrayList<>();
    // Patterns to match if its a normal message (Doesn't say GG)
    private final ArrayList<Pattern> normal = new ArrayList<>();

    @EventHandler(priority = Priority.HIGH)
    public void setup (InitializationEvent event) throws Exception {

        JsonObject response = new Gson().fromJson(DataUtil.getDataFromUrlOrLocal("autogg-triggers.json"), JsonObject.class);;
        JsonObject version = response.getAsJsonObject("version");

        Apollo.log("[AutoGG] Using triggers v"
                + version.get("version").getAsString() + " ("
                + version.get("date").getAsString() + ")");

        response
                .getAsJsonArray("wins")
                .forEach(trigger -> wins.add(Pattern.compile(trigger.getAsString())));
        response
                .getAsJsonArray("events")
                .forEach(trigger -> events.add(Pattern.compile(trigger.getAsString())));
        response
                .getAsJsonArray("normal")
                .forEach(trigger -> normal.add(Pattern.compile(trigger.getAsString())));
    }

    @SubscribeEvent(priority = Priority.HIGH)
    public void onChatReceived (ChatReceivedEvent event) {
        String message = event.chatComponent.getUnformattedText();
        if (!isNormalMessage(message) && isEndOfGame(message))
            // TODO: Separate thread with delay? configurable message
            Minecraft.getMinecraft().thePlayer.sendChatMessage(this.endOfGameMessage);
    }

    private boolean isNormalMessage(String message) {
        return this.normal.stream().anyMatch(s -> s.matcher(message).find());
    }

    private boolean isEndOfGame(String message) {
        return this.wins.stream().anyMatch(s -> s.matcher(message).find())
                || this.events.stream().anyMatch(s -> s.matcher(message).find());
    }
}

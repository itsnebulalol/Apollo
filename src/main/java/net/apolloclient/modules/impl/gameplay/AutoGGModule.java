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
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
import net.apolloclient.Apollo;
import net.apolloclient.events.bus.EventSubscriber;
import net.apolloclient.events.bus.Priority;
import net.apolloclient.events.impl.client.ChatReceivedEvent;
import net.apolloclient.events.impl.client.GameLoopEvent;
import net.apolloclient.modules.Category;
import net.apolloclient.modules.Module;
import net.minecraft.client.Minecraft;
import net.minecraft.util.IChatComponent;

/**
 * @author SLLCoding
 * @since b0.2
 */
public class AutoGGModule extends Module {

  // Patterns to match if the game is over.
  private List<Pattern> wins;
  // Patterns to match if an event has happened.
  private List<Pattern> events;
  // Patterns to match if its a normal message (Doesn't say GG)
  private List<Pattern> normal;

  private int tick = -1;

  public AutoGGModule() {
    super("AutoGG", "Automatically say GG at the end of a game.", Category.GAMEPLAY, true);
  }

  /** Sets up triggers. */
  @Override
  public void setup() throws Exception {
    wins = new ArrayList<>();
    events = new ArrayList<>();
    normal = new ArrayList<>();

    JsonObject response =
        new Gson().fromJson(this.getDataFromUrlOrLocal("autogg-triggers.json"), JsonObject.class);
    JsonObject version = response.getAsJsonObject("version");
    Apollo.log(
        "[AutoGG] Using triggers v"
            + version.get("version").getAsString()
            + " ("
            + version.get("date").getAsString()
            + ")");

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
      if (isEndOfGame(message))
        this.tick =
            0; // getSettings().stream().filter(setting ->
               // setting.name.equals("Delay")).findFirst().get()
    } catch (Exception ignored) {
    }
  }

  @EventSubscriber(priority = Priority.LOW)
  public void onGameTick(GameLoopEvent event) {
    if (this.tick == 0) {
      if (isEnabled()) {
        // TODO: Make command/message configurable.
        Minecraft.getMinecraft()
            .thePlayer
            .sendChatMessage(
                "/ac GG"); // getSettings().stream().filter(setting ->
                           // setting.name.equals("Message").findFirst().get())
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
    return this.wins.stream().anyMatch(s -> s.matcher(message).find())
        || this.events.stream().anyMatch(s -> s.matcher(message).find());
  }
}

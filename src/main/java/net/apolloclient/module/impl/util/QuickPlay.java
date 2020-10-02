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

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import net.apolloclient.Apollo;
import net.apolloclient.event.Priority;
import net.apolloclient.module.bus.Module;
import net.apolloclient.module.bus.Module.EventHandler;
import net.apolloclient.module.bus.Module.Instance;
import net.apolloclient.module.bus.event.InitializationEvent;
import net.apolloclient.utils.DataUtil;

import java.util.ArrayList;

/**
 * Choose a game to play in a good-looking menu.
 *
 * @author SLLCoding
 * @since b0.2
 */
@Module(name = QuickPlay.NAME, description = QuickPlay.DESCRIPTION, author = QuickPlay.AUTHOR, recommendedServersIP = QuickPlay.RECOMMENDED_SERVERS)
public class QuickPlay {

    public static final String NAME                = "QuickPlay";
    public static final String DESCRIPTION         = "Choose a game to play in a good-looking menu.";
    public static final String AUTHOR              = "SLLCoding";
    public static final String RECOMMENDED_SERVERS = "hypixel.net";

    @Instance public static final QuickPlay instance = new QuickPlay();

    public final ArrayList<Game> games = new ArrayList<>();

    @EventHandler(priority = Priority.HIGH)
    public void setup (InitializationEvent event) throws Exception {

        JsonObject response = new Gson().fromJson(DataUtil.getDataFromUrlOrLocal("quickplay-games.json"), JsonObject.class);
        JsonObject version = response.getAsJsonObject("version");

        Apollo.log("[Quickplay] Using games list v"
                + version.get("version").getAsString() + " ("
                + version.get("date").getAsString() + ")");

        response
                .getAsJsonArray("games")
                .forEach(game -> games.add(new Game(game.getAsJsonObject())));
    }

    public static class Game {

        public final String name;
        public final String icon;
        public final ArrayList<Mode> modes = new ArrayList<>();

        public Game(JsonObject data) {
            name = data.get("name").getAsString();
            icon = data.get("icon").getAsString();
            data.getAsJsonArray("modes").forEach(mode -> modes.add(new Mode(mode.getAsJsonObject())));
        }

        public static class Mode {

            public final String name;
            public final String command;
            public final boolean enabled;

            public Mode(JsonObject data) {
                name    = data.get("name").getAsString    ();
                command = data.get("command").getAsString ();
                enabled = data.get("enabled").getAsBoolean();
            }
        }
    }

}

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
package io.apollo.modules.impl.example;

import io.apollo.discord.DiscordRP;
import io.apollo.events.Event;
import io.apollo.events.bus.EventSubscriber;
import io.apollo.events.impl.client.GameLoopEvent;
import io.apollo.events.impl.world.LoadWorldEvent;
import io.apollo.modules.Category;
import io.apollo.modules.Module;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiMainMenu;
import net.minecraft.client.gui.GuiMultiplayer;
import org.newdawn.slick.Game;

import static io.apollo.Apollo.discordRP;

public class DiscordRPModule extends Module {
    public DiscordRPModule() { super("DiscordRP", "Updates Discord Rich Presents based on what your doing", Category.DISCORD, true); }

    @EventSubscriber
    public void onWorldLoad(LoadWorldEvent e){
        if(!Minecraft.getMinecraft().isSingleplayer()){
            discordRP.update("Multiplayer", "IP: " + Minecraft.getMinecraft().getCurrentServerData().serverIP, "", "");
        }
        else{
            discordRP.update("Singleplayer", "In Game", "", "");
        }
    }
    @EventSubscriber
    public void onTick(GameLoopEvent event){
        if(Minecraft.getMinecraft().currentScreen instanceof GuiMainMenu){
            discordRP.update("Main Menu", "In Game", "", "");
        }
        else if(Minecraft.getMinecraft().currentScreen instanceof GuiMultiplayer){
            discordRP.update("Multiplayer Menu", "In Game", "", "");
        }
    }
}

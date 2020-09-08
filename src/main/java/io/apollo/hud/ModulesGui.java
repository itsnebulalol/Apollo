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

package io.apollo.hud;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.util.ResourceLocation;

import java.io.IOException;

/** Main HUD to toggle modules and adjust settings. TODO: WIP
 * @author Icovid | Icovid#3888
 * @since 1.0.0 **/
public class ModulesGui extends GuiScreen {

    /** Called when gui is opened. **/
    public void initGui() {

    }

    /** Called when gui is closed. **/
    public void onGuiClosed() { mc.entityRenderer.switchUseShader();  }

    /** Called when {@link GuiButton} is pressed.
     * @param button button pressed
     * @throws IOException unused **/
    protected void actionPerformed(GuiButton button) throws IOException {

    }

    /** Called every tick. used to draw elements on screen
     * @param mouseX x position of cursor
     * @param mouseY y position of cursor
     * @param partialTicks tick per second of screen **/
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        this.drawModuleList(10, (float) this.height / 2, mouseX, mouseY);
        super.drawScreen(mouseX, mouseY, partialTicks);
    }

    /** Draws list of modules and handles hover event
     * @param yPosition x position of gui
     * @param xPosition y position of gui
     * @param mouseX x position of cursor
     * @param mouseY y position of cursor **/
    public void drawModuleList(float yPosition, float xPosition, int mouseX, int mouseY) {

    }
}

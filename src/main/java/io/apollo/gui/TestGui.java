/*⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤
 Copyright (C) 2020-2021 developed by Icovid and Apollo Development Team

 This program is free software: you can redistribute it and/or modify
 it under the terms of the GNU Lesser General Public License as published
 by the Free Software Foundation, either version 3 of the License, or
 (at your option) any later version.

 This program is distributed in the hope that it will be useful,
 but WITHOUT ANY WARRANTY; without even the implied warranty of
 MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 GNU Lesser General Public License for more details.

 You should have received a copy of the GNU Lesser General Public License
 along with this program.  If not, see http://www.gnu.org/licenses/ .
 
 Contact: Icovid#3888 @ https://discord.com
 ⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤*/

package io.apollo.gui;

import io.apollo.utils.ApolloFontRenderer;
import io.apollo.utils.GLRenderer;
import net.minecraft.client.gui.GuiScreen;
import org.newdawn.slick.SlickException;

import java.awt.*;
import java.io.IOException;

public class TestGui extends GuiScreen {

    public void updateScreen() { if (mc.theWorld != null) super.updateScreen();}

    public ApolloFontRenderer apolloFontRenderer;

    /* Called when Gui is Opened in Screen size is Changes */
    public void initGui() {
        try {
            apolloFontRenderer = new ApolloFontRenderer(ApolloFontRenderer.ROBOTO, 20);
        } catch (FontFormatException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SlickException e) {
            e.printStackTrace();
        }
    }

    /* Called Every Tick when Gui is Open - Used for Render Elements */
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        GLRenderer.drawRectangle(100, 100, 100, 100, Color.BLACK);
        GLRenderer.drawLine(100, 100, 200, 200, 10, Color.CYAN);
        GLRenderer.drawCircle(145, 125, 10, Color.CYAN);
        GLRenderer.drawHollowCircle(120, 145, 10, 5, Color.CYAN);
        GLRenderer.drawHollowPartialCircle(50, 50, 20, 0, 90, 10, Color.BLACK);
        GLRenderer.drawPartialCircle(50, 100, 20, 90, 180, Color.BLACK);
        GLRenderer.drawRegularPolygon(200, 50, 20, 9, Color.WHITE);
        GLRenderer.drawRoundedRectangle(300, 100, 100, 50, 8, Color.BLACK);
        apolloFontRenderer.drawString("Apollo Client", 100, 200, new Color(255, 255, 255, 255));
    }
}

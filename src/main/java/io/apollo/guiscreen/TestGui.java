/*⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤
 Copyright (C) 2020-2021 developed by Icovid and Apollo Development Team
 All Contributors can be found in the README.md
 
 TestGui.java is part of Apollo Client. 8/31/20, 8:51 PM
 
 TestGui.java can not be copied and/or distributed without the express
 permission of Icovid
 
 Contact: Icovid#3888 @ https://discord.com
 ⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤*/

package io.apollo.guiscreen;

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

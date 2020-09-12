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
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;

import java.awt.*;
import java.io.IOException;

/** Main HUD to toggle modules and adjust settings. TODO: WIP
 * @author Icovid | Icovid#3888
 * @since 1.0.0 * **/
public class ModulesGui extends GuiScreen {

  private final int yOffset = 0;
  private final int scaleFactor = 1200;
  private final int xOffset = 0;

  /** Called when gui is opened. * */
  public void initGui() {}

  /** Called when gui is closed. * */
  public void onGuiClosed() {
//    mc.entityRenderer.switchUseShader();
  }

  /** Called when {@link GuiButton} is pressed.
   * @param button button pressed
   * @throws IOException unused * **/
  protected void actionPerformed(GuiButton button) throws IOException {}

  /** Called every tick. used to draw elements on screen
   * @param mouseX x position of cursor
   * @param mouseY y position of cursor
   * @param partialTicks tick per second of screen * **/
  public void drawScreen(int mouseX, int mouseY, float partialTicks) { // TODO: 1024

    WorldRenderer worldrenderer = Tessellator.getInstance().getWorldRenderer();
    GlStateManager.enableBlend();
    GlStateManager.disableTexture2D();

    Color color = new Color(0, 0, 0, 107);
    GlStateManager.color(color.getRed() / 255.0F, color.getGreen() / 255.0F, color.getBlue() / 255.0F, color.getAlpha() / 255.0F);

    GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);

    // BASE
    worldrenderer.begin(7, DefaultVertexFormats.POSITION);
    worldrenderer.pos((this.width / 2) - (this.height * 672 / this.scaleFactor) - xOffset, (this.height * 936 / this.scaleFactor) + yOffset, 0.0D).endVertex();
    worldrenderer.pos((this.width / 2) + (this.height * 677 / this.scaleFactor) - xOffset, (this.height * 936 / this.scaleFactor) + yOffset, 0.0D).endVertex();
    worldrenderer.pos((this.width / 2) + (this.height * 677 / this.scaleFactor) - xOffset, (this.height * 923 / this.scaleFactor) + yOffset, 0.0D).endVertex();
    worldrenderer.pos((this.width / 2) - (this.height * 672 / this.scaleFactor) - xOffset, (this.height * 923 / this.scaleFactor) + yOffset, 0.0D).endVertex();
    Tessellator.getInstance().draw();
    worldrenderer.begin(7, DefaultVertexFormats.POSITION);
    worldrenderer.pos((this.width / 2) - (this.height * 684 / this.scaleFactor) - xOffset, (this.height * 923 / this.scaleFactor) + yOffset, 0.0D).endVertex();
    worldrenderer.pos((this.width / 2) - (this.height * 672 / this.scaleFactor) - xOffset, (this.height * 923 / this.scaleFactor) + yOffset, 0.0D).endVertex();
    worldrenderer.pos((this.width / 2) - (this.height * 672 / this.scaleFactor) - xOffset, (this.height * 273 / this.scaleFactor) + yOffset, 0.0D).endVertex();
    worldrenderer.pos((this.width / 2) - (this.height * 684 / this.scaleFactor) - xOffset, (this.height * 273 / this.scaleFactor) + yOffset, 0.0D).endVertex();
    Tessellator.getInstance().draw();
    worldrenderer.begin(7, DefaultVertexFormats.POSITION);
    worldrenderer.pos((this.width / 2) - (this.height * 672 / this.scaleFactor) - xOffset, (this.height * 923 / this.scaleFactor) + yOffset, 0.0D).endVertex();
    worldrenderer.pos((this.width / 2) - (this.height * 197 / this.scaleFactor) - xOffset, (this.height * 923 / this.scaleFactor) + yOffset, 0.0D).endVertex();
    worldrenderer.pos((this.width / 2) - (this.height * 197 / this.scaleFactor) - xOffset, (this.height * 260 / this.scaleFactor) + yOffset, 0.0D).endVertex();
    worldrenderer.pos((this.width / 2) - (this.height * 672 / this.scaleFactor) - xOffset, (this.height * 260 / this.scaleFactor) + yOffset, 0.0D).endVertex();
    Tessellator.getInstance().draw();
    worldrenderer.begin(7, DefaultVertexFormats.POSITION);
    worldrenderer.pos((this.width / 2) - (this.height * 197 / this.scaleFactor) - xOffset, (this.height * 331 / this.scaleFactor) + yOffset, 0.0D).endVertex();
    worldrenderer.pos((this.width / 2) - (this.height * 184 / this.scaleFactor) - xOffset, (this.height * 331 / this.scaleFactor) + yOffset, 0.0D).endVertex();
    worldrenderer.pos((this.width / 2) - (this.height * 184 / this.scaleFactor) - xOffset, (this.height * 273 / this.scaleFactor) + yOffset, 0.0D).endVertex();
    worldrenderer.pos((this.width / 2) - (this.height * 197 / this.scaleFactor) - xOffset, (this.height * 273 / this.scaleFactor) + yOffset, 0.0D).endVertex();
    Tessellator.getInstance().draw();
    worldrenderer.begin(7, DefaultVertexFormats.POSITION);
    worldrenderer.pos((this.width / 2) - (this.height * 197 / this.scaleFactor) - xOffset, (this.height * 923 / this.scaleFactor) + yOffset, 0.0D).endVertex();
    worldrenderer.pos((this.width / 2) + (this.height * 205 / this.scaleFactor) - xOffset, (this.height * 923 / this.scaleFactor) + yOffset, 0.0D).endVertex();
    worldrenderer.pos((this.width / 2) + (this.height * 205 / this.scaleFactor) - xOffset, (this.height * 331 / this.scaleFactor) + yOffset, 0.0D).endVertex();
    worldrenderer.pos((this.width / 2) - (this.height * 197 / this.scaleFactor) - xOffset, (this.height * 331 / this.scaleFactor) + yOffset, 0.0D).endVertex();
    Tessellator.getInstance().draw();
    worldrenderer.begin(7, DefaultVertexFormats.POSITION);
    worldrenderer.pos((this.width / 2) + (this.height * 192 / this.scaleFactor) - xOffset, (this.height * 331 / this.scaleFactor) + yOffset, 0.0D).endVertex();
    worldrenderer.pos((this.width / 2) + (this.height * 205 / this.scaleFactor) - xOffset, (this.height * 331 / this.scaleFactor) + yOffset, 0.0D).endVertex();
    worldrenderer.pos((this.width / 2) + (this.height * 205 / this.scaleFactor) - xOffset, (this.height * 273 / this.scaleFactor) + yOffset, 0.0D).endVertex();
    worldrenderer.pos((this.width / 2) + (this.height * 192 / this.scaleFactor) - xOffset, (this.height * 273 / this.scaleFactor) + yOffset, 0.0D).endVertex();
    Tessellator.getInstance().draw();
    worldrenderer.begin(7, DefaultVertexFormats.POSITION);
    worldrenderer.pos((this.width / 2) + (this.height * 205 / this.scaleFactor) - xOffset, (this.height * 923 / this.scaleFactor) + yOffset, 0.0D).endVertex();
    worldrenderer.pos((this.width / 2) + (this.height * 677 / this.scaleFactor) - xOffset, (this.height * 923 / this.scaleFactor) + yOffset, 0.0D).endVertex();
    worldrenderer.pos((this.width / 2) + (this.height * 677 / this.scaleFactor) - xOffset, (this.height * 260 / this.scaleFactor) + yOffset, 0.0D).endVertex();
    worldrenderer.pos((this.width / 2) + (this.height * 205 / this.scaleFactor) - xOffset, (this.height * 260 / this.scaleFactor) + yOffset, 0.0D).endVertex();
    Tessellator.getInstance().draw();
    worldrenderer.begin(7, DefaultVertexFormats.POSITION);
    worldrenderer.pos((this.width / 2) + (this.height * 677 / this.scaleFactor) - xOffset, (this.height * 923 / this.scaleFactor) + yOffset, 0.0D).endVertex();
    worldrenderer.pos((this.width / 2) + (this.height * 690 / this.scaleFactor) - xOffset, (this.height * 923 / this.scaleFactor) + yOffset, 0.0D).endVertex();
    worldrenderer.pos((this.width / 2) + (this.height * 690 / this.scaleFactor) - xOffset, (this.height * 273 / this.scaleFactor) + yOffset, 0.0D).endVertex();
    worldrenderer.pos((this.width / 2) + (this.height * 677 / this.scaleFactor) - xOffset, (this.height * 273 / this.scaleFactor) + yOffset, 0.0D).endVertex();
    Tessellator.getInstance().draw();

//     OUTLINE
    color = new Color(0, 0, 0, 125);
    GlStateManager.color(color.getRed() / 255.0F, color.getGreen() / 255.0F, color.getBlue() / 255.0F, color.getAlpha() / 255.0F);

    worldrenderer.begin(7, DefaultVertexFormats.POSITION);
    worldrenderer.pos((this.width / 2) - (this.height * 689 / this.scaleFactor) - xOffset, (this.height * 923 / this.scaleFactor) + yOffset, 0.0D).endVertex();
    worldrenderer.pos((this.width / 2) - (this.height * 684 / this.scaleFactor) - xOffset, (this.height * 923 / this.scaleFactor) + yOffset, 0.0D).endVertex();
    worldrenderer.pos((this.width / 2) - (this.height * 684 / this.scaleFactor) - xOffset, (this.height * 273 / this.scaleFactor) + yOffset, 0.0D).endVertex();
    worldrenderer.pos((this.width / 2) - (this.height * 689 / this.scaleFactor) - xOffset, (this.height * 273 / this.scaleFactor) + yOffset, 0.0D).endVertex();
    Tessellator.getInstance().draw();
    worldrenderer.begin(7, DefaultVertexFormats.POSITION);
    worldrenderer.pos((this.width / 2) - (this.height * 672 / this.scaleFactor) - xOffset, (this.height * 260 / this.scaleFactor) + yOffset, 0.0D).endVertex();
    worldrenderer.pos((this.width / 2) - (this.height * 197 / this.scaleFactor) - xOffset, (this.height * 260 / this.scaleFactor) + yOffset, 0.0D).endVertex();
    worldrenderer.pos((this.width / 2) - (this.height * 197 / this.scaleFactor) - xOffset, (this.height * 255 / this.scaleFactor) + yOffset, 0.0D).endVertex();
    worldrenderer.pos((this.width / 2) - (this.height * 672 / this.scaleFactor) - xOffset, (this.height * 255 / this.scaleFactor) + yOffset, 0.0D).endVertex();
    Tessellator.getInstance().draw();
    worldrenderer.begin(7, DefaultVertexFormats.POSITION);
    worldrenderer.pos((this.width / 2) - (this.height * 184 / this.scaleFactor) - xOffset, (this.height * 309 / this.scaleFactor) + yOffset, 0.0D).endVertex();
    worldrenderer.pos((this.width / 2) - (this.height * 179 / this.scaleFactor) - xOffset, (this.height * 309 / this.scaleFactor) + yOffset, 0.0D).endVertex();
    worldrenderer.pos((this.width / 2) - (this.height * 179 / this.scaleFactor) - xOffset, (this.height * 273 / this.scaleFactor) + yOffset, 0.0D).endVertex();
    worldrenderer.pos((this.width / 2) - (this.height * 184 / this.scaleFactor) - xOffset, (this.height * 273 / this.scaleFactor) + yOffset, 0.0D).endVertex();
    Tessellator.getInstance().draw();
    worldrenderer.begin(7, DefaultVertexFormats.POSITION);
    worldrenderer.pos((this.width / 2) - (this.height * 162 / this.scaleFactor) - xOffset, (this.height * 331 / this.scaleFactor) + yOffset, 0.0D).endVertex();
    worldrenderer.pos((this.width / 2) + (this.height * 167 / this.scaleFactor) - xOffset, (this.height * 331 / this.scaleFactor) + yOffset, 0.0D).endVertex();
    worldrenderer.pos((this.width / 2) + (this.height * 167 / this.scaleFactor) - xOffset, (this.height * 326 / this.scaleFactor) + yOffset, 0.0D).endVertex();
    worldrenderer.pos((this.width / 2) - (this.height * 162 / this.scaleFactor) - xOffset, (this.height * 326 / this.scaleFactor) + yOffset, 0.0D).endVertex();
    Tessellator.getInstance().draw();

    GlStateManager.enableTexture2D();
    GlStateManager.disableBlend();

    super.drawScreen(mouseX, mouseY, partialTicks);
  }

  /** Draws list of modules and handles hover event
   * @param yPosition x position of gui
   * @param xPosition y position of gui
   * @param mouseX x position of cursor
   * @param mouseY y position of cursor **/
  public void drawModuleList(int yPosition, int xPosition, int mouseX, int mouseY) {
  }
}

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

import io.apollo.Apollo;
import io.apollo.events.bus.EventSubscriber;
import io.apollo.events.impl.client.input.KeyPressedEvent;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.util.MathHelper;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;

import java.awt.*;
import java.io.IOException;

/** Main HUD to toggle modules and adjust settings. TODO: WIP
 * @author Icovid | Icovid#3888
 * @since 1.0.0 * **/
public class ModulesGui extends GuiScreen {

  private int yOffset = 0;
  private int scaleFactor = 1200;
  private int xOffset = 0;
  private double opacityOffset = 1;

  /** Called when gui is opened. * */
  public void initGui() {
    Apollo.EVENT_BUS.register(this);
  }

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
  public void drawScreen(int mouseX, int mouseY, float partialTicks) {
    WorldRenderer worldrenderer = Tessellator.getInstance().getWorldRenderer();
    GL11.glPushMatrix();
    GlStateManager.enableBlend();
    GlStateManager.disableTexture2D();
    GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);

    // main base
    GlStateManager.color(0, 0, 0, (float) (107 * this.opacityOffset) / 255.0F);

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

    // category box. top left
    GlStateManager.color(0, 0, 0, (float) (48 * this.opacityOffset) / 255.0F);

    worldrenderer.begin(7, DefaultVertexFormats.POSITION);
    worldrenderer.pos((this.width / 2) - (this.height * 640 / this.scaleFactor) - xOffset, (this.height * 327 / this.scaleFactor) + yOffset, 0.0D).endVertex();
    worldrenderer.pos((this.width / 2) - (this.height * 226 / this.scaleFactor) - xOffset, (this.height * 327 / this.scaleFactor) + yOffset, 0.0D).endVertex();
    worldrenderer.pos((this.width / 2) - (this.height * 226 / this.scaleFactor) - xOffset, (this.height * 304 / this.scaleFactor) + yOffset, 0.0D).endVertex();
    worldrenderer.pos((this.width / 2) - (this.height * 640 / this.scaleFactor) - xOffset, (this.height * 304 / this.scaleFactor) + yOffset, 0.0D).endVertex();
    Tessellator.getInstance().draw();
    worldrenderer.begin(7, DefaultVertexFormats.POSITION);
    worldrenderer.pos((this.width / 2) - (this.height * 628 / this.scaleFactor) - xOffset, (this.height * 304 / this.scaleFactor) + yOffset, 0.0D).endVertex();
    worldrenderer.pos((this.width / 2) - (this.height * 240 / this.scaleFactor) - xOffset, (this.height * 304 / this.scaleFactor) + yOffset, 0.0D).endVertex();
    worldrenderer.pos((this.width / 2) - (this.height * 240 / this.scaleFactor) - xOffset, (this.height * 290 / this.scaleFactor) + yOffset, 0.0D).endVertex();
    worldrenderer.pos((this.width / 2) - (this.height * 628 / this.scaleFactor) - xOffset, (this.height * 290 / this.scaleFactor) + yOffset, 0.0D).endVertex();
    Tessellator.getInstance().draw();
    worldrenderer.begin(7, DefaultVertexFormats.POSITION);
    worldrenderer.pos((this.width / 2) - (this.height * 240 / this.scaleFactor) - xOffset, (this.height * 327 / this.scaleFactor) + yOffset, 0.0D).endVertex();
    worldrenderer.pos((this.width / 2) - (this.height * 628 / this.scaleFactor) - xOffset, (this.height * 327 / this.scaleFactor) + yOffset, 0.0D).endVertex();
    worldrenderer.pos((this.width / 2) - (this.height * 628 / this.scaleFactor) - xOffset, (this.height * 341 / this.scaleFactor) + yOffset, 0.0D).endVertex();
    worldrenderer.pos((this.width / 2) - (this.height * 240 / this.scaleFactor) - xOffset, (this.height * 341 / this.scaleFactor) + yOffset, 0.0D).endVertex();
    Tessellator.getInstance().draw();
    // category box top right
    worldrenderer.begin(7, DefaultVertexFormats.POSITION);
    worldrenderer.pos((this.width / 2) + (this.height * 236 / this.scaleFactor) - xOffset, (this.height * 327 / this.scaleFactor) + yOffset, 0.0D).endVertex();
    worldrenderer.pos((this.width / 2) + (this.height * 435 / this.scaleFactor) - xOffset, (this.height * 327 / this.scaleFactor) + yOffset, 0.0D).endVertex();
    worldrenderer.pos((this.width / 2) + (this.height * 435 / this.scaleFactor) - xOffset, (this.height * 304 / this.scaleFactor) + yOffset, 0.0D).endVertex();
    worldrenderer.pos((this.width / 2) + (this.height * 236 / this.scaleFactor) - xOffset, (this.height * 304 / this.scaleFactor) + yOffset, 0.0D).endVertex();
    Tessellator.getInstance().draw();
    worldrenderer.begin(7, DefaultVertexFormats.POSITION);
    worldrenderer.pos((this.width / 2) + (this.height * 248 / this.scaleFactor) - xOffset, (this.height * 304 / this.scaleFactor) + yOffset, 0.0D).endVertex();
    worldrenderer.pos((this.width / 2) + (this.height * 435 / this.scaleFactor) - xOffset, (this.height * 304 / this.scaleFactor) + yOffset, 0.0D).endVertex();
    worldrenderer.pos((this.width / 2) + (this.height * 435 / this.scaleFactor) - xOffset, (this.height * 290 / this.scaleFactor) + yOffset, 0.0D).endVertex();
    worldrenderer.pos((this.width / 2) + (this.height * 248 / this.scaleFactor) - xOffset, (this.height * 290 / this.scaleFactor) + yOffset, 0.0D).endVertex();
    Tessellator.getInstance().draw();
    worldrenderer.begin(7, DefaultVertexFormats.POSITION);
    worldrenderer.pos((this.width / 2) + (this.height * 435 / this.scaleFactor) - xOffset, (this.height * 327 / this.scaleFactor) + yOffset, 0.0D).endVertex();
    worldrenderer.pos((this.width / 2) + (this.height * 248 / this.scaleFactor) - xOffset, (this.height * 327 / this.scaleFactor) + yOffset, 0.0D).endVertex();
    worldrenderer.pos((this.width / 2) + (this.height * 248 / this.scaleFactor) - xOffset, (this.height * 341 / this.scaleFactor) + yOffset, 0.0D).endVertex();
    worldrenderer.pos((this.width / 2) + (this.height * 435 / this.scaleFactor) - xOffset, (this.height * 341 / this.scaleFactor) + yOffset, 0.0D).endVertex();
    Tessellator.getInstance().draw();
    worldrenderer.begin(7, DefaultVertexFormats.POSITION);
    worldrenderer.pos((this.width / 2) + (this.height * 455 / this.scaleFactor) - xOffset, (this.height * 327 / this.scaleFactor) + yOffset, 0.0D).endVertex();
    worldrenderer.pos((this.width / 2) + (this.height * 645 / this.scaleFactor) - xOffset, (this.height * 327 / this.scaleFactor) + yOffset, 0.0D).endVertex();
    worldrenderer.pos((this.width / 2) + (this.height * 645 / this.scaleFactor) - xOffset, (this.height * 304 / this.scaleFactor) + yOffset, 0.0D).endVertex();
    worldrenderer.pos((this.width / 2) + (this.height * 455 / this.scaleFactor) - xOffset, (this.height * 304 / this.scaleFactor) + yOffset, 0.0D).endVertex();
    Tessellator.getInstance().draw();
    worldrenderer.begin(7, DefaultVertexFormats.POSITION);
    worldrenderer.pos((this.width / 2) + (this.height * 455 / this.scaleFactor) - xOffset, (this.height * 304 / this.scaleFactor) + yOffset, 0.0D).endVertex();
    worldrenderer.pos((this.width / 2) + (this.height * 633 / this.scaleFactor) - xOffset, (this.height * 304 / this.scaleFactor) + yOffset, 0.0D).endVertex();
    worldrenderer.pos((this.width / 2) + (this.height * 633 / this.scaleFactor) - xOffset, (this.height * 290 / this.scaleFactor) + yOffset, 0.0D).endVertex();
    worldrenderer.pos((this.width / 2) + (this.height * 455 / this.scaleFactor) - xOffset, (this.height * 290 / this.scaleFactor) + yOffset, 0.0D).endVertex();
    Tessellator.getInstance().draw();
    worldrenderer.begin(7, DefaultVertexFormats.POSITION);
    worldrenderer.pos((this.width / 2) + (this.height * 633 / this.scaleFactor) - xOffset, (this.height * 327 / this.scaleFactor) + yOffset, 0.0D).endVertex();
    worldrenderer.pos((this.width / 2) + (this.height * 455 / this.scaleFactor) - xOffset, (this.height * 327 / this.scaleFactor) + yOffset, 0.0D).endVertex();
    worldrenderer.pos((this.width / 2) + (this.height * 455 / this.scaleFactor) - xOffset, (this.height * 341 / this.scaleFactor) + yOffset, 0.0D).endVertex();
    worldrenderer.pos((this.width / 2) + (this.height * 633 / this.scaleFactor) - xOffset, (this.height * 341 / this.scaleFactor) + yOffset, 0.0D).endVertex();
    Tessellator.getInstance().draw();

    GlStateManager.enableTexture2D();
    GlStateManager.disableBlend();
    GL11.glPopMatrix();

    GL11.glPushMatrix();
    GL11.glEnable(3042);
    GL11.glDisable(3553);

    // corners for main base.
    GlStateManager.color(0, 0, 0, (float) (107 * this.opacityOffset) / 255.0F);

    int yRadius = ((this.height * 273 / this.scaleFactor) + yOffset) - ((this.height * 260 / this.scaleFactor) + yOffset);
    worldrenderer.begin(GL11.GL_TRIANGLE_FAN, DefaultVertexFormats.POSITION);
    worldrenderer.pos((this.width / 2) - (this.height * 672 / this.scaleFactor + xOffset), (this.height * 273 / this.scaleFactor) + yOffset , 0.0D).endVertex();
    for (int i = (int) (0 / 360.0 * 100); i <= (int) (90 / 360.0 * 100); i++) {
      double angle = (Math.PI * 2 * i / 100) + Math.toRadians(180);
      int xRadius = ((this.width / 2) - (this.height * 672 / this.scaleFactor + xOffset)) - ((this.width / 2) - (this.height * 684 / this.scaleFactor) - xOffset);
      worldrenderer.pos((this.width / 2) - (this.height * 672 / this.scaleFactor + xOffset) + Math.sin(angle) * xRadius, (this.height * 273 / this.scaleFactor) + yOffset + Math.cos(angle) * yRadius, 0.0D).endVertex();
    } Tessellator.getInstance().draw();
    worldrenderer.begin(GL11.GL_TRIANGLE_FAN, DefaultVertexFormats.POSITION);
    worldrenderer.pos((this.width / 2) - (this.height * 197 / this.scaleFactor + xOffset), (this.height * 273 / this.scaleFactor) + yOffset , 0.0D).endVertex();
    for (int i = (int) (270 / 360.0 * 100); i <= (int) (360 / 360.0 * 100); i++) {
      double angle = (Math.PI * 2 * i / 100) + Math.toRadians(180);
      int xRadius = ((this.width / 2) - (this.height * 184 / this.scaleFactor + xOffset)) - ((this.width / 2) - (this.height * 197 / this.scaleFactor) - xOffset);
      worldrenderer.pos((this.width / 2) - (this.height * 197 / this.scaleFactor + xOffset) + Math.sin(angle) * xRadius, (this.height * 273 / this.scaleFactor) + yOffset + Math.cos(angle) * yRadius, 0.0D).endVertex();
    } Tessellator.getInstance().draw();
    worldrenderer.begin(GL11.GL_TRIANGLE_FAN, DefaultVertexFormats.POSITION);
    worldrenderer.pos((this.width / 2) + (this.height * 205 / this.scaleFactor - xOffset), (this.height * 273 / this.scaleFactor) + yOffset , 0).endVertex();
    for (int i = (int) (0 / 360.0 * 100); i <= (int) (90 / 360.0 * 100); i++) {
      double angle = (Math.PI * 2 * i / 100) + Math.toRadians(180);
      int xRadius = ((this.width / 2) + (this.height * 205 / this.scaleFactor - xOffset)) - ((this.width / 2) + (this.height * 192 / this.scaleFactor) - xOffset);
      worldrenderer.pos((this.width / 2) + (this.height * 205 / this.scaleFactor - xOffset) + Math.sin(angle) * xRadius, (this.height * 273 / this.scaleFactor) + yOffset + Math.cos(angle) * yRadius, 0.0D).endVertex();
    } Tessellator.getInstance().draw();
    worldrenderer.begin(GL11.GL_TRIANGLE_FAN, DefaultVertexFormats.POSITION);
    worldrenderer.pos((this.width / 2) + (this.height * 677 / this.scaleFactor - xOffset), (this.height * 273 / this.scaleFactor) + yOffset , 0.0D).endVertex();
    for (int i = (int) (270 / 360.0 * 100); i <= (int) (360 / 360.0 * 100); i++) {
      double angle = (Math.PI * 2 * i / 100) + Math.toRadians(180);
      int xRadius =  ((this.width / 2) + (this.height * 690 / this.scaleFactor) - xOffset) - ((this.width / 2) + (this.height * 677 / this.scaleFactor - xOffset));
      worldrenderer.pos((this.width / 2) + (this.height * 677 / this.scaleFactor - xOffset) + Math.sin(angle) * xRadius, (this.height * 273 / this.scaleFactor) + yOffset + Math.cos(angle) * yRadius, 0.0D).endVertex();
    } Tessellator.getInstance().draw();
    yRadius = ((this.height * 936 / this.scaleFactor) + yOffset) - ((this.height * 923 / this.scaleFactor) + yOffset);
    worldrenderer.begin(GL11.GL_TRIANGLE_FAN, DefaultVertexFormats.POSITION);
    worldrenderer.pos((this.width / 2) - (this.height * 672 / this.scaleFactor + xOffset), (this.height * 923 / this.scaleFactor) + yOffset , 0.0D).endVertex();
    for (int i = (int) (90 / 360.0 * 100); i <= (int) (180 / 360.0 * 100); i++) {
      double angle = (Math.PI * 2 * i / 100) + Math.toRadians(180);
      int xRadius = ((this.width / 2) - (this.height * 672 / this.scaleFactor + xOffset)) - ((this.width / 2) - (this.height * 684 / this.scaleFactor) - xOffset);
      worldrenderer.pos((this.width / 2) - (this.height * 672 / this.scaleFactor + xOffset) + Math.sin(angle) * xRadius, (this.height * 923 / this.scaleFactor) + yOffset + Math.cos(angle) * yRadius, 0.0D).endVertex();
    } Tessellator.getInstance().draw();
    worldrenderer.begin(GL11.GL_TRIANGLE_FAN, DefaultVertexFormats.POSITION);
    worldrenderer.pos((this.width / 2) + (this.height * 677 / this.scaleFactor - xOffset), (this.height * 923 / this.scaleFactor) + yOffset , 0.0D).endVertex();
    for (int i = (int) (180 / 360.0 * 100); i <= (int) (270 / 360.0 * 100); i++) {
      double angle = (Math.PI * 2 * i / 100) + Math.toRadians(180);
      int xRadius = ((this.width / 2) + (this.height * 690 / this.scaleFactor - xOffset)) - ((this.width / 2) + (this.height * 677 / this.scaleFactor) - xOffset);
      worldrenderer.pos((this.width / 2) + (this.height * 677 / this.scaleFactor - xOffset) + Math.sin(angle) * xRadius, (this.height * 923 / this.scaleFactor) + yOffset + Math.cos(angle) * yRadius, 0.0D).endVertex();
    } Tessellator.getInstance().draw();


    GlStateManager.color(0, 0, 0, (float) (48 * this.opacityOffset) / 255.0F);
    yRadius = ((this.height * 304 / this.scaleFactor) + yOffset) - ((this.height * 290 / this.scaleFactor) + yOffset);
    worldrenderer.begin(GL11.GL_TRIANGLE_FAN, DefaultVertexFormats.POSITION);
    worldrenderer.pos((this.width / 2) - (this.height * 628 / this.scaleFactor + xOffset), (this.height * 304 / this.scaleFactor) + yOffset , 0.0D).endVertex();
    for (int i = (int) (0 / 360.0 * 100); i <= (int) (90 / 360.0 * 100); i++) {
      double angle = (Math.PI * 2 * i / 100) + Math.toRadians(180);
      int xRadius = ((this.width / 2) - (this.height * 628 / this.scaleFactor) - xOffset) - ((this.width / 2) - (this.height * 640 / this.scaleFactor + xOffset));
      worldrenderer.pos((this.width / 2) - (this.height * 628 / this.scaleFactor + xOffset) + Math.sin(angle) * xRadius, (this.height * 304 / this.scaleFactor) + yOffset + Math.cos(angle) * yRadius, 0.0D).endVertex();
    } Tessellator.getInstance().draw();

    worldrenderer.begin(GL11.GL_TRIANGLE_FAN, DefaultVertexFormats.POSITION);
    worldrenderer.pos((this.width / 2) + (this.height * 248 / this.scaleFactor - xOffset), (this.height * 304 / this.scaleFactor) + yOffset , 0.0D).endVertex();
    for (int i = (int) (0 / 360.0 * 100); i <= (int) (90 / 360.0 * 100); i++) {
      double angle = (Math.PI * 2 * i / 100) + Math.toRadians(180);
      int xRadius = ((this.width / 2) + (this.height * 248 / this.scaleFactor) - xOffset) - ((this.width / 2) + (this.height * 236 / this.scaleFactor - xOffset));
      worldrenderer.pos((this.width / 2) + (this.height * 248 / this.scaleFactor - xOffset) + Math.sin(angle) * xRadius, (this.height * 304 / this.scaleFactor) + yOffset + Math.cos(angle) * yRadius, 0.0D).endVertex();
    } Tessellator.getInstance().draw();

    worldrenderer.begin(GL11.GL_TRIANGLE_FAN, DefaultVertexFormats.POSITION);
    worldrenderer.pos((this.width / 2) - (this.height * 240 / this.scaleFactor + xOffset), (this.height * 304 / this.scaleFactor) + yOffset , 0.0D).endVertex();
    for (int i = (int) (270 / 360.0 * 100); i <= (int) (360 / 360.0 * 100); i++) {
      double angle = (Math.PI * 2 * i / 100) + Math.toRadians(180);
      int xRadius = ((this.width / 2) - (this.height * 226 / this.scaleFactor - xOffset)) -  ((this.width / 2) - (this.height * 240 / this.scaleFactor) + xOffset);
      worldrenderer.pos((this.width / 2) - (this.height * 240 / this.scaleFactor + xOffset) + Math.sin(angle) * xRadius, (this.height * 304 / this.scaleFactor) + yOffset + Math.cos(angle) * yRadius, 0.0D).endVertex();
    } Tessellator.getInstance().draw();

    GL11.glEnable(3553);
    GL11.glDisable(3042);
    GL11.glPopMatrix();

    super.drawScreen(mouseX, mouseY, partialTicks);
  }

  /** Draws list of modules and handles hover event
   * @param yPosition x position of gui
   * @param xPosition y position of gui
   * @param mouseX x position of cursor
   * @param mouseY y position of cursor **/
  public void drawModuleList(int yPosition, int xPosition, int mouseX, int mouseY) {
  }

  @EventSubscriber public void onKey(KeyPressedEvent event) {
    if (event.getKeyCode() == Keyboard.KEY_P) this.scaleFactor = this.scaleFactor - 6;
    if (event.getKeyCode() == Keyboard.KEY_O) this.scaleFactor = this.scaleFactor + 6;
    if (event.getKeyCode() == Keyboard.KEY_DOWN) this.yOffset = yOffset + 8;
    if (event.getKeyCode() == Keyboard.KEY_UP) this.yOffset = yOffset - 8;
    if (event.getKeyCode() == Keyboard.KEY_LEFT) this.xOffset = xOffset + 8;
    if (event.getKeyCode() == Keyboard.KEY_RIGHT) this.xOffset = xOffset - 8;
    if (event.getKeyCode() == Keyboard.KEY_K) this.opacityOffset = opacityOffset + 0.1;
    if (event.getKeyCode() == Keyboard.KEY_L) this.opacityOffset = opacityOffset - 0.1;
  }

}


//    // OUTLINE
//    GlStateManager.color(0, 0, 0, 125 / 255.0F - this.opacityOffset);
//    worldrenderer.begin(7, DefaultVertexFormats.POSITION);
//    worldrenderer.pos((this.width / 2) - (this.height * 689 / this.scaleFactor) - xOffset, (this.height * 923 / this.scaleFactor) + yOffset, 0.0D).endVertex();
//    worldrenderer.pos((this.width / 2) - (this.height * 684 / this.scaleFactor) - xOffset, (this.height * 923 / this.scaleFactor) + yOffset, 0.0D).endVertex();
//    worldrenderer.pos((this.width / 2) - (this.height * 684 / this.scaleFactor) - xOffset, (this.height * 273 / this.scaleFactor) + yOffset, 0.0D).endVertex();
//    worldrenderer.pos((this.width / 2) - (this.height * 689 / this.scaleFactor) - xOffset, (this.height * 273 / this.scaleFactor) + yOffset, 0.0D).endVertex();
//    Tessellator.getInstance().draw();
//    worldrenderer.begin(7, DefaultVertexFormats.POSITION);
//    worldrenderer.pos((this.width / 2) - (this.height * 672 / this.scaleFactor) - xOffset, (this.height * 260 / this.scaleFactor) + yOffset, 0.0D).endVertex();
//    worldrenderer.pos((this.width / 2) - (this.height * 197 / this.scaleFactor) - xOffset, (this.height * 260 / this.scaleFactor) + yOffset, 0.0D).endVertex();
//    worldrenderer.pos((this.width / 2) - (this.height * 197 / this.scaleFactor) - xOffset, (this.height * 255 / this.scaleFactor) + yOffset, 0.0D).endVertex();
//    worldrenderer.pos((this.width / 2) - (this.height * 672 / this.scaleFactor) - xOffset, (this.height * 255 / this.scaleFactor) + yOffset, 0.0D).endVertex();
//    Tessellator.getInstance().draw();
//    worldrenderer.begin(7, DefaultVertexFormats.POSITION);
//    worldrenderer.pos((this.width / 2) - (this.height * 184 / this.scaleFactor) - xOffset, (this.height * 309 / this.scaleFactor) + yOffset, 0.0D).endVertex();
//    worldrenderer.pos((this.width / 2) - (this.height * 179 / this.scaleFactor) - xOffset, (this.height * 309 / this.scaleFactor) + yOffset, 0.0D).endVertex();
//    worldrenderer.pos((this.width / 2) - (this.height * 179 / this.scaleFactor) - xOffset, (this.height * 273 / this.scaleFactor) + yOffset, 0.0D).endVertex();
//    worldrenderer.pos((this.width / 2) - (this.height * 184 / this.scaleFactor) - xOffset, (this.height * 273 / this.scaleFactor) + yOffset, 0.0D).endVertex();
//    Tessellator.getInstance().draw();
//    worldrenderer.begin(7, DefaultVertexFormats.POSITION);
//    worldrenderer.pos((this.width / 2) - (this.height * 162 / this.scaleFactor) - xOffset, (this.height * 331 / this.scaleFactor) + yOffset, 0.0D).endVertex();
//    worldrenderer.pos((this.width / 2) + (this.height * 167 / this.scaleFactor) - xOffset, (this.height * 331 / this.scaleFactor) + yOffset, 0.0D).endVertex();
//    worldrenderer.pos((this.width / 2) + (this.height * 167 / this.scaleFactor) - xOffset, (this.height * 326 / this.scaleFactor) + yOffset, 0.0D).endVertex();
//    worldrenderer.pos((this.width / 2) - (this.height * 162 / this.scaleFactor) - xOffset, (this.height * 326 / this.scaleFactor) + yOffset, 0.0D).endVertex();
//    Tessellator.getInstance().draw();
//    worldrenderer.begin(7, DefaultVertexFormats.POSITION);
//    worldrenderer.pos((this.width / 2) + (this.height * 187 / this.scaleFactor) - xOffset, (this.height * 309 / this.scaleFactor) + yOffset, 0.0D).endVertex();
//    worldrenderer.pos((this.width / 2) + (this.height * 192 / this.scaleFactor) - xOffset, (this.height * 309 / this.scaleFactor) + yOffset, 0.0D).endVertex();
//    worldrenderer.pos((this.width / 2) + (this.height * 192 / this.scaleFactor) - xOffset, (this.height * 273 / this.scaleFactor) + yOffset, 0.0D).endVertex();
//    worldrenderer.pos((this.width / 2) + (this.height * 187 / this.scaleFactor) - xOffset, (this.height * 273 / this.scaleFactor) + yOffset, 0.0D).endVertex();
//    Tessellator.getInstance().draw();
//    worldrenderer.begin(7, DefaultVertexFormats.POSITION);
//    worldrenderer.pos((this.width / 2) + (this.height * 205 / this.scaleFactor) - xOffset, (this.height * 260 / this.scaleFactor) + yOffset, 0.0D).endVertex();
//    worldrenderer.pos((this.width / 2) + (this.height * 677 / this.scaleFactor) - xOffset, (this.height * 260 / this.scaleFactor) + yOffset, 0.0D).endVertex();
//    worldrenderer.pos((this.width / 2) + (this.height * 677 / this.scaleFactor) - xOffset, (this.height * 255 / this.scaleFactor) + yOffset, 0.0D).endVertex();
//    worldrenderer.pos((this.width / 2) + (this.height * 205 / this.scaleFactor) - xOffset, (this.height * 255 / this.scaleFactor) + yOffset, 0.0D).endVertex();
//    Tessellator.getInstance().draw();
//    worldrenderer.begin(7, DefaultVertexFormats.POSITION);
//    worldrenderer.pos((this.width / 2) + (this.height * 690 / this.scaleFactor) - xOffset, (this.height * 923 / this.scaleFactor) + yOffset, 0.0D).endVertex();
//    worldrenderer.pos((this.width / 2) + (this.height * 695 / this.scaleFactor) - xOffset, (this.height * 923 / this.scaleFactor) + yOffset, 0.0D).endVertex();
//    worldrenderer.pos((this.width / 2) + (this.height * 695 / this.scaleFactor) - xOffset, (this.height * 273 / this.scaleFactor) + yOffset, 0.0D).endVertex();
//    worldrenderer.pos((this.width / 2) + (this.height * 690 / this.scaleFactor) - xOffset, (this.height * 273 / this.scaleFactor) + yOffset, 0.0D).endVertex();
//    Tessellator.getInstance().draw();
//    worldrenderer.begin(7, DefaultVertexFormats.POSITION);
//    worldrenderer.pos((this.width / 2) - (this.height * 672 / this.scaleFactor) - xOffset, (this.height * 941 / this.scaleFactor) + yOffset, 0.0D).endVertex();
//    worldrenderer.pos((this.width / 2) + (this.height * 677 / this.scaleFactor) - xOffset, (this.height * 941 / this.scaleFactor) + yOffset, 0.0D).endVertex();
//    worldrenderer.pos((this.width / 2) + (this.height * 677 / this.scaleFactor) - xOffset, (this.height * 936 / this.scaleFactor) + yOffset, 0.0D).endVertex();
//    worldrenderer.pos((this.width / 2) - (this.height * 672 / this.scaleFactor) - xOffset, (this.height * 936 / this.scaleFactor) + yOffset, 0.0D).endVertex();
//    Tessellator.getInstance().draw();
//    // CATEGORIES
//    GlStateManager.color(0, 0, 0, 48 / 255.0F - this.opacityOffset);
//    worldrenderer.begin(7, DefaultVertexFormats.POSITION);
//    worldrenderer.pos((this.width / 2) - (this.height * 640 / this.scaleFactor) - xOffset, (this.height * 327 / this.scaleFactor) + yOffset, 0.0D).endVertex();
//    worldrenderer.pos((this.width / 2) - (this.height * 226 / this.scaleFactor) - xOffset, (this.height * 327 / this.scaleFactor) + yOffset, 0.0D).endVertex();
//    worldrenderer.pos((this.width / 2) - (this.height * 226 / this.scaleFactor) - xOffset, (this.height * 304 / this.scaleFactor) + yOffset, 0.0D).endVertex();
//    worldrenderer.pos((this.width / 2) - (this.height * 640 / this.scaleFactor) - xOffset, (this.height * 304 / this.scaleFactor) + yOffset, 0.0D).endVertex();
//    Tessellator.getInstance().draw();
//    worldrenderer.begin(7, DefaultVertexFormats.POSITION);
//    worldrenderer.pos((this.width / 2) - (this.height * 628 / this.scaleFactor) - xOffset, (this.height * 304 / this.scaleFactor) + yOffset, 0.0D).endVertex();
//    worldrenderer.pos((this.width / 2) - (this.height * 240 / this.scaleFactor) - xOffset, (this.height * 304 / this.scaleFactor) + yOffset, 0.0D).endVertex();
//    worldrenderer.pos((this.width / 2) - (this.height * 240 / this.scaleFactor) - xOffset, (this.height * 290 / this.scaleFactor) + yOffset, 0.0D).endVertex();
//    worldrenderer.pos((this.width / 2) - (this.height * 628 / this.scaleFactor) - xOffset, (this.height * 290 / this.scaleFactor) + yOffset, 0.0D).endVertex();
//    Tessellator.getInstance().draw();
//    worldrenderer.begin(7, DefaultVertexFormats.POSITION);
//    worldrenderer.pos((this.width / 2) - (this.height * 240 / this.scaleFactor) - xOffset, (this.height * 327 / this.scaleFactor) + yOffset, 0.0D).endVertex();
//    worldrenderer.pos((this.width / 2) - (this.height * 628 / this.scaleFactor) - xOffset, (this.height * 327 / this.scaleFactor) + yOffset, 0.0D).endVertex();
//    worldrenderer.pos((this.width / 2) - (this.height * 628 / this.scaleFactor) - xOffset, (this.height * 341 / this.scaleFactor) + yOffset, 0.0D).endVertex();
//    worldrenderer.pos((this.width / 2) - (this.height * 240 / this.scaleFactor) - xOffset, (this.height * 341 / this.scaleFactor) + yOffset, 0.0D).endVertex();
//    Tessellator.getInstance().draw();
//    // CATEGORIES OUTLINE
//    GlStateManager.color(0, 0, 0, 37 / 255.0F - this.opacityOffset);
//    worldrenderer.begin(7, DefaultVertexFormats.POSITION);
//    worldrenderer.pos((this.width / 2) - (this.height * 628 / this.scaleFactor) - xOffset, (this.height * 290 / this.scaleFactor) + yOffset, 0.0D).endVertex();
//    worldrenderer.pos((this.width / 2) - (this.height * 240 / this.scaleFactor) - xOffset, (this.height * 290 / this.scaleFactor) + yOffset, 0.0D).endVertex();
//    worldrenderer.pos((this.width / 2) - (this.height * 240 / this.scaleFactor) - xOffset, (this.height * 286 / this.scaleFactor) + yOffset, 0.0D).endVertex();
//    worldrenderer.pos((this.width / 2) - (this.height * 628 / this.scaleFactor) - xOffset, (this.height * 286 / this.scaleFactor) + yOffset, 0.0D).endVertex();
//    Tessellator.getInstance().draw();
//    worldrenderer.begin(7, DefaultVertexFormats.POSITION);
//    worldrenderer.pos((this.width / 2) - (this.height * 628 / this.scaleFactor) - xOffset, (this.height * 345 / this.scaleFactor) + yOffset, 0.0D).endVertex();
//    worldrenderer.pos((this.width / 2) - (this.height * 240 / this.scaleFactor) - xOffset, (this.height * 345 / this.scaleFactor) + yOffset, 0.0D).endVertex();
//    worldrenderer.pos((this.width / 2) - (this.height * 240 / this.scaleFactor) - xOffset, (this.height * 341 / this.scaleFactor) + yOffset, 0.0D).endVertex();
//    worldrenderer.pos((this.width / 2) - (this.height * 628 / this.scaleFactor) - xOffset, (this.height * 341 / this.scaleFactor) + yOffset, 0.0D).endVertex();
//    Tessellator.getInstance().draw();
//    GlStateManager.enableTexture2D();
//    GlStateManager.disableBlend();
//    GL11.glPopMatrix();
/*
 * ****************************************************************
 *  Copyright (C) 2020-2021 developed by Icovid
 *
 *  GLRenderer.java is part of Apollo Client. [8/28/20, 5:08 PM]
 *
 *  GLRenderer.java can not be copied and/or distributed without the express
 *  permission of Icovid
 *
 *  Contact: Icovid#3888
 * ****************************************************************
 */

package io.apollo.utils;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

import java.awt.*;

/* Various Functions to Draw Gui Objects using GL11 and WorldRenderer */
// Haven't tested most things in this class
public class GLRenderer {

    // TODO: FIX ROUNDED RECT

    /** GlScissor using minecraft screen positions.
     * @param xPosition X start location
     * @param yPosition Y start location
     * @param width glscissor width
     * @param height glscissor height **/
    public static void glScissor(double xPosition, double yPosition, double width, double height) {
        ScaledResolution scaledResolution = new ScaledResolution(Minecraft.getMinecraft());
        GL11.glScissor((int) ((xPosition * Minecraft.getMinecraft().displayWidth) / scaledResolution.getScaledWidth()),
                (int) (((scaledResolution.getScaledHeight() - (yPosition + height)) * Minecraft.getMinecraft().displayHeight) / scaledResolution.getScaledHeight()),
                (int) (width * Minecraft.getMinecraft().displayWidth / scaledResolution.getScaledWidth()),
                (int) (height * Minecraft.getMinecraft().displayHeight / scaledResolution.getScaledHeight()));
    }

    /** Draw a rounded rectangle on screen.
     * @param xPosition x start location
     * @param yPosition y start location
     * @param width width of rectangle
     * @param height height of rectangle
     * @param cornerRadius corner radius of rectangle
     * @param color color of rectangle **/
    public static void drawRoundedRect(int xPosition, int yPosition, int width, int height, int cornerRadius, Color color) {
        drawRect(xPosition, yPosition + cornerRadius, cornerRadius, height - cornerRadius, color);
        drawRect(xPosition + cornerRadius, yPosition, width - cornerRadius, height, color);
        drawRect(xPosition + width - cornerRadius, yPosition + cornerRadius, width, height - cornerRadius, color);
        drawArc(xPosition + cornerRadius, yPosition + cornerRadius, cornerRadius, 0, 90, color);
        drawArc(xPosition + width - cornerRadius, yPosition + cornerRadius, cornerRadius, 270, 360, color);
        drawArc(xPosition + width - cornerRadius, yPosition + height - cornerRadius, cornerRadius, 180, 270, color);
        drawArc(xPosition + cornerRadius, yPosition + height - cornerRadius, cornerRadius, 90, 180, color);
    }

    /** Draw arch on screen based on angle.
     * @param xPosition x start location
     * @param yPosition y start location
     * @param radius radius of angle
     * @param startAngle Start orientation of angle
     * @param endAngle end orientation of angle
     * @param color color of angle
     * @implNote Strait Angles - 90|180|270|360 **/
    public static void drawArc(int xPosition, int yPosition, int radius, int startAngle, int endAngle, Color color) {
        GL11.glPushMatrix();
        GL11.glEnable(3042);
        GL11.glDisable(3553);
        GL11.glBlendFunc(770, 771);
        GL11.glColor4f((float) color.getRed() / 255, (float) color.getGreen() / 255, (float) color.getBlue() / 255, (float) color.getAlpha() / 255);
        WorldRenderer worldRenderer = Tessellator.getInstance().getWorldRenderer();
        worldRenderer.begin(6, DefaultVertexFormats.POSITION);
        worldRenderer.pos(xPosition, yPosition, 0).endVertex();
        for (int i = (int) (startAngle / 360.0 * 100); i <= (int) (endAngle / 360.0 * 100); i++) {
            double angle = (Math.PI * 2 * i / 100) + Math.toRadians(180);
            worldRenderer.pos(xPosition + Math.sin(angle) * radius, yPosition + Math.cos(angle) * radius, 0).endVertex();
        }
        Tessellator.getInstance().draw();
        GL11.glEnable(3553);
        GL11.glDisable(3042);
        GL11.glPopMatrix();
    }

    /** Draw rectangle on screen.
     * @param xPosition x start location
     * @param yPosition y start location
     * @param width width of rectangle
     * @param height height of rectangle
     * @param color color of rectangle **/
    public static void drawRect(double xPosition, double yPosition, double width, double height, Color color) {
        float red = (float) (color.hashCode() >> 16 & 255) / 255.0F;
        float blue = (float) (color.hashCode() >> 8 & 255) / 255.0F;
        float green = (float) (color.hashCode() & 255) / 255.0F;
        float alpha = (float) (color.hashCode() >> 24 & 255) / 255.0F;
        Tessellator tessellator = Tessellator.getInstance();
        WorldRenderer worldrenderer = tessellator.getWorldRenderer();
        GlStateManager.enableBlend();
        GlStateManager.disableTexture2D();
        GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
        GlStateManager.color(red, blue, green, alpha);
        worldrenderer.begin(7, DefaultVertexFormats.POSITION);
        worldrenderer.pos(xPosition, (yPosition + height), 0.0D).endVertex();
        worldrenderer.pos((xPosition + width), (yPosition + height), 0.0D).endVertex();
        worldrenderer.pos((xPosition + width), yPosition, 0.0D).endVertex();
        worldrenderer.pos(xPosition, yPosition, 0.0D).endVertex();
        tessellator.draw();
        GlStateManager.enableTexture2D();
        GlStateManager.disableBlend();
    }

    /** Draw rectangle with border on screen.
     * @param xPosition x start location
     * @param yPosition y start location
     * @param width width of rectangle
     * @param height height of rectangle
     * @param borderWidth width of rectangle border
     * @param color color of rectangle
     * @param borderColor color of rectangle border **/
    public static void drawBorderedRect(double xPosition, double yPosition, double width, double height, int borderWidth, Color color, Color borderColor) {
        float red = (float) (borderColor.hashCode() >> 16 & 255) / 255.0F;
        float blue = (float) (borderColor.hashCode() >> 8 & 255) / 255.0F;
        float green = (float) (borderColor.hashCode() & 255) / 255.0F;
        float alpha = (float) (borderColor.hashCode() >> 24 & 255) / 255.0F;
        drawRect(xPosition, yPosition, width, height, color);
        GL11.glEnable(GL11.GL_BLEND);
        GL11.glDisable(GL11.GL_TEXTURE_2D);
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
        GL11.glEnable(GL11.GL_LINE_SMOOTH);
        GL11.glPushMatrix();
        GlStateManager.color(red, blue, green, alpha);
        GL11.glLineWidth(borderWidth);
        GL11.glBegin(1);
        GL11.glVertex2d(xPosition, yPosition);
        GL11.glVertex2d(xPosition, (yPosition + height));
        GL11.glVertex2d((xPosition + width), (yPosition + height));
        GL11.glVertex2d((xPosition + width), yPosition);
        GL11.glVertex2d(xPosition, yPosition);
        GL11.glVertex2d((xPosition + width), yPosition);
        GL11.glVertex2d(xPosition, (yPosition + height));
        GL11.glVertex2d((xPosition + width), (yPosition + height));
        GL11.glEnd();
        GL11.glPopMatrix();
        GL11.glEnable(GL11.GL_TEXTURE_2D);
        GL11.glDisable(GL11.GL_BLEND);
        GL11.glDisable(GL11.GL_LINE_SMOOTH);
    }

    /** Draw circle on screen.
     * @param xPosition x start location
     * @param yPosition y start location
     * @param radius radius of circle
     * @param color color of circle **/
    public static void drawCircle(int xPosition, int yPosition, int radius, Color color) {
        float red = (float) (color.hashCode() >> 16 & 255) / 255.0F;
        float blue = (float) (color.hashCode() >> 8 & 255) / 255.0F;
        float green = (float) (color.hashCode() & 255) / 255.0F;
        float alpha = (float) (color.hashCode() >> 24 & 255) / 255.0F;
        GL11.glPushMatrix();
        GL11.glEnable(GL11.GL_BLEND);
        GL11.glDisable(GL11.GL_TEXTURE_2D);
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
        GL11.glEnable(GL11.GL_LINE_SMOOTH);
        GL11.glShadeModel(GL11.GL_SMOOTH);
        GL11.glBegin(2);
        for (int i = 0; i < 70; i++) {
            float x = radius * MathHelper.cos((float) (i * 0.08975979010256552D));
            float y = radius * MathHelper.sin((float) (i * 0.08975979010256552D));
            GlStateManager.color(red, blue, green, alpha);
            GL11.glVertex2f(xPosition + x, yPosition + y);
        }
        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
        GL11.glEnd();
        GL11.glEnable(GL11.GL_TEXTURE_2D);
        GL11.glDisable(GL11.GL_BLEND);
        GL11.glDisable(GL11.GL_LINE_SMOOTH);
        GL11.glPopMatrix();
    }

    /** Draw filled circle on screen.
     * @param xPosition x start location
     * @param yPosition y start location
     * @param radius radius of circle
     * @param color color of circle **/
    public static void drawFilledCircle(int xPosition, int yPosition, float radius, Color color) {
        GlStateManager.pushAttrib();
        GlStateManager.pushMatrix();
        GlStateManager.enableBlend();
        GlStateManager.tryBlendFuncSeparate(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA, GL11.GL_ONE, GL11.GL_ZERO);
        GlStateManager.blendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
        GlStateManager.disableTexture2D();
        GL11.glBegin(GL11.GL_TRIANGLE_FAN);
        for (int i = 0; i < 50; i++) {
            float px = xPosition + radius * MathHelper.sin((float) (i * (6.28318530718 / 50)));
            float py = yPosition + radius * MathHelper.cos((float) (i * (6.28318530718 / 50)));
            GL11.glColor4f((float) color.getRed() / 255, (float) color.getGreen() / 255, (float) color.getBlue() / 255, (float) color.getAlpha() / 255);
            GL11.glVertex2d(px, py);
        }
        GL11.glEnd();
        GlStateManager.enableTexture2D();
        GlStateManager.disableBlend();
        GlStateManager.popAttrib();
        GlStateManager.popMatrix();
        GlStateManager.bindTexture(0);
        GlStateManager.color(1.0f, 1.0f, 1.0f, 1.0f);
    }

    /** Draw line on screen.
     * @param xPosition x start location
     * @param yPosition y start location
     * @param x1 x end location
     * @param y1 y end location
     * @param color color of line **/
    public static void drawLine(float xPosition, float yPosition, float x1, float y1, float width, Color color) {
        float red = (float) (color.hashCode() >> 16 & 255) / 255.0F;
        float blue = (float) (color.hashCode() >> 8 & 255) / 255.0F;
        float green = (float) (color.hashCode() & 255) / 255.0F;
        float alpha = (float) (color.hashCode() >> 24 & 255) / 255.0F;
        GlStateManager.enableBlend();
        GlStateManager.disableTexture2D();
        GlStateManager.blendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
        GL11.glEnable(GL11.GL_LINE_SMOOTH);
        GlStateManager.pushMatrix();
        GlStateManager.color(red, green, blue, alpha);
        GL11.glLineWidth(width);
        GL11.glBegin(GL11.GL_LINE_STRIP);
        GL11.glVertex2f(xPosition, yPosition);
        GL11.glVertex2f(x1, y1);
        GL11.glEnd();
        GlStateManager.popMatrix();
        GlStateManager.enableTexture2D();
        GlStateManager.disableBlend();
        GL11.glDisable(GL11.GL_LINE_SMOOTH);
    }

    /** Draw gradient rectangle on screen.
     * @param xPosition x start location
     * @param yPosition y start location
     * @param width width of rectangle
     * @param height height of rectangle
     * @param color start color of rectangle
     * @param color1 end color of rectangle **/
    public static void drawGradientRect(double xPosition, double yPosition, double width, double height, int color, int color1) {
        float f = (color >> 24 & 0xFF) / 255.0F;
        float f1 = (color >> 16 & 0xFF) / 255.0F;
        float f2 = (color >> 8 & 0xFF) / 255.0F;
        float f3 = (color & 0xFF) / 255.0F;
        float f4 = (color1 >> 24 & 0xFF) / 255.0F;
        float f5 = (color1 >> 16 & 0xFF) / 255.0F;
        float f6 = (color1 >> 8 & 0xFF) / 255.0F;
        float f7 = (color1 & 0xFF) / 255.0F;
        GL11.glEnable(3042);
        GL11.glDisable(3553);
        GL11.glBlendFunc(770, 771);
        GL11.glEnable(2848);
        GL11.glShadeModel(7425);
        GL11.glPushMatrix();
        GL11.glBegin(7);
        GL11.glColor4f(f1, f2, f3, f);
        GL11.glVertex2d((xPosition + width), yPosition);
        GL11.glVertex2d(xPosition, yPosition);
        GL11.glColor4f(f5, f6, f7, f4);
        GL11.glVertex2d(xPosition, (yPosition + height));
        GL11.glVertex2d((xPosition + width), (yPosition + height));
        GL11.glEnd();
        GL11.glPopMatrix();
        GL11.glEnable(3553);
        GL11.glDisable(3042);
        GL11.glDisable(2848);
        GL11.glShadeModel(7424);
    }

    /** Draw textured rectangle on screen.
     * @param resourceLocation ResourceLocation of texture
     * @param xPosition x start location
     * @param yPosition y start location
     * @param width width of rectangle
     * @param height height of rectangle **/
    public static void drawTexturedRectangle(ResourceLocation resourceLocation, double xPosition, double yPosition, double width, double height) {
        float u = 1, v = 1, uWidth = 1, vHeight = 1, textureWidth = 1, textureHeight = 1;
        GL11.glEnable(GL11.GL_BLEND);
        Minecraft.getMinecraft().getTextureManager().bindTexture(resourceLocation);
        GL11.glBegin(GL11.GL_QUADS);
        GL11.glTexCoord2d(u / textureWidth, v / textureHeight);
        GL11.glVertex2d(xPosition, yPosition);
        GL11.glTexCoord2d(u / textureWidth, (v + vHeight) / textureHeight);
        GL11.glVertex2d(xPosition, yPosition + height);
        GL11.glTexCoord2d((u + uWidth) / textureWidth, (v + vHeight) / textureHeight);
        GL11.glVertex2d(xPosition + width, yPosition + height);
        GL11.glTexCoord2d((u + uWidth) / textureWidth, v / textureHeight);
        GL11.glVertex2d(xPosition + width, yPosition);
        GL11.glEnd();
        GL11.glDisable(GL11.GL_BLEND);
    }
}

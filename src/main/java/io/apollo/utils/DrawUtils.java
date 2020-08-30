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

/** Various functions to draw gui objects using GL11 and WorldRenderer.
 * @author Icovid | Icovid#3888
 * @since 1.0.0 **/
public class DrawUtils {

    /** {@link GL11} scissor using minecraft screen positions.
     * @param xPosition X start location
     * @param yPosition Y start location
     * @param width scissor width
     * @param height scissor height **/
    public static void glScissor(double xPosition, double yPosition, double width, double height) {
        ScaledResolution scaledResolution = new ScaledResolution(Minecraft.getMinecraft());
        GL11.glScissor((int) ((xPosition * Minecraft.getMinecraft().displayWidth) / scaledResolution.getScaledWidth()),
                (int) (((scaledResolution.getScaledHeight() - (yPosition + height)) * Minecraft.getMinecraft().displayHeight) / scaledResolution.getScaledHeight()),
                (int) (width * Minecraft.getMinecraft().displayWidth / scaledResolution.getScaledWidth()),
                (int) (height * Minecraft.getMinecraft().displayHeight / scaledResolution.getScaledHeight()));
    }

    /** Draw circle on screen.
     * @param xPosition x start location
     * @param yPosition y start location
     * @param radius radius of circle
     * @param color color of circle
     * @param lineWidth width of circle outline **/
    // TODO: FIX GAPS - NOT SHOWN ON LAPTOP
    public static void drawHallowCircle(float xPosition, float yPosition, float radius, Color color, float lineWidth) {
        GlStateManager.pushMatrix();
        GlStateManager.color((float)color.getRed() / 255.0F, (float)color.getGreen() / 255.0F, (float)color.getBlue() / 255.0F, (float)color.getAlpha() / 255.0F);
        GL11.glEnable(2848);
        GL11.glDisable(3553);
        GL11.glEnable(2848);
        GL11.glEnable(3042);
        GL11.glLineWidth(lineWidth);
        GL11.glBegin(2);
        for (int i = 0; i < 70; i++) {
            float x = radius * MathHelper.cos((float) (i * 0.08975979010256552D));
            float y = radius * MathHelper.sin((float) (i * 0.08975979010256552D));
            GL11.glVertex2f(xPosition + x, yPosition + y);
        }
        GL11.glEnd();
        GL11.glDisable(2848);
        GL11.glEnable(3553);
        GlStateManager.popMatrix();
        GL11.glLineWidth(2.0F);
        GlStateManager.color(1.0f, 1.0f, 1.0f, 1.0f);
        GlStateManager.bindTexture(0);
    }

    /** Draw filled circle on screen.
     * @param xPosition x start location
     * @param yPosition y start location
     * @param radius radius of circle
     * @param color color of circle **/
    public static void drawCircle(int xPosition, int yPosition, float radius, Color color) {
        GlStateManager.pushAttrib();
        GlStateManager.pushMatrix();
        GlStateManager.color((float)color.getRed() / 255.0F, (float)color.getGreen() / 255.0F, (float)color.getBlue() / 255.0F, (float)color.getAlpha() / 255.0F);
        GlStateManager.enableBlend();
        GlStateManager.tryBlendFuncSeparate(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA, GL11.GL_ONE, GL11.GL_ZERO);
        GlStateManager.blendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
        GlStateManager.disableTexture2D();
        GL11.glBegin(GL11.GL_TRIANGLE_FAN);
        for (int i = 0; i < 50; i++) {
            float x = xPosition + radius * MathHelper.sin((float) (i * (6.28318530718 / 50)));
            float y = yPosition + radius * MathHelper.cos((float) (i * (6.28318530718 / 50)));
            GL11.glVertex2d(x, y);
        }
        GL11.glEnd();
        GlStateManager.enableTexture2D();
        GlStateManager.disableBlend();
        GlStateManager.popAttrib();
        GlStateManager.popMatrix();
        GlStateManager.color(1.0f, 1.0f, 1.0f, 1.0f);
        GlStateManager.bindTexture(0);
    }

    /** Draw rectangle on screen.
     * @param xPosition x start location
     * @param yPosition y start location
     * @param width width of rectangle
     * @param height height of rectangle
     * @param color color of rectangle **/
    public static void drawRectangle(float xPosition, float yPosition, float width, float height, Color color) {
        GlStateManager.pushMatrix();
        GL11.glEnable(3042);
        GL11.glDisable(3553);
        GL11.glBlendFunc(770, 771);
        GlStateManager.color((float)color.getRed() / 255.0F, (float)color.getGreen() / 255.0F, (float)color.getBlue() / 255.0F, (float)color.getAlpha() / 255.0F);
        GL11.glBegin(7);
        GL11.glVertex2f(xPosition, yPosition + height);
        GL11.glVertex2f(xPosition + width, yPosition + height);
        GL11.glVertex2f(xPosition + width, yPosition);
        GL11.glVertex2f(xPosition, yPosition);
        GL11.glEnd();
        GL11.glEnable(3553);
        GL11.glDisable(3042);
        GlStateManager.popMatrix();
        GlStateManager.color(1.0f, 1.0f, 1.0f, 1.0f);
        GlStateManager.bindTexture(0);
    }

    /** Draw line on screen.
     * @param xPosition x start location
     * @param yPosition y start location
     * @param x1 x end location
     * @param y1 y end location
     * @param color color of line **/
    public static void drawLine(float xPosition, float yPosition, float x1, float y1, float width, Color color) {
        GlStateManager.pushMatrix();
        GlStateManager.color((float)color.getRed() / 255.0F, (float)color.getGreen() / 255.0F, (float)color.getBlue() / 255.0F, (float)color.getAlpha() / 255.0F);
        GL11.glEnable(2848);
        GL11.glDisable(3553);
        GL11.glEnable(2848);
        GL11.glEnable(3042);
        GL11.glLineWidth(width);
        GL11.glBegin(2);
        GL11.glVertex2f(xPosition, yPosition);
        GL11.glVertex2f(x1, y1);
        GL11.glEnd();
        GL11.glDisable(2848);
        GL11.glEnable(3553);
        GlStateManager.popMatrix();
        GL11.glLineWidth(2.0F);
        GlStateManager.color(1.0f, 1.0f, 1.0f, 1.0f);
        GlStateManager.bindTexture(0);
    }

    /** Draw rectangle with border on screen.
     * @param xPosition x start location
     * @param yPosition y start location
     * @param width width of rectangle
     * @param height height of rectangle
     * @param borderWidth width of rectangle border
     * @param color color of rectangle
     * @param borderColor color of rectangle border **/
    public static void drawBorderedRect(float xPosition, float yPosition, float width, float height, float borderWidth, Color color, Color borderColor) {
        // TODO:
    }

    /** Draw rounded rectangle.
     * @param xPosition x location
     * @param yPosition y location
     * @param width width of rectangle
     * @param height height of rectangle
     * @param angle angle of rectangle corners
     * @param color color of rectangle **/
    public static void drawRoundedRectangle(float xPosition, float yPosition, float width, float height, float angle, Color color) {
        // TODO:
    }

    /** Draw rounded rectangle with border.
     * @param xPosition x location
     * @param yPosition y location
     * @param width width of rectangle
     * @param height height of rectangle
     * @param angle angle of rectangle corners
     * @param borderWidth width of rectangle border
     * @param color color of rectangle
     * @param borderColor color of rectangle border **/
    public static void drawBorderedRoundedRectangle(float xPosition, float yPosition, float width, float height, float angle, float borderWidth, Color color, Color borderColor) {
        // TODO:
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
        GlStateManager.color(1.0f, 1.0f, 1.0f, 1.0f);
        GlStateManager.bindTexture(0);
    }

}

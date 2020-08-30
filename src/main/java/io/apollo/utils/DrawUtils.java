package io.apollo.utils;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.MathHelper;
import org.lwjgl.opengl.GL11;

import java.awt.*;

/** Various functions to draw gui objects using GL11 and WorldRenderer.
 * @author Icovid | Icovid#3888
 * @since 1.0.0 **/
public class DrawUtils {

    /** Draw circle on screen.
     * @param xPosition x start location
     * @param yPosition y start location
     * @param radius radius of circle
     * @param color color of circle
     * @param lineWidth width of circle outline **/
    // TODO: FIX GAPS
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
        GlStateManager.bindTexture(0);
        GL11.glLineWidth(2.0F);
        GlStateManager.color(1.0f, 1.0f, 1.0f, 1.0f);
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
        GlStateManager.bindTexture(0);
        GlStateManager.color(1.0f, 1.0f, 1.0f, 1.0f);
    }


}

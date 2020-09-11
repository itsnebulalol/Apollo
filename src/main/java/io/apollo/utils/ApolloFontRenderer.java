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

package io.apollo.utils;

import lombok.SneakyThrows;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.StringUtils;
import org.lwjgl.opengl.GL11;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.UnicodeFont;
import org.newdawn.slick.font.effects.ColorEffect;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

/** Font rendered used for all Apollo guis.
 * @author Icovid | Icovid#3888
 * @see org.newdawn.slick.UnicodeFont
 * @since 1.0.0 **/
public class ApolloFontRenderer extends UnicodeFont {

    // Public fonts used in Apollo Guis.
    public static String ROBOTO = "/fonts/Roboto-Regular.ttf";
    public static String ROBOTO_MEDIUM = "/fonts/Roboto-Medium.ttf";
    public static String ROBOTO_BOLD = "/fonts/Roboto-Bold.ttf";
    public static String ROBOTO_THIN = "/fonts/Roboto-thin.ttf";

    // Cache of strings widths and heights to improve performance.
    private final Map<String, Integer> stringWidthMap = new HashMap<>();
    private final Map<String, Integer> stringHeightMap = new HashMap<>();

    // Pattern and codes to implement color codes into renderer.
    private static final Pattern COLOR_CODE_PATTERN = Pattern.compile("§[0123456789abcdefklmnor]");
    private static final int[] COLOR_CODES = {0x000000, 0x0000AA, 0x00AA00, 0x00AAAA, 0xAA0000, 0xAA00AA, 0xFFAA00, 0xAAAAAA, 0x555555, 0x5555FF, 0x55FF55, 0x55FFFF, 0xFF5555, 0xFF55FF, 0xFFFF55, 0xFFFFFF};

    /** Apollo FontRender using {@link UnicodeFont}.
     * @param fontName string of .ttf font file as {@link InputStream}
     * @param fontSize size font will be renderer **/
    public ApolloFontRenderer(String fontName, float fontSize) throws FontFormatException, SlickException, IOException { this(ApolloFontRenderer.class.getResourceAsStream(fontName), fontSize); }

    /** Apollo FontRender using {@link UnicodeFont}.
     * @param fontStream {@link InputStream} of .ttf font file
     * @param fontSize   size font will be renderer**/
    public ApolloFontRenderer(InputStream fontStream, float fontSize) throws IOException, FontFormatException, SlickException {
        super(Font.createFont(Font.TRUETYPE_FONT, fontStream).deriveFont(fontSize * new ScaledResolution(Minecraft.getMinecraft()).getScaleFactor() / 2));
        this.addAsciiGlyphs();
        this.getEffects().add(new ColorEffect(Color.WHITE));
        this.loadGlyphs();
    }

    /** Apollo FontRender using {@link UnicodeFont}.
     * @param fontFile font .ttf {@link File}
     * @param fontSize size font will be renderer**/
    public ApolloFontRenderer(File fontFile, float fontSize) throws IOException, FontFormatException, SlickException {
        super(Font.createFont(Font.TRUETYPE_FONT, fontFile).deriveFont(fontSize * new ScaledResolution(Minecraft.getMinecraft()).getScaleFactor() / 2));
        this.addAsciiGlyphs();
        this.getEffects().add(new ColorEffect(Color.WHITE));
        this.loadGlyphs();
    }

    /** Get height of string.
     * @param input string to get height for
     * @return height of string **/
    @Override public int getHeight(String input) {
        if (stringHeightMap.size() > 1000) stringHeightMap.clear();
        return stringHeightMap.computeIfAbsent(input, e -> (int) (super.getHeight(input) / 2.0F));
    }

    /** Get width of string.
     * @param input string to get width for
     * @return width of string **/
    @Override public int getWidth(String input) {
        if (stringWidthMap.size() > 1000) stringWidthMap.clear();
        return stringWidthMap.computeIfAbsent(input, e -> super.getWidth(EnumChatFormatting.getTextWithoutFormattingCodes(input)) / new ScaledResolution(Minecraft.getMinecraft()).getScaleFactor());
    }

    /** Draw string on screen.
     * @param text string to be rendered
     * @param xPosition x location of text
     * @param yPosition y location of text
     * @param color {@link org.newdawn.slick.Color} of text
     * @implNote use /n for new line and § for color codes **/
    @Override public void drawString(float xPosition, float yPosition, String text, org.newdawn.slick.Color color) {
        ScaledResolution resolution = new ScaledResolution(Minecraft.getMinecraft());
        GlStateManager.bindTexture(0);
        xPosition *= resolution.getScaleFactor();
        yPosition *= resolution.getScaleFactor();

        GlStateManager.pushMatrix();
        GlStateManager.scale(1 / (float) resolution.getScaleFactor(), 1 / (float) resolution.getScaleFactor(), 1 / (float) resolution.getScaleFactor());
        GlStateManager.color((float) color.getRed() / 255.0F, (float) color.getGreen() / 255.0F, (float) color.getBlue() / 255.0F, (float) color.getAlpha() / 255.0F);

        GlStateManager.disableLighting();
        GlStateManager.enableBlend();
        GlStateManager.tryBlendFuncSeparate(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA, GL11.GL_ONE, GL11.GL_ZERO);
        GlStateManager.blendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);

        // TODO : COLOR AND /n/r

        super.drawString(xPosition, yPosition, text, color);

        GlStateManager.color(1F, 1F, 1F, 1F);
        GlStateManager.bindTexture(0);
        GlStateManager.popMatrix();
    }

    /** Draw string on screen.
     * @param text string to be rendered
     * @param xPosition x location of text
     * @param yPosition y location of text
     * @param color color of text **/
    public void drawString(String text, float xPosition, float yPosition, Color color) { this.drawString(xPosition, yPosition, text, new org.newdawn.slick.Color(color.hashCode())); }

    /** Draw string in center of xPosition
     * @param text string to be rendered
     * @param xPosition x location of text
     * @param yPosition y location of text
     * @param color color of text **/
    public void drawCenteredString(String text, float xPosition, float yPosition, Color color) { this.drawString(text, xPosition - (getWidth(text) >> 1), yPosition, color); }

    /** Draw string with shadow.
     * @param text string to be rendered
     * @param xPosition x location of text
     * @param yPosition y location of text
     * @param color color of text **/
    public void drawStringWithShadow(String text, float xPosition, float yPosition, Color color) {
        this.drawString(StringUtils.stripControlCodes(text), xPosition + 0.5F, yPosition + 0.5F, new Color(0,0,0));
        this.drawString(text, xPosition, yPosition, color);
    }

    /** Draw string in center of xPosition with shadow.
     * @param text string to be rendered
     * @param xPosition x location of text
     * @param yPosition y location of text
     * @param color color of text **/
    public void drawCenteredStringWithShadow(String text, float xPosition, float yPosition, Color color) {
        this.drawCenteredString(StringUtils.stripControlCodes(text), xPosition + 0.5F, yPosition + 0.5F, new Color(0,0,0));
        this.drawCenteredString(text, xPosition, yPosition, color);
    }

    /** Draw string with shadow.
     * @param text string to be rendered
     * @param xPosition x location of text
     * @param yPosition y location of text
     * @param color color of text
     * @param shadowColor color of text shadow **/
    public void drawStringWithShadow(String text, float xPosition, float yPosition, Color color, Color shadowColor) {
        this.drawString(StringUtils.stripControlCodes(text), xPosition + 0.5F, yPosition + 0.5F, shadowColor);
        this.drawString(text, xPosition, yPosition, color);
    }

    /** Draw string in center of xPosition with shadow.
     * @param text string to be rendered
     * @param xPosition x location of text
     * @param yPosition y location of text
     * @param color color of text
     * @param shadowColor color of text shadow **/
    public void drawCenteredStringWithShadow(String text, float xPosition, float yPosition, Color color, Color shadowColor) {
        this.drawCenteredString(StringUtils.stripControlCodes(text), xPosition + 0.5F, yPosition + 0.5F, shadowColor);
        this.drawCenteredString(text, xPosition, yPosition, color);
    }

    /** Draw list of strings on screen.
     * @param lines     strings to be rendered
     * @param xPosition x location of text
     * @param yPosition start y location of text
     * @param color     color of text **/
    public void drawSplitString(ArrayList<String> lines, int xPosition, int yPosition, Color color) { this.drawString(String.join("\n", lines), xPosition, yPosition, color); }

    /** Draw list of strings with shadow.
     * @param lines     strings to be rendered
     * @param xPosition x location of text
     * @param yPosition start y location of text
     * @param color     color of text **/
    public void drawWrapStringWithShadow(ArrayList<String> lines, int xPosition, int yPosition, Color color) { this.drawStringWithShadow(String.join("\n", lines), xPosition, yPosition, color); }

    /** Draw list of strings width shadow.
     * @param lines       strings to be rendered
     * @param xPosition   x location of text
     * @param yPosition   start y location of text
     * @param color       color of text
     * @param shadowColor color of text shadow **/
    public void drawWrapStringWithShadow(ArrayList<String> lines, int xPosition, int yPosition, Color color, Color shadowColor) { this.drawStringWithShadow(String.join("\n", lines), xPosition, yPosition, color, shadowColor); }

    /** Spilt string if overlaps set width.
     * @param text      text to spilt
     * @param wrapWidth width to split at
     * @return array of split strings **/
    public ArrayList<String> wrapStringAtWidth(String text, int wrapWidth) {
        ArrayList<String> lines = new ArrayList<>();
        StringBuilder stringBuilder = new StringBuilder();
        boolean ignoreWidth = true;
        for (String word : text.split(" ")) {
            if (ignoreWidth) stringBuilder.append(word).append(" ");
            else if (this.getWidth(stringBuilder.append(word).append(" ").toString()) <= wrapWidth) {
                stringBuilder.append(word).append(" ");
                ignoreWidth = false;
            } else {
                lines.add(stringBuilder.toString());
                stringBuilder = new StringBuilder();
                ignoreWidth = true;
            }
        }
        return lines;
    }
}

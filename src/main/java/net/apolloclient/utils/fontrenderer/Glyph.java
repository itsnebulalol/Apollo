package net.apolloclient.utils.fontrenderer;

import com.google.gson.JsonObject;

/**
 * Holds glyph information for each char in
 *
 * @author Icovid | Icovid#3888
 * @since b0.2
 */
public class Glyph {

    public final int charID;
    public final double xTextureCord;
    public final double yTextureCord;
    public final int textureWidth;
    public final int textureHeight;
    public final int xOffset;
    public final int yOffset;
    public final int xAdvanced;

    /**
     * Constructs a newly allocated {@code Glyph} object that
     * represents the specified {@code char} value and Hiero information.
     *
     * @param value character of glyph
     * @param hieroObject hiero settings object
     */
    public Glyph(char value, JsonObject hieroObject) {
        this.charID = value;
        this.xTextureCord = hieroObject.get("x").getAsDouble();
        this.yTextureCord = hieroObject.get("y").getAsDouble();
        this.xOffset = hieroObject.get("xOffset").getAsInt();
        this.yOffset = hieroObject.get("yOffset").getAsInt();
        this.xAdvanced = hieroObject.get("xAdvance").getAsInt();
        this.textureWidth = hieroObject.get("width").getAsInt();
        this.textureHeight = hieroObject.get("height").getAsInt();
    }

    /**
     * Constructs a newly allocated {@code Glyph} object that
     * represents the specified {@code char} value and Hiero information.
     *
     * @param xTextureCord x position of char
     * @param yTextureCord y position of char
     * @param textureWidth width of char
     * @param textureHeight height of char
     * @param xOffset char x offset
     * @param yOffset char of y offset
     * @param xAdvanced unused
     */
    public Glyph(char value, double xTextureCord, double yTextureCord, int textureWidth, int textureHeight, int xOffset, int yOffset, int xAdvanced) {
        this.charID = value;
        this.xTextureCord = xTextureCord;
        this.yTextureCord = yTextureCord;
        this.textureWidth = textureWidth;
        this.textureHeight = textureHeight;
        this.xOffset = xOffset;
        this.yOffset = yOffset;
        this.xAdvanced = xAdvanced;
    }
}

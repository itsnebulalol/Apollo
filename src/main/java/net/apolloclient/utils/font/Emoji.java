package net.apolloclient.utils.font;

import net.minecraft.client.gui.Gui;

/**
 * Hold information used to store and draw emojis in
 * {@link ApolloFontRenderer}
 *
 * @author Icovid | Icovid#3888
 * @since b0.2
 */
public class Emoji {

    public final int xOffset;
    public final int yOffset;
    public final int width;
    public final int height;

    /**
     * @param xOffset x atlas offset
     * @param yOffset y atlas offset
     * @param width texture width
     * @param height texture height
     */
    public Emoji(int xOffset, int yOffset, int width, int height) {
        this.xOffset = xOffset;
        this.yOffset = yOffset;
        this.width = width;
        this.height = height;
    }

    /**
     * renders emoji in text line.
     *
     * @param xPosition x position of emoji
     * @param yPosition y position of emoji
     * @param scale scale for emoji
     */
    public void draw(int xPosition, int yPosition, int scale) {
        Gui.drawScaledCustomSizeModalRect(xPosition, yPosition, xOffset, yOffset,
                width, height, getWidth(scale), scale, ApolloFontRenderer.EMOJI_ATLAS_SIZE, ApolloFontRenderer.EMOJI_ATLAS_SIZE);
    }

    /**
     * @param scale emoji height
     * @return width of emoji relative to height and scale
     */
    public int getWidth(int scale) {
        return scale  * (width / height);
    }
}

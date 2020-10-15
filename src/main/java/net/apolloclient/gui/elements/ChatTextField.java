package net.apolloclient.gui.elements;

import net.apolloclient.utils.GLRenderer;
import net.apolloclient.utils.font.ApolloFontRenderer;
import net.minecraft.client.gui.Gui;
import org.lwjgl.input.Keyboard;

import java.awt.*;

public class ChatTextField extends Gui {

    private final ApolloFontRenderer fontRenderer;

    public final int xPosition;
    public final int yPosition;
    public final int width;
    public final int height;

    private StringBuilder active_text = new StringBuilder(100);
    private final int text_color = 0xFAFAFA;
    private final int emoji_color = 0xA834EC;

    /**
     * @param fontRenderer renderer to draw text
     * @param xPosition x position of element
     * @param yPosition y position of element
     * @param width width of element
     * @param height height of element
     */
    public ChatTextField(ApolloFontRenderer fontRenderer, int xPosition, int yPosition, int width, int height) {
        this.fontRenderer = fontRenderer;
        this.xPosition = xPosition;
        this.yPosition = yPosition;
        this.width = width;
        this.height = height;
    }

    /**
     * Called when key is typed in gui
     *
     * @param character char typed
     * @param keycode key typed code
     */
    public void keyTyped(char character, int keycode) {
        if (keycode == Keyboard.KEY_BACK && active_text.length() > 1) {
            active_text = new StringBuilder(active_text.substring(0, active_text.length() - 1));
        }
        else active_text.append(character);
    }

    /**
     * Called each tick to draw text box
     *
     * @param mouseX mouse x pos
     * @param mouseY mouse y pos
     * @param partialTicks ticks
     */
    public void draw(int mouseX, int mouseY, float partialTicks) {
        GLRenderer.drawRoundedRectangle(xPosition, yPosition, width, height, 3, new Color(0,0,0, 124));
        fontRenderer.drawString(active_text.toString(), xPosition + 3, yPosition, new Color(text_color), false);
    }
}

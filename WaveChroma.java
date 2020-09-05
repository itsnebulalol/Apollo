package io.apollo.utils.colours;

import net.minecraft.client.Minecraft;

import java.awt.*;

/** Cool wavechroma text
 * @author Pinkulu | pinkulu#6260
 * @since 1.0.0 **/
public class WaveChroma
{
    public static void drawChromaString(String string, int x, int y, boolean shadow)
    {
        for (char textChar : string.toCharArray())
        {
            long l = System.currentTimeMillis() - (x * 10 - y * 10);
            int i = Color.HSBtoRGB(l % (int) 2000.0F / 2000.0F, 0.7F, 0.9F);
            String tcs = String.valueOf(textChar);
            Minecraft.getMinecraft().fontRendererObj.drawString(tcs, x, y, i, shadow);
            x += Minecraft.getMinecraft().fontRendererObj.getCharWidth(textChar);
        }
    }
    /**
     * @usage
     * WaveChroma.drawChromaString("UwU this text is waveyyyyyyyyyyyyy", 1, 1, true);
     * **/

}
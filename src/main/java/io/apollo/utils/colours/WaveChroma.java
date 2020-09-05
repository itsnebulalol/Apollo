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
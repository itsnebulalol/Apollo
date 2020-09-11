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

package io.apollo.hud.framework;

import lombok.Getter;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;

import java.util.ArrayList;

/** Basic panel hold objects to display and resolution to display them at.
 * @author Icovid | Icovid#3888
 * @since 1.0.0 **/
public class Panel {

    @Getter private final String name;
    @Getter private int xPosition;
    @Getter private int yPosition;

    @Getter private int scaledWidth;
    @Getter private int scaledHeight;
    @Getter private ScaledResolution scaledResolution;

    private final ArrayList<Element> elements = new ArrayList<>();

    public Panel (String name, int xPosition, int yPosition) {
        this.name = name;
        this.xPosition = xPosition;
        this.yPosition = yPosition;
        this.scaledResolution = new ScaledResolution(Minecraft.getMinecraft());
        this.scaledHeight = scaledResolution.getScaledHeight();
        this.scaledWidth = scaledResolution.getScaledWidth();
    }

    public Panel position (int xPosition, int yPosition) {
        this.xPosition = xPosition;
        this.yPosition = yPosition;
        return this;
    }

    public Panel resolution (int scaledWidth, int scaledHeight) {
        this.scaledWidth = scaledWidth;
        this.scaledHeight = scaledHeight;
        this.scaledResolution = new ScaledResolution(Minecraft.getMinecraft());
        return this;
    }

    public Panel addElement (Element element) {
        this.elements.add(element);
        return this;
    }

    public void draw() {

    }

}

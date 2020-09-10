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

import javafx.scene.layout.Pane;
import lombok.Getter;

/**
 * Hud Panel can be used to hold child object and organize UIs
 * <p>All child element of panel will follow panels resolution
 * unless specified otherwise with {@link HudElement}.resolution()</p>
 * @author Icovid | Icovid#3888
 * @since 1.0.0 **/
public class Panel extends HudElement {

    @Getter private int width;
    @Getter private int height;

    public Panel (String name, int xPosition, int yPosition) { super(name, xPosition, yPosition); }
    public Panel (String name) { super(name); }

    @Override public Panel position (int xPosition, int yPosition) { return (Panel) super.position(xPosition, yPosition); }
    @Override public Panel resolution (int resolutionWidth, int resolutionHeight) { return (Panel) super.resolution(resolutionWidth, resolutionHeight); }
    @Override public Panel ignoreResolution () { return (Panel) super.ignoreResolution(); }

    @Override public void draw () {

    }
}

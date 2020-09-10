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

/** Basic hud element
 * <p>All elements wil extends this in some way.</p>
 * @author Icovid | Icovid#3888
 * @since 1.0.0 **/
public abstract class HudElement {

    // Name can be used to edit element when is child.
    @Getter private final String name;
    @Getter private int xPosition;
    @Getter private int yPosition;
    // Resolution Element will be scaled too.
    @Getter private int resolutionWidth = 2048;
    @Getter private int resolutionHeight = 1152;
    @Getter private boolean customResolution = false;
    @Getter private boolean ignoreResolution = false;

    /** Create and HudElement.
     * @param name name of the element.
     * @param xPosition x position of element.
     * @param yPosition y position of element. **/
    public HudElement (String name, int xPosition, int yPosition) {
        this.name = name;
        this.xPosition = xPosition;
        this.yPosition = yPosition;
    }

    /** Create HudElement at 0,0.
     * @param name name of the element. **/
    public HudElement (String name) { this(name, 0, 0); }

    /** Change the position of element.
     * <p>Default Position is 0,0.</p>
     * @param xPosition x position of element.
     * @param yPosition y position of element.
     * @return the element. **/
    public HudElement position(int xPosition, int yPosition) {
        this.xPosition = xPosition;
        this.yPosition = yPosition;
        return this;
    }

    /** Change the resolution the element is scaled too.
     * <p>Default resolution is 2048 / 1152.</p>
     * @param resolutionWidth width to scale element.
     * @param resolutionHeight height to scale element.
     * @return the element. **/
    public HudElement resolution(int resolutionWidth, int resolutionHeight) {
        this.customResolution = true;
        this.ignoreResolution = false;
        this.resolutionWidth = resolutionWidth;
        this.resolutionHeight = resolutionHeight;
        return this;
    }

    /** Ignore scaled resolution for element **/
    public HudElement ignoreResolution() {
        this.ignoreResolution = true;
        return this;
    }

    public abstract void draw();
}

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

package net.apolloclient.hud.framework.objects;

import java.awt.*;
import lombok.Getter;
import lombok.Setter;
import net.apolloclient.Apollo;
import net.apolloclient.hud.framework.Panel;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.WorldRenderer;

/**
 * Draws a rectangle on screen.
 *
 * @author Icovid | Icovid#3888
 * @deprecated scratching framework / to ambitious
 * @since 1.0.0
 */
public class Rectangle extends GuiElement {

  // Position of rectangle with either 4 points or 2 w/ width and height.
  @Getter @Setter private int x1;
  @Getter @Setter private int y1;
  @Getter @Setter private int x2 = -1;
  @Getter @Setter private int y2 = -1;
  // Color of rectangle.
  @Getter @Setter private Color color;
  @Getter @Setter private Color outlineColor;
  // Outline width of rectangle.
  @Getter @Setter private int outlineLeft = 0;
  @Getter @Setter private int outlineTop = 0;
  @Getter @Setter private int outlineRight = 0;
  @Getter @Setter private int outlineBottom = 0;
  // holds value for each corner rectangle.
  @Getter @Setter private int topLeftCorner = 0;
  @Getter @Setter private int topRightCorner = 0;
  @Getter @Setter private int bottomLeftCorner = 0;
  @Getter @Setter private int bottomRightCorner = 0;

  /** @param name name of the rectangle */
  public Rectangle(String name) {
    super(name);
  }

  /**
   * change location of rectangle
   *
   * @param x1 first x position
   * @param y1 first y position
   */
  public Rectangle position(int x1, int y1) {
    this.x1 = x1;
    this.y1 = y1;
    return this;
  }

  /**
   * change location of rectangle
   *
   * @param x1 first x position
   * @param y1 first y position
   * @param x2 second x position
   * @param y2 second y position
   */
  public Rectangle position(int x1, int y1, int x2, int y2) {
    this.x1 = x1;
    this.y1 = y1;
    this.x2 = x2;
    this.y1 = y2;
    return this;
  }

  /**
   * Set the size of the rectangle.
   *
   * @param width width of rectangle.
   * @param height height of rectangle.
   * @return this rectangle
   */
  public Rectangle size(int width, int height) {
    if (this.x2 != -1) {
      Apollo.error(
          "[framework-" + this.name + "] Object controlled by points, size has been ignored!");
      return this;
    }
    this.x2 = x1 + width;
    this.y2 = y1 + height;
    return this;
  }

  /**
   * Round the rectangles corners
   *
   * @param amount amount to round.
   * @return this rectangle
   */
  public Rectangle round(int amount) {
    this.topLeftCorner = amount;
    this.topRightCorner = amount;
    this.bottomLeftCorner = amount;
    this.bottomRightCorner = amount;
    return this;
  }

  /**
   * Round corners of rectangle per corner.
   *
   * @param topLeft top left corner
   * @param topRight top right corner
   * @param bottomLeft bottom left corner
   * @param bottomRight bottom right corner
   * @return this rectangle
   */
  public Rectangle corners(int topLeft, int topRight, int bottomLeft, int bottomRight) {
    this.topLeftCorner = topLeft;
    this.topRightCorner = topRight;
    this.bottomLeftCorner = bottomLeft;
    this.bottomRightCorner = bottomRight;
    return this;
  }

  /**
   * Outline rectangle.
   *
   * @param amount size of outline
   * @return this rectangle
   */
  public Rectangle outline(int amount) {
    this.outlineLeft = amount;
    this.outlineRight = amount;
    this.outlineTop = amount;
    this.outlineBottom = amount;
    return this;
  }

  /**
   * Outline rectangle on a per side basis.
   *
   * @param outlineLeft left border size
   * @param outlineTop top border size
   * @param outlineRight right border size
   * @param outlineBottom bottom border size
   * @return this rectangle
   */
  public Rectangle outlines(int outlineLeft, int outlineTop, int outlineRight, int outlineBottom) {
    this.outlineLeft = outlineLeft;
    this.outlineRight = outlineRight;
    this.outlineTop = outlineTop;
    this.outlineBottom = outlineBottom;
    return this;
  }

  /**
   * Color of rectangle.
   *
   * @param color color to set rectangle
   * @return this rectangle
   */
  public Rectangle color(Color color) {
    this.color = color;
    return this;
  }

  /**
   * Color of rectangle outline.
   *
   * @param color color to set outline
   * @return this rectangle
   */
  public Rectangle outlineColor(Color color) {
    this.outlineColor = color;
    return this;
  }

  /** Try to fix any errors caused when creating Rectangle */
  @Override
  public Rectangle build() {
    if (this.color == null) {
      this.color = Color.BLACK;
      Apollo.error(
          "[framework-" + this.name + "] Rectangle had no color and has been defaulted to BLACK!");
    }
    if (this.outlineColor == null
        && (this.outlineBottom > 0
            || this.outlineTop > 0
            || this.outlineLeft > 0
            || this.outlineRight > 0)) {
      this.outlineColor = Color.darkGray;
      Apollo.error(
          "[framework-"
              + this.name
              + "] Rectangle had no outline color and has been defaulted to DARK-GRAY!");
    }
    if (this.x2 == -1) {
      this.x2 = this.x1 + 10;
      this.y2 = this.y1 + 10;
      Apollo.error(
          "[framework-"
              + this.name
              + "] Rectangle has no size or secondary position and has be defaulted to 10:10");
    }
    if (this.topLeftCorner > ((this.y2 - this.y1) / 2)
        || this.topRightCorner > ((this.y2 - this.y1) / 2)
        || this.bottomRightCorner > ((this.y2 - this.y1) / 2)
        || this.bottomLeftCorner > ((this.y2 - this.y1) / 2)) {
      Apollo.error(
          "[framework-" + this.name + "] Rectangle corner was to large and have been reduced!");
    }
    if (this.topLeftCorner > ((this.y2 - this.y1) / 2)) {
      this.topLeftCorner = ((this.y2 - this.y1) / 2);
    }
    if (this.topRightCorner > ((this.y2 - this.y1) / 2)) {
      this.topRightCorner = ((this.y2 - this.y1) / 2);
    }
    if (this.bottomRightCorner > ((this.y2 - this.y1) / 2)) {
      this.bottomRightCorner = ((this.y2 - this.y1) / 2);
    }
    if (this.bottomLeftCorner > ((this.y2 - this.y1) / 2)) {
      this.bottomLeftCorner = ((this.y2 - this.y1) / 2);
    }
    return this;
  }

  /** Generates the GL attributes used for a rectangle. */
  @Override
  public final void openGLAttributes() {
    GlStateManager.pushMatrix();
    GlStateManager.enableBlend();
    GlStateManager.disableTexture2D();
    GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
  }

  /** Closes the GL attributes used for a rectangle. */
  @Override
  public final void closeGLAttributes() {
    GlStateManager.enableTexture2D();
    GlStateManager.disableBlend();
    GlStateManager.popMatrix();
  }

  @Override
  public void draw(Panel panel) {

    WorldRenderer worldrenderer = Tessellator.getInstance().getWorldRenderer();
    int x1, y1, x2, y2;

    x1 = panel.getXPosition() + panel.scale(this.x1);
    y1 = panel.getYPosition() + panel.scale(this.y1);
    x2 = panel.getXPosition() + panel.scale(this.x2);
    y2 = panel.getYPosition() + panel.scale(this.y2);

    int longerLeftGap =
        this.topLeftCorner > this.bottomLeftCorner ? this.topLeftCorner : this.bottomLeftCorner;
    int longerRightGap =
        this.topRightCorner > this.bottomRightCorner ? this.topRightCorner : this.bottomRightCorner;
  }
}

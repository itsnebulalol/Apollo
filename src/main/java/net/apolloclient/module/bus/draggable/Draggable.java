package net.apolloclient.module.bus.draggable;

import net.apolloclient.module.ModuleContainer;
import net.apolloclient.module.bus.draggable.ScreenPosition;

/**
 * Hold Draggable information for {@link ModuleContainer} and
 * supplies the draw method
 *
 * @author Icovid | Icovid#3888
 * @since b0.2
 */
public interface Draggable {

    /**
     * Draws element in drag gui and on screen
     *
     * @param xPosition current x position of element
     * @param yPosition current y position of element
     * @param width current width of element
     * @param height current height of element
     */
    void draw(int xPosition, int yPosition, int width, int height);

    /**
     * @return default {@link ScreenPosition} of module if module
     * has not be moved
     */
    default ScreenPosition getDefaultScreenPositionScreenPosition() { return new ScreenPosition(0, 10); }

    /**
     * @return default scale of the modules ui element
     */
    default int defaultScale() { return 0; }
}

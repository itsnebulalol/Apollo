package net.apolloclient.events.impl.client;

import lombok.Getter;
import net.apolloclient.events.Event;
import net.minecraft.client.gui.GuiScreen;

public class GuiSwitchEvent extends Event {

    @Getter public GuiScreen guiScreen;

    /**
     * @param guiScreen the GuiScreen.
     */
    public GuiSwitchEvent(GuiScreen guiScreen)
    {
        this.guiScreen = guiScreen;
    }
}

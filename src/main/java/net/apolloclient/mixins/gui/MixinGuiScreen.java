package net.apolloclient.mixins.gui;

import net.apolloclient.events.impl.client.GuiSwitchEvent;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/** Mixin events for GuiScreen.class
 * @author MatthewTGM | MatthewTGM#4058
 * @since 1.0.0 **/
@Mixin(GuiScreen.class)
public class MixinGuiScreen {

    @Shadow protected Minecraft mc;

    /** Post {@link GuiSwitchEvent} when gui screen changes.
    * @param mouseX the x position of the mouse
    * @param mouseY the y position of the mouse
    * @param partialTicks TODO
    * @param callbackInfo unused **/
    @Inject(method = "drawScreen", at = @At("RETURN"))
    public void drawScreen(int mouseX, int mouseY, float partialTicks, CallbackInfo callbackInfo) { new GuiSwitchEvent(this.mc.currentScreen).post(); }
}

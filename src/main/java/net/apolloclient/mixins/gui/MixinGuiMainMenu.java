package net.apolloclient.mixins.gui;

import net.apolloclient.Apollo;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiMainMenu;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.resources.I18n;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * Custom gui main menu - temporary.
 *
 * @author MatthewTGM | MatthewTGM#4058
 * @since 1.0.0
 */
@Mixin(GuiMainMenu.class)
public class MixinGuiMainMenu extends GuiScreen {

  @Overwrite
  public void addSingleplayerMultiplayerButtons(int p_73969_1_, int p_73969_2_) {
    this.buttonList.add(
        new GuiButton(
            1, this.width / 2 - 100, p_73969_1_, I18n.format("menu.singleplayer", new Object[0])));
    this.buttonList.add(
        new GuiButton(
            2,
            this.width / 2 - 100,
            p_73969_1_ + p_73969_2_ * 1,
            I18n.format("menu.multiplayer", new Object[0])));
  }

  @Inject(method = "drawScreen", at = @At("RETURN"))
  public void drawScren(int mouseX, int mouseY, float partialTicks, CallbackInfo callbackInfo) {
    this.drawString(fontRendererObj, Apollo.NAME + " " + Apollo.VERSION, 2, this.height - 20, -1);
  }
}

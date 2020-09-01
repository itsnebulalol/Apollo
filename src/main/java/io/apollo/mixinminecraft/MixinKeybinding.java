/*
 * ****************************************************************
 *  Copyright (C) 2020-2021 developed by Icovid
 *
 *  MixinKeybinding.java is part of Apollo Client. [8/29/20, 11:10 AM]
 *
 *  MixinKeybinding.java can not be copied and/or distributed without the express
 *  permission of Icovid
 *
 *  Contact: Icovid#3888
 * ****************************************************************
 */

package io.apollo.mixinminecraft;

import io.apollo.Apollo;
import io.apollo.guiscreen.TestGui;
import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.KeyBinding;
import org.lwjgl.input.Keyboard;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/** MixinBootstrap Events for Keybinding.class.
 * @author Icovid | Icovid#3888
 * @since 1.0.0 **/
@Mixin(KeyBinding.class) public class MixinKeybinding {

    /** Called on key press.
     * @param key key code on key pressed **/
    @Inject(method = "onTick", at = @At("HEAD"))
    private static void keyHook(int key, CallbackInfo callbackInfo) {
        if (key == Keyboard.KEY_RSHIFT) {
            Minecraft.getMinecraft().displayGuiScreen(new TestGui());
            Apollo.log("Started Test GUI");
        }
    }
}

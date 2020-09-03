/*⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤
 Copyright (C) 2020-2021 developed by Icovid and Apollo Development Team

 MixinKeybinding.java is part of Apollo Client. 9/3/20, 12:06 AM

 MixinKeybinding.java can not be copied and/or distributed without the express
 permission of Icovid

 Contact: Icovid#3888 @ https://discord.com
 ⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤*/

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
     * @param key key code of key pressed **/
    @Inject(method = "onTick", at = @At("HEAD"))
    private static void keyHook(int key, CallbackInfo callbackInfo) {

    }
}

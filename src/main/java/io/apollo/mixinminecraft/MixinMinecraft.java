/*
 * ****************************************************************
 *  Copyright (C) 2020-2021 developed by Icovid
 *
 *  MixinMinecraft.java is part of Apollo Client. [8/28/20, 2:36 PM]
 *
 *  MixinMinecraft.java can not be copied and/or distributed without the express
 *  permission of Icovid
 *
 *  Contact: Icovid#3888
 * ****************************************************************
 */

package io.apollo.mixinminecraft;

import io.apollo.Apollo;
import net.minecraft.client.Minecraft;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/** MixinBootstrap Events for Minecraft.class.
 * @author isXander | isXander#4285
 * @since 1.0.0 **/
@Mixin(Minecraft.class) public class MixinMinecraft {

    // Called on game start
    @Inject(method = "startGame", at = @At("RETURN"))
    private void onGameStart(CallbackInfo info) { Apollo.instance.postInitialisation(); }
}

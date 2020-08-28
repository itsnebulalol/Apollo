/*
 * ****************************************************************
 *  Copyright (C) 2020-2021 developed by Icovid
 *  Class Authors : Nora [Nora#0001]
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

@Mixin(Minecraft.class)
/* MixinBootstrap Events for Minecraft.class */
public class MixinMinecraft {

    // Called on Game Start - Used to Start Apollo
    @Inject(method = "startGame", at = @At("RETURN"))
    private void onGameStart(CallbackInfo info) { Apollo.instance.postInitialisation(); }
}

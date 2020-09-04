/*⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤
 Copyright (C) 2020-2021 developed by Icovid and Apollo Development Team

 MixinMinecraft.java is part of Apollo Client. 9/3/20, 8:54 PM
 
 MixinMinecraft.java can not be copied and/or distributed without the express
 permission of Icovid
 
 Contact: Icovid#3888 @ https://discord.com
 ⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤*/

package io.apollo.mixinminecraft;

import io.apollo.Apollo;
import io.apollo.events.impl.GameLoopEvent;
import net.minecraft.client.Minecraft;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/** MixinBootstrap Events for Minecraft.class.
 * @author Icovid | Icovid#3888
 * @since 1.0.0 **/
@Mixin(Minecraft.class) public class MixinMinecraft {

    /** Post {@link Apollo} start.
     * @param callbackInfo unused **/
    @Inject(method = "startGame", at = @At("RETURN"))
    private void onGameStart(CallbackInfo callbackInfo) { Apollo.INSTANCE.postInitialisation(); }

    /** Post {@link GameLoopEvent} every tick.
     * @param callbackInfo unused
     * @author Nora Cos | #Nora#0001 */
    @Inject(method = "runGameLoop", at = @At(value = "FIELD", target = "Lnet/minecraft/client/Minecraft;skipRenderWorld:Z", shift = At.Shift.AFTER))
    private void runGameLoop(CallbackInfo callbackInfo) {
        new GameLoopEvent().post();
    }
}

/*⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤
 Copyright (C) 2020-2021 developed by Icovid and Apollo Development Team

 MixinMinecraft.java is part of Apollo Client. 9/4/20, 2:07 PM
 
 MixinMinecraft.java can not be copied and/or distributed without the express
 permission of Icovid
 
 Contact: Icovid#3888 @ https://discord.com
 ⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤*/

package io.apollo.mixins.client;

import io.apollo.Apollo;
import io.apollo.events.impl.client.GameLoopEvent;
import io.apollo.events.impl.client.input.KeyPressedEvent;
import io.apollo.events.impl.client.input.KeyReleasedEvent;
import io.apollo.events.impl.client.input.LeftClickEvent;
import io.apollo.events.impl.client.input.RightClickEvent;
import io.apollo.events.impl.world.LoadWorldEvent;
import io.apollo.events.impl.world.SinglePlayerJoinEvent;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.world.WorldSettings;
import org.lwjgl.input.Keyboard;
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

    /** Post {@link Apollo} shutdown.
     * @param callbackInfo unused **/
    @Inject(method = "shutdown", at = @At("HEAD"))
    private void shutdown(CallbackInfo callbackInfo) {
        Apollo.INSTANCE.shutdown();
    }

    /** Post {@link GameLoopEvent} every tick.
     * @param callbackInfo unused
     * @author Nora Cos | #Nora#0001 */
    @Inject(method = "runGameLoop", at = @At(value = "FIELD", target = "Lnet/minecraft/client/Minecraft;skipRenderWorld:Z", shift = At.Shift.AFTER))
    private void runGameLoop(CallbackInfo callbackInfo) {
        new GameLoopEvent().post();
    }

    /** Post {@link KeyPressedEvent} or {@link KeyReleasedEvent} at key press.
     * @param callbackInfo unused **/
    @Inject(method = "dispatchKeypresses", at = @At(value = "INVOKE_ASSIGN", target = "Lorg/lwjgl/input/Keyboard;getEventKeyState()Z", remap = false))
    private void runTickKeyboard(CallbackInfo callbackInfo) {
        Apollo.EVENT_BUS.post(Keyboard.getEventKeyState() ?
                new KeyPressedEvent(Keyboard.isRepeatEvent(), Keyboard.getEventKey())
                : new KeyReleasedEvent(Keyboard.isRepeatEvent(), Keyboard.getEventKey()));
    }

    /** Post {@link LeftClickEvent} at left mouse click.
     * @param callbackInfo unused **/
    @Inject(method = "clickMouse", at = @At("RETURN"))
    private void leftClickMouse(CallbackInfo callbackInfo) { new LeftClickEvent().post(); }

    /** Post {@link RightClickEvent} at right mouse click.
     * @param callbackInfo unused **/
    @Inject(method = "rightClickMouse", at = @At("RETURN"))
    private void rightClickMouse(CallbackInfo callbackInfo) { new RightClickEvent().post(); }

    /** Post {@link SinglePlayerJoinEvent} when joining single player world.
     * @param folderName name of folder world file is located in.
     * @param worldName name of world
     * @param worldSettingsIn settings of world
     * @param callbackInfo unused **/
    @Inject(method = "launchIntegratedServer", at = @At("HEAD"))
    private void joinSinglePlayer(String folderName, String worldName, WorldSettings worldSettingsIn, CallbackInfo callbackInfo) {
        new SinglePlayerJoinEvent(folderName, worldName, worldSettingsIn).post();
    }

    /** Post {@link LoadWorldEvent} when new world is loaded for player.
     * @param worldClient world client used.
     * @param callbackInfo unused **/
    @Inject(method = "loadWorld(Lnet/minecraft/client/multiplayer/WorldClient;)V", at = @At("HEAD"))
    private void loadWorld(WorldClient worldClient, CallbackInfo callbackInfo) {
        new LoadWorldEvent(worldClient).post();
    }


}

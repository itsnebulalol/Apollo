/*⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤
 Copyright (C) 2020-2021 developed by Icovid and Apollo Development Team

 MixinEntityPlayerSP.java is part of Apollo Client. 9/3/20, 8:54 PM

 MixinEntityPlayerSP.java can not be copied and/or distributed without the express
 permission of Icovid

 Contact: Icovid#3888 @ https://discord.com
 ⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤*/

package io.apollo.mixins.entity;

import io.apollo.events.impl.client.PlayerChatEvent;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.network.NetHandlerPlayClient;
import net.minecraft.network.play.client.C01PacketChatMessage;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

/** MixinBootstrap Events for EntityPlayerSP.class.
 * @author Nora Cos | Nora#0001
 * @since 1.0.0 **/
@Mixin(EntityPlayerSP.class)
public class MixinEntityPlayerSP {

    @Shadow @Final public NetHandlerPlayClient sendQueue;

    /** Posts a {@link PlayerChatEvent}.
     * If the event is canceled, prevent the client from sending any message.
     * @param  message message player sent **/
    @Overwrite public void sendChatMessage(String message) {
        if (message != null) {
            PlayerChatEvent event = new PlayerChatEvent(message);
            if (!event.isCanceled()) {
                this.sendQueue.addToSendQueue(new C01PacketChatMessage(event.getMessage()));
            }
        }
    }
}

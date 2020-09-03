/*
 * ⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤
 * Copyright (C) 2020-2021 developed by Icovid and Apollo Development Team
 *
 * MixinEntityPlayerSP.java is part of Apollo Client. 2020-09-03, 6:10 p.m.
 *
 * MixinEntityPlayerSP.java can not be copied and/or distributed without the express
 * permission of Icovid
 *
 * Contact: Icovid#3888 @ https://discord.com
 * ⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤
 */

package io.apollo.mixinminecraft.entity;

import io.apollo.events.impl.chat.PlayerChatEvent;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.network.NetHandlerPlayClient;
import net.minecraft.network.play.client.C01PacketChatMessage;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(EntityPlayerSP.class)
public class MixinEntityPlayerSP {
    @Shadow @Final public NetHandlerPlayClient sendQueue;

    /**
     * Posts a {@link io.apollo.events.impl.chat.PlayerChatEvent}.
     * If the event is canceled, prevent the client from sending any message.
     *
     * @author Nora Cos | Nora#0001
     */
    @Overwrite
    public void sendChatMessage(String message) {
        if (message != null) {
            PlayerChatEvent event = new PlayerChatEvent(message);
            if (!event.isCanceled()) {
                this.sendQueue.addToSendQueue(new C01PacketChatMessage(event.getMessage()));
            }
        }
    }
}

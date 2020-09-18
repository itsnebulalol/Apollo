package net.apolloclient.mixins.network;

import net.apolloclient.events.impl.client.ClientChatReceivedEvent;
import net.minecraft.client.network.NetHandlerPlayClient;
import net.minecraft.network.play.server.S02PacketChat;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import net.apolloclient.events.impl.client.ChatReceivedEvent;

/** MixinBootstrap Events for MixinNetHandlerPlayClient.class.
 * @author Nora Cos | Nora#0001
 * @since 1.0.0 **/
@Mixin(NetHandlerPlayClient.class)
public class MixinNetHandlerPlayClient {

    /** {@link EventChatReceived} at chat packet.
     * @param callbackInfo unused
     * @param packetIn chat packet received **/
    @Inject(method = "handleChat", at = @At(value = "INVOKE", target = "Lnet/minecraft/network/PacketThreadUtil;checkThreadAndEnqueue(Lnet/minecraft/network/Packet;Lnet/minecraft/network/INetHandler;Lnet/minecraft/util/IThreadListener;)V", shift = At.Shift.AFTER), cancellable = true)
    private void onChatPacket(S02PacketChat packetIn, CallbackInfo callbackInfo) {
        EventChatReceived event = new EventChatReceived(packetIn.getChatComponent());
        event.post();
        if (event.isCanceled()) {
            callbackInfo.cancel();
        }
    }

    /** {@link EventClientChatReceived} at handleChat.
     * @author MatthewTGM | MatthewTGM#4058
     * @param callbackInfo unused
     * @param packetIn chat packet received. **/
    @Inject(method = "handleChat", at = @At("HEAD"), cancellable = false)
    private void handleChat(S02PacketChat packetIn, CallbackInfo callbackInfo) {
        EventClientChatReceived event = new EventClientChatReceived(packetIn.getType(), packetIn.getChatComponent());
        event.post();
    }
}
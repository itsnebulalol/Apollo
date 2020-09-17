package io.apollo.mixins.entity;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import org.spongepowered.asm.mixin.Mixin;

import io.apollo.events.impl.player.EventAttackEntity;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/** Mixin Event
 * @author MatthewTGM | MatthewTGM#4058
 */
@Mixin(EntityPlayer.class)
public class MixinEntityPlayer {

    /** posts a {@link EventAttackEntity}. **/
    @Inject(method = "attackTargetEntityWithCurrentItem", at = @At("RETURN")) public void attackTargetEntityWithCurrentItem(Entity targetEntity, CallbackInfo ci) {
        if(targetEntity != null) {
            EventAttackEntity event = new EventAttackEntity(Minecraft.getMinecraft().thePlayer, targetEntity);
        }
    }
}

package net.apolloclient.mixins.entity;

import net.apolloclient.events.impl.player.AttackEntityEvent;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * Event target ejections for EntityPlayer.class.
 *
 * @author MatthewTGM | MatthewTGM#4058
 * @since 1.0.0 *
 */
@Mixin(EntityPlayer.class)
public class MixinEntityPlayer {

  /**
   * posts a {@link AttackEntityEvent} when entity hits another entity.
   *
   * @param targetEntity entity hit
   * @param callbackInfo unused
   */
  @Inject(method = "attackTargetEntityWithCurrentItem", at = @At("RETURN"))
  public void attackTargetEntityWithCurrentItem(Entity targetEntity, CallbackInfo callbackInfo) {
    if (targetEntity != null) {
      new AttackEntityEvent(targetEntity).post();
    }
  }
}

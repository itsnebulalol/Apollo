package net.apolloclient.mixins.renderer;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.entity.Entity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * Event target ejections for Render.class TODO: IMPLEMENT THIS IN MODULE / COSMETIC GUI
 *
 * @author MatthewTGM | MatthewTGM#4058
 * @since 1.0.0
 */
@Mixin(Render.class)
public abstract class MixinRender<T extends Entity> {

  @Shadow
  protected abstract void renderLivingLabel(
      T entityIn, String str, double x, double y, double z, int maxDistance);

  /**
   * @param entity the entity which is being called
   * @param x TODO
   * @param y TODO
   * @param z TODO
   * @param entityYaw the yaw of the entity
   * @param partialTicks TODO
   * @param callbackInfo unused
   */
  @Inject(method = "doRender", at = @At("HEAD"))
  public void doRender(
      T entity,
      double x,
      double y,
      double z,
      float entityYaw,
      float partialTicks,
      CallbackInfo callbackInfo) {
    if (entity instanceof EntityPlayerSP) {
      this.renderLivingLabel(
          entity,
          Minecraft.getMinecraft().thePlayer.getDisplayName().getFormattedText(),
          x,
          y,
          z,
          64);
    }
  }
}

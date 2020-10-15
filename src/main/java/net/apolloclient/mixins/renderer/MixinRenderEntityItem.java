package net.apolloclient.mixins.renderer;

import net.apolloclient.Apollo;
import net.apolloclient.module.impl.render.ItemPhysics;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
import net.minecraft.client.renderer.entity.RenderEntityItem;
import net.minecraft.client.resources.model.IBakedModel;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MathHelper;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

/**
 * Event target ejections for RenderEntityItem.class.
 *
 * @author Icovid | Icovid#3888
 * @since b0.2
 */
@Mixin(RenderEntityItem.class)
public abstract class MixinRenderEntityItem {

    /**
     * Overwrites item animations for {@link net.apolloclient.module.impl.render.ItemPhysics}
     *
     * @author Icovid | Icovid#3888
     */
    @Overwrite
    private int func_177077_a(EntityItem p_177077_1_, double p_177077_2_, double p_177077_4_, double p_177077_6_, float p_177077_8_, IBakedModel p_177077_9_) {
        ItemStack lvt_10_1_ = p_177077_1_.getEntityItem();
        Item lvt_11_1_ = lvt_10_1_.getItem();
        if (lvt_11_1_ == null) return 0;

        if (Apollo.MODULE_FACTORY.getModContainerByName(ItemPhysics.NAME).isEnabled()) {
            int i = this.func_177078_a(lvt_10_1_);

            float f1 = -0.125f;
            if(!p_177077_9_.isGui3d()) f1 = -0.175f;

            float f2 = p_177077_9_.getItemCameraTransforms().getTransform(ItemCameraTransforms.TransformType.GROUND).scale.y;
            GlStateManager.translate((float)p_177077_2_, (float)p_177077_4_ + f1 + 0.25F * f2, (float)p_177077_6_);

            if (!p_177077_9_.isGui3d())
            {
                if(p_177077_1_.onGround) GlStateManager.rotate(180, 0.0f, 1.0f, 1.0f);
            }
            float speed = ItemPhysics.instance.speed;

            if(!p_177077_1_.onGround) {
                float rotAmount = ((float)p_177077_1_.getAge() * speed) % 360;
                GlStateManager.rotate(rotAmount, 1f, 0f, 1f);
            }

            GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
            return i;

        } else {

            int lvt_13_1_ = this.func_177078_a(lvt_10_1_);
            float lvt_15_1_ = MathHelper.sin(((float)p_177077_1_.getAge() + p_177077_8_) / 10.0F + p_177077_1_.hoverStart) * 0.1F + 0.1F;
            float lvt_16_1_ = p_177077_9_.getItemCameraTransforms().getTransform(ItemCameraTransforms.TransformType.GROUND).scale.y;
            GlStateManager.translate((float)p_177077_2_, (float)p_177077_4_ + lvt_15_1_ + 0.25F * lvt_16_1_, (float)p_177077_6_);
            float lvt_17_2_;

            if (p_177077_9_.isGui3d() || Minecraft.getMinecraft().getRenderManager().options != null) {
                lvt_17_2_ = (((float)p_177077_1_.getAge() + p_177077_8_) / 20.0F + p_177077_1_.hoverStart) * 57.295776F;
                GlStateManager.rotate(lvt_17_2_, 0.0F, 1.0F, 0.0F);
            }

            if (!p_177077_9_.isGui3d()) {
                lvt_17_2_ = -0.0F * (float)(lvt_13_1_ - 1) * 0.5F;
                float lvt_18_1_ = -0.0F * (float)(lvt_13_1_ - 1) * 0.5F;
                float lvt_19_1_ = -0.046875F * (float)(lvt_13_1_ - 1) * 0.5F;
                GlStateManager.translate(lvt_17_2_, lvt_18_1_, lvt_19_1_);
            }

            GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
            return lvt_13_1_;

        }
    }

    @Shadow protected abstract int func_177078_a(ItemStack p_177078_1_);
}

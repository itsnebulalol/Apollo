package net.apolloclient.module.impl.render;

import net.apolloclient.module.bus.Instance;
import net.apolloclient.module.bus.Module;
import net.minecraft.client.renderer.entity.RenderEntityItem;
import org.spongepowered.asm.mixin.Mixin;

/**
 * Cool item animations.
 *
 * <p>Make falling items and items on ground look
 * more realistic</p>
 *
 * @see net.apolloclient.mixins.renderer.MixinRenderEntityItem
 * @author Icovid | Icovid#3888
 * @since b0.2
 */
@Module(name = ItemPhysics.NAME, description = ItemPhysics.DESCRIPTION, author = ItemPhysics.AUTHOR, enabled = true)
public class ItemPhysics {

    public static final String NAME        = "ItemPhysics";
    public static final String DESCRIPTION = "cool item animations";
    public static final String AUTHOR      = "Icovid";

    @Instance public static final ItemPhysics instance = new ItemPhysics();

    // TODO: Setting System impl.
    public float speed = 12;


}

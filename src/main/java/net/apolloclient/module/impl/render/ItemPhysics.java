package net.apolloclient.module.impl.render;

import net.apolloclient.module.bus.Module;
import net.apolloclient.module.bus.Instance;

/**
 * Cool item animations.
 *
 * <p>Make falling items and items on ground look
 * more realistic</p>
 *
 * @author Icovid | Icovid#3888
 * @since b0.2
 */
@Module(name = ItemPhysics.NAME, description = ItemPhysics.DESCRIPTION, author = ItemPhysics.AUTHOR)
public class ItemPhysics {

    public static final String NAME        = "ItemPhysics";
    public static final String DESCRIPTION = "cool item animations";
    public static final String AUTHOR      = "Icovid";

    @Instance public static final ItemPhysics instance = new ItemPhysics();
}

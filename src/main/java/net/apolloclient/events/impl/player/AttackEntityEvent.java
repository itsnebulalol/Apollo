package net.apolloclient.events.impl.player;

import lombok.Getter;
import net.apolloclient.events.Event;
import net.minecraft.entity.Entity;

/** Fired when player hits entity.
 * @author MatthewTGM | MatthewTGM#4058
 * @since 1.0.0 **/
public class AttackEntityEvent extends Event {

    @Getter private final Entity target;

    /** @param target entity hit. */
    public AttackEntityEvent(Entity target) {
        this.target = target;
    }
}

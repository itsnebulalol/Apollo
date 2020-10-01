package net.apolloclient.event.impl.player;

import net.apolloclient.event.Event;
import net.minecraft.entity.Entity;

/**
 * Fired when player attacks an entity.
 *
 * @author MatthewTGM | MatthewTGM#4058
 * @since 1.0.0
 */
public class AttackEntityEvent extends Event {

  public final Entity target;

  /** @param target entity hit */
  public AttackEntityEvent(Entity target) {
    this.target = target;
  }
}

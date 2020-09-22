package net.apolloclient.events.impl.player;

import lombok.Getter;
import net.apolloclient.events.Event;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;

/** Mixin event for attacking entities.
* @author MatthewTGM | MatthewTGM#4058
*/
public class AttackEntityEvent extends Event
{
    @Getter private final Entity target;

    public AttackEntityEvent(Entity target)
    {
        this.target = target;
    }
}
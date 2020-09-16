package ga.matthewtgm.events.impl.player;

import ga.matthewtgm.events.CancellableEvent;
import ga.matthewtgm.events.Event;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;

public class EventAttackEntity extends Event
{
    public final Entity target;
    public EventAttackEntity(EntityPlayer player, Entity target)
    {
        this.target = target;
    }
}
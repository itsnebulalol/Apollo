package net.apolloclient.events.impl.player;

import net.apolloclient.events.Event;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;

/** Mixin event for attacking entities.
* @author MatthewTGM | MatthewTGM#4058
*/
public class AttackEntityEvent extends Event
{
    public final Entity target;
    public AttackEntityEvent(EntityPlayer player, Entity target)
    {
        this.target = target;
    }
}
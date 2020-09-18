package net.apolloclient.events.impl.client;

import net.apolloclient.events.Event;
import net.minecraft.util.IChatComponent;

/** Apollo Event for client chat.
 * @author MatthewTGM | MatthewTGM#4058 **/
public class ClientChatReceivedEvent extends Event
{
    public IChatComponent message;
    /** @param type unused
     * @param message chat component being received.
     */
    public final byte type;
    public ClientChatReceivedEvent(byte type, IChatComponent message)
    {
        this.type = type;
        this.message = message;
    }
}
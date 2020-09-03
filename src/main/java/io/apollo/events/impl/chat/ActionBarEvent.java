/*
 * ⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤
 * Copyright (C) 2020-2021 developed by Icovid and Apollo Development Team
 *
 * ActionBarEvent.java is part of Apollo Client. 2020-09-03, 3:05 p.m.
 *
 * ActionBarEvent.java can not be copied and/or distributed without the express
 * permission of Icovid
 *
 * Contact: Icovid#3888 @ https://discord.com
 * ⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤
 */

package io.apollo.events.impl.chat;

import net.minecraft.util.IChatComponent;

/**
 * Fired when the Action Bar is updated.
 *
 * @author Nora Cos | Nora#0001
 */
public class ActionBarEvent extends ChatEvent {
    /**
     * @param chatComponent action bar text
     */
    public ActionBarEvent(IChatComponent chatComponent) {
        super(chatComponent);
    }
}

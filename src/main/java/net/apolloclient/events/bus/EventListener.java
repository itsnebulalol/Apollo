/*⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤
Copyright (C) 2020-2021 developed by Icovid and Apollo Development Team

This program is free software: you can redistribute it and/or modify
it under the terms of the GNU Affero General Public License as published
by the Free Software Foundation, either version 3 of the License, or
(at your option) any later version.

This program is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU Affero General Public License for more details.

You should have received a copy of the GNU Affero General Public License
along with this program.  If not, see https://www.gnu.org/licenses/.

Contact: Icovid#3888 @ https://discord.com
⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤*/

package net.apolloclient.events.bus;

import java.lang.reflect.Method;
import lombok.Getter;
import lombok.Setter;
import net.apolloclient.Apollo;
import net.apolloclient.events.Event;

/**
 * Internal data class for storing event listener data.
 *
 * @author Nora Cos | Nora#0001
 * @since 1.0.0 *
 */
public class EventListener {

  @Getter private final Object instance;
  @Getter @Setter private Method method;
  @Getter private Priority eventPriority;

  public EventListener(Object listener, Method method, Priority eventPriority) {
    instance = listener;
    this.method = method;
    this.eventPriority = eventPriority;
  }

  /**
   * Sets the event priority and sorts the event listeners.
   *
   * @param eventPriority new event priority
   * @param classToSort event class *
   */
  void setEventPriority(Priority eventPriority, Class<? extends Event> classToSort) {
    this.eventPriority = eventPriority;
    if (classToSort != null) {
      Apollo.EVENT_BUS.sort(classToSort);
    }
  }
}

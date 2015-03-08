package de.knightsoftnet.validationexample.client.event;

import com.google.gwt.event.shared.EventHandler;

/**
 * event handler for user changes.
 *
 * @author Manfred Tremmel
 *
 */
public interface ChangeUserEventHandler extends EventHandler {
  /**
   * event when user changes.
   *
   * @param pEvent change user event
   */
  void onChangeUser(ChangeUserEvent pevent);
}

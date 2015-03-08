package de.knightsoftnet.validationexample.client.event;

import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.event.shared.HasHandlers;

/**
 * has handler Interface for user changes, has to be implemented by the fire event methods.
 *
 * @author Manfred Tremmel
 *
 */
public interface HasChangeUserEventHandlers extends HasHandlers {
  /**
   * add has change user event handler.
   *
   * @param pHandler handler to add
   * @return handler registration
   */
  HandlerRegistration addHasChangeUserEventHandler(ChangeUserEventHandler phandler);
}

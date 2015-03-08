package de.knightsoftnet.validationexample.client.ui.about;

import com.google.gwt.user.client.ui.IsWidget;

/**
 * View of the about page, interface.
 *
 * @author Manfred Tremmel
 *
 */
public interface AboutViewInterface extends IsWidget {
  /**
   * set a reference to the presenter/activity.
   *
   * @param paboutPresenterInterface reference to set
   */
  void setPresenter(AboutPresenterInterface paboutPresenterInterface);
}

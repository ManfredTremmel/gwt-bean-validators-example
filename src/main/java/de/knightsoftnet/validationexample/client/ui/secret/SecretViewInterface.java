package de.knightsoftnet.validationexample.client.ui.secret;

import com.google.gwt.user.client.ui.IsWidget;

/**
 * View of the secret page, interface.
 *
 * @author Manfred Tremmel
 *
 */
public interface SecretViewInterface extends IsWidget {
  /**
   * set a reference to the presenter/activity.
   *
   * @param psecretPresenter reference to set
   */
  void setPresenter(SecretPresenterInterface psecretPresenter);
}

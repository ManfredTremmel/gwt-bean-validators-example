package de.knightsoftnet.validationexample.client.ui.login;

import de.knightsoftnet.validationexample.client.basepage.BasePresenterInterface;

/**
 * Activity/Presenter of the login, interface.
 *
 * @author Manfred Tremmel
 *
 */
public interface LoginPresenterInterface extends BasePresenterInterface {
  /**
   * try to login.
   */
  void tryToLogin();
}

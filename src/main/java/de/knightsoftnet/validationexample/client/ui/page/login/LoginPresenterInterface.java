package de.knightsoftnet.validationexample.client.ui.page.login;

import de.knightsoftnet.validationexample.client.ui.basepage.BasePresenterInterface;

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

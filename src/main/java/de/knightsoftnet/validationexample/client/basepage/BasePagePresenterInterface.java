package de.knightsoftnet.validationexample.client.basepage;

import de.knightsoftnet.validationexample.client.ui.about.AboutPresenterInterface;

/**
 * Activity/Presenter of the main page, interface.
 *
 * @author Manfred Tremmel
 *
 */
public interface BasePagePresenterInterface extends AboutPresenterInterface {
  /**
   * show info dialog.
   */
  void showInfo();

  /**
   * hide info dialog.
   */
  void hideInfo();
}

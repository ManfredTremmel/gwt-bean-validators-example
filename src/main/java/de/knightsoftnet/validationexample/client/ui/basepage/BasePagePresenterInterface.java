package de.knightsoftnet.validationexample.client.ui.basepage;

import de.knightsoftnet.validationexample.client.ui.page.about.AboutPresenterInterface;

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

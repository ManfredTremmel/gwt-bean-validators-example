package de.knightsoftnet.validationexample.client.ui.page.settings;

import de.knightsoftnet.validationexample.client.ui.basepage.BasePresenterInterface;

import com.google.gwt.user.client.ui.ListBox;

/**
 * Activity/Presenter of the settings interface.
 *
 * @author Manfred Tremmel
 *
 */
public interface SettingsPresenterInterface extends BasePresenterInterface {

  /**
   * fill a list box with available language entries.
   *
   * @param plistBox the list box to fill
   */
  void fillLanguages(ListBox plistBox);

  /**
   * change the language of the ui.
   *
   * @param planguage language to switch to.
   */
  void changeLanguage(String planguage);
}

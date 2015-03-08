package de.knightsoftnet.validationexample.client.navigation;

import com.google.gwt.i18n.client.Constants;

/**
 * Localized values for the settings ui.
 *
 * @author Manfred Tremmel
 *
 */
public interface NavigationConstants extends Constants {
  /**
   * text of the navigation button.
   *
   * @return button
   */
  String button();

  /**
   * text of the header line.
   *
   * @return header
   */
  String header();

  /**
   * error message displayed when loading data failes.
   *
   * @return errorLoading
   */
  String errorLoading();

  /**
   * text of the test menu entry.
   *
   * @return test
   */
  String menuTest();

  /**
   * text of the settings menu entry.
   *
   * @return settings
   */
  String menuSettings();

  /**
   * text of the login menu entry.
   *
   * @return login
   */
  String menuLogin();

  /**
   * text of the logout menu entry.
   *
   * @return logout
   */
  String menuLogout();

  /**
   * text of the test folder menu entry.
   *
   * @return testFolder
   */
  String menuTestFolder();

  /**
   * text of the secret menu entry.
   *
   * @return secret
   */
  String menuSecret();

  /**
   * text of the sepa menu entry.
   *
   * @return sepa
   */
  String menuSepa();

  /**
   * text of the postal address menu entry.
   * 
   * @return postalAddress
   */
  String menuPostalAddress();
}

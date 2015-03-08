package de.knightsoftnet.validationexample.client.navigation;

import de.knightsoftnet.validationexample.shared.models.UserData;

import com.google.gwt.safehtml.shared.SafeHtml;

/**
 * The <code>NavigationEntryInterface</code> defines one menu entry or folders with subentries as
 * interface.
 *
 * @author Manfred Tremmel
 */
public interface NavigationEntryInterface {

  /**
   * get menu value.
   *
   * @return the menuValue
   */
  SafeHtml getMenuValue();

  /**
   * get token.
   *
   * @return the token
   */
  String getToken();

  /**
   * get full token.
   *
   * @return the token with static and dynamic part
   */
  String getFullToken();

  /**
   * get token dynamic.
   *
   * @return the tokenDynamic
   */
  String getTokenDynamic();

  /**
   * set token dynamic.
   *
   * @param pTokenDynamic the tokenDynamic to set
   */
  void setTokenDynamic(String ptokenDynamic);

  /**
   * get parent entry.
   *
   * @return the parentEntry
   */
  NavigationEntryInterface getParentEntry();

  /**
   * add a parent entry.
   *
   * @param pparentEntry entry to add
   */
  void setParentEntry(NavigationEntryInterface pparentEntry);

  /**
   * is entry open on startup.
   *
   * @return the openOnStartup
   */
  boolean isOpenOnStartup();

  /**
   * check if this entry should be displayed.
   *
   * @param puser the user to test for
   * @return true if it should be displayed
   */
  boolean isDisplayable(UserData puser);

}

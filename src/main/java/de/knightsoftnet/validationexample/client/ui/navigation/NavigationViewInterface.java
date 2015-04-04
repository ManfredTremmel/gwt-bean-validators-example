package de.knightsoftnet.validationexample.client.ui.navigation;

import com.google.gwt.user.client.ui.IsWidget;

/**
 * View of the validator Navigation, Interface.
 *
 * @author Manfred Tremmel
 *
 */
public interface NavigationViewInterface extends IsWidget {
  /**
   * set a reference to the presenter/activity.
   *
   * @param pnavigationPresenterInterface reference to set
   */
  void setPresenter(NavigationPresenterInterface pnavigationPresenterInterface);

  /**
   * create navigation.
   *
   * @param pplace the place with the navigation data
   */
  void createNavigation(final NavigationPlace pplace);
}

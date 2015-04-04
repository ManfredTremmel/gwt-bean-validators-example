package de.knightsoftnet.validationexample.client.ui.basepage;

import com.google.gwt.user.client.ui.HasOneWidget;
import com.google.gwt.user.client.ui.IsWidget;

/**
 * View of the BasePage, Interface.
 *
 * @author Manfred Tremmel
 *
 */
public interface BasePageViewInterface extends IsWidget {
  /**
   * container where to place content.
   *
   * @return content container
   */
  HasOneWidget getContentContainer();

  /**
   * container where to place navigation.
   *
   * @return navigation container
   */
  HasOneWidget getNavigationContainer();

  /**
   * set a reference to the presenter/activity.
   *
   * @param pbasePagePresenterInterface reference to set
   */
  void setPresenter(BasePagePresenterInterface pbasePagePresenterInterface);
}

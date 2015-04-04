package de.knightsoftnet.validationexample.client.ui.navigation;

import de.knightsoftnet.validationexample.client.ClientFactoryInterface;

import com.google.gwt.activity.shared.Activity;
import com.google.web.bindery.event.shared.SimpleEventBus;

/**
 * Activity/Presenter of the base page interface.
 *
 * @author Manfred Tremmel
 *
 */
public interface NavigationPresenterInterface extends Activity {
  /**
   * go to another place by given navigation entry.
   *
   * @param pnavigationEntry the navigation entry which represents the place to go to
   */
  void goToNavigationEntry(final NavigationEntryInterface pnavigationEntry);

  /**
   * get the client factory.
   *
   * @return ClientFactoryInterface
   */
  ClientFactoryInterface getClientFactory();

  /**
   * get event bus.
   *
   * @return event bus
   */
  SimpleEventBus getEventBus();

  /**
   * get the current place.
   *
   * @return place data
   */
  NavigationPlace getPlace();
}

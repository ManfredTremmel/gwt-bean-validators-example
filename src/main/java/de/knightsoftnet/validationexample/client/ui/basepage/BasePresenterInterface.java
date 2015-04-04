package de.knightsoftnet.validationexample.client.ui.basepage;

import com.google.gwt.activity.shared.Activity;
import com.google.web.bindery.event.shared.SimpleEventBus;

/**
 * Activity/Presenter basic functions interface.
 *
 * @author Manfred Tremmel
 *
 */
public interface BasePresenterInterface extends Activity {

  /**
   * get event bus.
   *
   * @return event bus
   */
  SimpleEventBus getEventBus();

  /**
   * view the about info.
   */
  void viewAbout();

  /**
   * display navigation instead.
   */
  void showNavigation();
}

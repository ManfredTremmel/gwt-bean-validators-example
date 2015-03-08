package de.knightsoftnet.validationexample.client;

import de.knightsoftnet.validationexample.client.basepage.BasePageViewInterface;
import de.knightsoftnet.validationexample.client.navigation.AbstractNavigationPlace;
import de.knightsoftnet.validationexample.client.navigation.AppPlaceHistoryMapper;
import de.knightsoftnet.validationexample.client.navigation.NavigationViewInterface;
import de.knightsoftnet.validationexample.client.ui.about.AboutViewInterface;
import de.knightsoftnet.validationexample.client.ui.address.AddressRemoteServiceAsync;
import de.knightsoftnet.validationexample.client.ui.address.AddressViewInterface;
import de.knightsoftnet.validationexample.client.ui.login.LoginLogoutRemoteServiceAsync;
import de.knightsoftnet.validationexample.client.ui.login.LoginViewInterface;
import de.knightsoftnet.validationexample.client.ui.secret.SecretViewInterface;
import de.knightsoftnet.validationexample.client.ui.sepa.SepaRemoteServiceAsync;
import de.knightsoftnet.validationexample.client.ui.sepa.SepaViewInterface;
import de.knightsoftnet.validationexample.client.ui.settings.SettingsViewInterface;
import de.knightsoftnet.validationexample.shared.models.UserData;

import com.google.gwt.place.shared.PlaceController;
import com.google.web.bindery.event.shared.SimpleEventBus;

/**
 * Client factory interface.
 *
 * @author Manfred Tremmel
 *
 */
public interface ClientFactoryInterface {

  /**
   * get event bus.
   *
   * @return event bus
   */
  SimpleEventBus getEventBus();

  /**
   * get the place controller.
   *
   * @return place controller
   */
  PlaceController getPlaceController();

  /**
   * get the place history mapper.
   *
   * @return place history mapper
   */
  AppPlaceHistoryMapper getPlaceHistoryMapper();

  /**
   * get the navigation place.
   *
   * @return navigation place
   */
  AbstractNavigationPlace getNavigationPlace();

  /**
   * get the logged in user.
   *
   * @return user or null if user is not logged in
   */
  UserData getUser();

  /**
   * get/create the login/logout service.
   *
   * @return LoginLogoutRemoteServiceAsync
   */
  LoginLogoutRemoteServiceAsync getLoginLogoutService();

  /**
   * get/create the sepa service.
   *
   * @return SepaRemoteServiceAsync
   */
  SepaRemoteServiceAsync getSepaService();

  /**
   * get/create the address service.
   *
   * @return AddressRemoteServiceAsync
   */
  AddressRemoteServiceAsync getAddressService();

  /**
   * set a new user.
   *
   * @param puser new user to set
   * @param pplaceToken token of the place to switch to, when user changed
   */
  void setUser(UserData puser, String pplaceToken);

  /**
   * get/create the base page view.
   *
   * @return NavigationViewInterface
   */
  BasePageViewInterface getBasePageView();

  /**
   * get/create the settings view.
   *
   * @return SettingsViewInterface
   */
  SettingsViewInterface getSettingsView();

  /**
   * get/create the navigation view.
   *
   * @return NavigationViewInterface
   */
  NavigationViewInterface getNavigationView();

  /**
   * get/create the login view.
   *
   * @return LoginViewInterface
   */
  LoginViewInterface getLoginView();

  /**
   * get/create the secret view.
   *
   * @return SecretViewInterface
   */
  SecretViewInterface getSecretView();

  /**
   * get/create the about view.
   *
   * @return AboutViewInterface
   */
  AboutViewInterface getAboutView();

  /**
   * get/create the sepa view.
   *
   * @return SepaViewInterface
   */
  SepaViewInterface getSepaView();

  /**
   * get/create the address view.
   *
   * @return AddressViewInterface
   */
  AddressViewInterface getAddressView();
}

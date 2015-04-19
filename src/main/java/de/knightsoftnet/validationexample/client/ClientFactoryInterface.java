/*
 * Licensed to the Apache Software Foundation (ASF) under one or more contributor license
 * agreements. See the NOTICE file distributed with this work for additional information regarding
 * copyright ownership. The ASF licenses this file to You under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance with the License. You may obtain a
 * copy of the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */

package de.knightsoftnet.validationexample.client;

import de.knightsoftnet.validationexample.client.ui.basepage.BasePageViewInterface;
import de.knightsoftnet.validationexample.client.ui.navigation.AbstractNavigationPlace;
import de.knightsoftnet.validationexample.client.ui.navigation.AppPlaceHistoryMapper;
import de.knightsoftnet.validationexample.client.ui.navigation.NavigationViewInterface;
import de.knightsoftnet.validationexample.client.ui.page.about.AboutViewInterface;
import de.knightsoftnet.validationexample.client.ui.page.address.AddressRemoteServiceAsync;
import de.knightsoftnet.validationexample.client.ui.page.address.AddressViewInterface;
import de.knightsoftnet.validationexample.client.ui.page.login.LoginLogoutRemoteServiceAsync;
import de.knightsoftnet.validationexample.client.ui.page.login.LoginViewInterface;
import de.knightsoftnet.validationexample.client.ui.page.secret.SecretViewInterface;
import de.knightsoftnet.validationexample.client.ui.page.sepa.SepaRemoteServiceAsync;
import de.knightsoftnet.validationexample.client.ui.page.sepa.SepaViewInterface;
import de.knightsoftnet.validationexample.client.ui.page.settings.SettingsViewInterface;
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

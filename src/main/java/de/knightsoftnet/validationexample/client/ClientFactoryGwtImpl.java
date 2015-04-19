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

import de.knightsoftnet.validationexample.client.ui.basepage.BasePageViewGwtImpl;
import de.knightsoftnet.validationexample.client.ui.basepage.BasePageViewInterface;
import de.knightsoftnet.validationexample.client.ui.navigation.NavigationViewGwtImpl;
import de.knightsoftnet.validationexample.client.ui.navigation.NavigationViewInterface;
import de.knightsoftnet.validationexample.client.ui.page.about.AboutViewGwtImpl;
import de.knightsoftnet.validationexample.client.ui.page.about.AboutViewInterface;
import de.knightsoftnet.validationexample.client.ui.page.address.AddressViewGwtImpl;
import de.knightsoftnet.validationexample.client.ui.page.address.AddressViewInterface;
import de.knightsoftnet.validationexample.client.ui.page.login.LoginViewGwtImpl;
import de.knightsoftnet.validationexample.client.ui.page.login.LoginViewInterface;
import de.knightsoftnet.validationexample.client.ui.page.secret.SecretViewGwtImpl;
import de.knightsoftnet.validationexample.client.ui.page.secret.SecretViewInterface;
import de.knightsoftnet.validationexample.client.ui.page.sepa.SepaViewGwtImpl;
import de.knightsoftnet.validationexample.client.ui.page.sepa.SepaViewInterface;
import de.knightsoftnet.validationexample.client.ui.page.settings.SettingsViewGwtImpl;
import de.knightsoftnet.validationexample.client.ui.page.settings.SettingsViewInterface;

/**
 * Client factory implementation.
 *
 * @author Manfred Tremmel
 *
 */
public class ClientFactoryGwtImpl extends AbstractClientFactoryImpl implements
    ClientFactoryInterface {
  /**
   * view of the base page.
   */
  private BasePageViewInterface basePageView;

  /**
   * view of the navigation part.
   */
  private NavigationViewInterface navigationView;

  /**
   * settings view.
   */
  private SettingsViewInterface settingsView;

  /**
   * login view.
   */
  private LoginViewInterface loginView;

  /**
   * secret view.
   */
  private SecretViewInterface secretView;

  /**
   * about view.
   */
  private AboutViewInterface aboutView;

  /**
   * sepa view.
   */
  private SepaViewInterface sepaView;

  /**
   * address view.
   */
  private AddressViewInterface addressView;

  @Override
  public final BasePageViewInterface getBasePageView() {
    if (this.basePageView == null) {
      this.basePageView = new BasePageViewGwtImpl();
    }
    return this.basePageView;
  }

  @Override
  public final NavigationViewInterface getNavigationView() {
    if (this.navigationView == null) {
      this.navigationView = new NavigationViewGwtImpl();
    }
    return this.navigationView;
  }

  @Override
  public final SettingsViewInterface getSettingsView() {
    if (this.settingsView == null) {
      this.settingsView = new SettingsViewGwtImpl();
    }
    return this.settingsView;
  }

  @Override
  public final LoginViewInterface getLoginView() {
    if (this.loginView == null) {
      this.loginView = new LoginViewGwtImpl();
    }
    return this.loginView;
  }

  @Override
  public final SecretViewInterface getSecretView() {
    if (this.secretView == null) {
      this.secretView = new SecretViewGwtImpl();
    }
    return this.secretView;
  }

  @Override
  public final AboutViewInterface getAboutView() {
    if (this.aboutView == null) {
      this.aboutView = new AboutViewGwtImpl();
    }
    return this.aboutView;
  }

  @Override
  public final SepaViewInterface getSepaView() {
    if (this.sepaView == null) {
      this.sepaView = new SepaViewGwtImpl();
    }
    return this.sepaView;
  }

  @Override
  public final AddressViewInterface getAddressView() {
    if (this.addressView == null) {
      this.addressView = new AddressViewGwtImpl();
    }
    return this.addressView;
  }
}

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

package de.knightsoftnet.validationexample.client.gin;


import de.knightsoftnet.navigation.client.session.Session;
import de.knightsoftnet.navigation.client.ui.navigation.NavigationPresenter;
import de.knightsoftnet.navigation.client.ui.navigation.NavigationStructure;
import de.knightsoftnet.navigation.client.ui.navigation.TreeNavigationView;
import de.knightsoftnet.navigation.shared.models.User;
import de.knightsoftnet.validationexample.client.CurrentSession;
import de.knightsoftnet.validationexample.client.ui.basepage.BasePagePresenter;
import de.knightsoftnet.validationexample.client.ui.basepage.BasePageViewGwtImpl;
import de.knightsoftnet.validationexample.client.ui.navigation.MyNavigationStructure;
import de.knightsoftnet.validationexample.client.ui.page.about.AboutPresenter;
import de.knightsoftnet.validationexample.client.ui.page.about.AboutViewGwtImpl;
import de.knightsoftnet.validationexample.client.ui.page.address.AddressPresenter;
import de.knightsoftnet.validationexample.client.ui.page.address.AddressViewGwtImpl;
import de.knightsoftnet.validationexample.client.ui.page.login.LoginPresenter;
import de.knightsoftnet.validationexample.client.ui.page.login.LoginViewGwtImpl;
import de.knightsoftnet.validationexample.client.ui.page.logout.LogoutPresenter;
import de.knightsoftnet.validationexample.client.ui.page.logout.LogoutViewGwtImpl;
import de.knightsoftnet.validationexample.client.ui.page.phonenumber.PhoneNumberPresenter;
import de.knightsoftnet.validationexample.client.ui.page.phonenumber.PhoneNumberViewGwtImpl;
import de.knightsoftnet.validationexample.client.ui.page.secret.SecretPresenter;
import de.knightsoftnet.validationexample.client.ui.page.secret.SecretViewGwtImpl;
import de.knightsoftnet.validationexample.client.ui.page.sepa.SepaPresenter;
import de.knightsoftnet.validationexample.client.ui.page.sepa.SepaViewGwtImpl;
import de.knightsoftnet.validationexample.client.ui.page.settings.SettingsPresenter;
import de.knightsoftnet.validationexample.client.ui.page.settings.SettingsViewGwtImpl;
import de.knightsoftnet.validationexample.shared.models.UserData;

import com.google.inject.Singleton;
import com.gwtplatform.mvp.client.gin.AbstractPresenterModule;

public class ApplicationModule extends AbstractPresenterModule {

  @Override
  protected void configure() {
    this.bindPresenter(BasePagePresenter.class, BasePagePresenter.MyView.class,
        BasePageViewGwtImpl.class, BasePagePresenter.MyProxy.class);

    this.bindPresenter(NavigationPresenter.class, NavigationPresenter.MyView.class,
        TreeNavigationView.class, NavigationPresenter.MyProxy.class);

    this.bindPresenterWidget(AboutPresenter.class, AboutPresenter.MyView.class,
        AboutViewGwtImpl.class);
    this.bindPresenter(AddressPresenter.class, AddressPresenter.MyView.class,
        AddressViewGwtImpl.class, AddressPresenter.MyProxy.class);
    this.bindPresenter(LoginPresenter.class, LoginPresenter.MyView.class, LoginViewGwtImpl.class,
        LoginPresenter.MyProxy.class);
    this.bindPresenter(LogoutPresenter.class, LogoutPresenter.MyView.class, LogoutViewGwtImpl.class,
        LogoutPresenter.MyProxy.class);
    this.bindPresenter(SecretPresenter.class, SecretPresenter.MyView.class, SecretViewGwtImpl.class,
        SecretPresenter.MyProxy.class);
    this.bindPresenter(SepaPresenter.class, SepaPresenter.MyView.class, SepaViewGwtImpl.class,
        SepaPresenter.MyProxy.class);
    this.bindPresenter(PhoneNumberPresenter.class, PhoneNumberPresenter.MyView.class,
        PhoneNumberViewGwtImpl.class, PhoneNumberPresenter.MyProxy.class);
    this.bindPresenter(SettingsPresenter.class, SettingsPresenter.MyView.class,
        SettingsViewGwtImpl.class, SettingsPresenter.MyProxy.class);

    this.bind(User.class).to(UserData.class).in(Singleton.class);
    this.bind(Session.class).to(CurrentSession.class).in(Singleton.class);
    this.bind(NavigationStructure.class).to(MyNavigationStructure.class).in(Singleton.class);
  }
}

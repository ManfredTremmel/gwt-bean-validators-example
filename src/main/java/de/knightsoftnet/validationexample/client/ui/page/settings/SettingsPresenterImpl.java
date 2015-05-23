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

package de.knightsoftnet.validationexample.client.ui.page.settings;

import de.knightsoftnet.validationexample.client.ClientFactoryInterface;
import de.knightsoftnet.validationexample.client.JsHelper;
import de.knightsoftnet.validationexample.client.ui.basepage.AbstractPresenter;

import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.RunAsyncCallback;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.i18n.client.LocaleInfo;
import com.google.gwt.user.client.Cookies;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.AcceptsOneWidget;

/**
 * Activity/Presenter of the validator test settings.
 *
 * @author Manfred Tremmel
 *
 */
public class SettingsPresenterImpl extends AbstractPresenter implements SettingsPresenterInterface {
  /**
   * the constructor of the activity/presenter.
   *
   * @param pclientFactory client factory
   */
  public SettingsPresenterImpl(final ClientFactoryInterface pclientFactory) {
    super(pclientFactory);
  }

  @Override
  public final void start(final AcceptsOneWidget ppanel, final EventBus peventBus) {
    GWT.runAsync(new RunAsyncCallback() {

      @Override
      public void onSuccess() {
        final SettingsViewInterface view =
            SettingsPresenterImpl.this.getClientFactory().getSettingsView();
        view.setPresenter(SettingsPresenterImpl.this);

        ppanel.setWidget(view.asWidget());
      }

      @Override
      public void onFailure(final Throwable preason) {
        JsHelper.forceReload();
      }
    });
  }

  @Override
  public final void changeLanguage(final String planguage) {
    Cookies.setCookie(LocaleInfo.getLocaleCookieName(), planguage);
    Window.Location.reload();
  }
}

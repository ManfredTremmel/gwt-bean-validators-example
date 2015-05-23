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

package de.knightsoftnet.validationexample.client.ui.page.logout;

import de.knightsoftnet.validationexample.client.ClientFactoryInterface;
import de.knightsoftnet.validationexample.client.ui.basepage.AbstractPresenter;

import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.AcceptsOneWidget;

/**
 * Activity/Presenter of the logout, implementation.
 *
 * @author Manfred Tremmel
 *
 */
public class LogoutPresenterImpl extends AbstractPresenter implements LogoutPresenterInterface {
  /**
   * the constructor of the activity/presenter.
   *
   * @param pclientFactory client factory
   */
  public LogoutPresenterImpl(final ClientFactoryInterface pclientFactory) {
    super(pclientFactory);
  }

  @Override
  public final void start(final AcceptsOneWidget ppanel, final EventBus peventBus) {
    // removed user and go to home screen
    this.getClientFactory().getLoginLogoutService().logout(new AsyncCallback<Void>() {

      @Override
      public void onFailure(final Throwable pcaught) {
        // we've logged out, it doesn't matter!
      }

      @Override
      public void onSuccess(final Void presult) {
        // we've logged out, it doesn't matter!
      }

    });
    this.getClientFactory().setUser(null,
        this.getClientFactory().getNavigationPlace().getFirstToken());
  }
}

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

package de.knightsoftnet.validationexample.client.ui.page.secret;

import de.knightsoftnet.validationexample.client.ClientFactoryInterface;
import de.knightsoftnet.validationexample.client.JsHelper;
import de.knightsoftnet.validationexample.client.ui.basepage.AbstractPresenter;

import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.RunAsyncCallback;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.ui.AcceptsOneWidget;

/**
 * Activity/Presenter of the secret page, implementation.
 *
 * @author Manfred Tremmel
 *
 */
public class SecretPresenterImpl extends AbstractPresenter implements SecretPresenterInterface {
  /**
   * the constructor of the activity/presenter.
   *
   * @param pclientFactory client factory
   */
  public SecretPresenterImpl(final ClientFactoryInterface pclientFactory) {
    super(pclientFactory);
  }

  @Override
  public final void start(final AcceptsOneWidget ppanel, final EventBus pcventBus) {
    GWT.runAsync(new RunAsyncCallback() {

      @Override
      public void onSuccess() {
        final SecretViewInterface view =
            SecretPresenterImpl.this.getClientFactory().getSecretView();
        view.setPresenter(SecretPresenterImpl.this);
        ppanel.setWidget(view.asWidget());
      }

      @Override
      public void onFailure(final Throwable preason) {
        JsHelper.forceReload();
      }
    });
  }
}

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

package de.knightsoftnet.validationexample.client.ui.page.about;

import de.knightsoftnet.validationexample.client.ClientFactoryInterface;
import de.knightsoftnet.validationexample.client.JsHelper;
import de.knightsoftnet.validationexample.client.ui.basepage.AbstractPresenter;

import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.RunAsyncCallback;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.ui.AcceptsOneWidget;

/**
 * Activity/Presenter of the about page, implementation.
 *
 * @author Manfred Tremmel
 *
 */
public class AboutPresenterImpl extends AbstractPresenter implements AboutPresenterInterface {

  /**
   * place to remember.
   */
  private final AboutPlace place;

  /**
   * the constructor of the activity/presenter.
   *
   * @param pplace the place representing this page
   * @param pclientFactory client factory
   */
  public AboutPresenterImpl(final AboutPlace pplace, final ClientFactoryInterface pclientFactory) {
    super(pclientFactory);
    this.place = pplace;
  }

  @Override
  public final void start(final AcceptsOneWidget ppanel, final EventBus peventBus) {
    GWT.runAsync(new RunAsyncCallback() {

      @Override
      public void onSuccess() {
        final AboutViewInterface view = AboutPresenterImpl.this.getClientFactory().getAboutView();
        view.setPresenter(AboutPresenterImpl.this);

        ppanel.setWidget(view.asWidget());
      }

      @Override
      public void onFailure(final Throwable preason) {
        JsHelper.forceReload();
      }
    });
  }

  @Override
  public final void goBackToPreviousPage() {
    if (this.place != null) {
      this.getClientFactory()
          .getPlaceController()
          .goTo(
              this.getClientFactory().getPlaceHistoryMapper()
                  .getPlace(this.place.getRedirectToken()));
    }
  }

  @Override
  public final String mayStop() {
    // Nothing to do
    return null;
  }

  @Override
  public final void onCancel() {
    // Nothing to do
  }

  @Override
  public final void onStop() {
    // Nothing to do
  }
}

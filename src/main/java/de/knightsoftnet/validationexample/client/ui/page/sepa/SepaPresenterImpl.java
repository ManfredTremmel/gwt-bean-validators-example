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

package de.knightsoftnet.validationexample.client.ui.page.sepa;

import de.knightsoftnet.validationexample.client.ClientFactoryInterface;
import de.knightsoftnet.validationexample.client.JsHelper;
import de.knightsoftnet.validationexample.client.ui.basepage.AbstractPresenter;
import de.knightsoftnet.validationexample.shared.models.CountryEnum;
import de.knightsoftnet.validationexample.shared.models.SepaData;
import de.knightsoftnet.validators.shared.exceptions.ValidationException;

import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.RunAsyncCallback;
import com.google.gwt.core.client.Scheduler;
import com.google.gwt.core.client.Scheduler.ScheduledCommand;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.AcceptsOneWidget;

/**
 * Activity/Presenter of the Sepa, implementation.
 *
 * @author Manfred Tremmel
 *
 */
public class SepaPresenterImpl extends AbstractPresenter implements SepaPresenterInterface {

  /**
   * sepa data to remember.
   */
  private final SepaData sepaData;

  /**
   * view of the page.
   */
  private SepaViewInterface view;

  /**
   * the constructor of the activity/presenter.
   *
   * @param pclientFactory client factory
   */
  public SepaPresenterImpl(final ClientFactoryInterface pclientFactory) {
    super(pclientFactory);
    this.sepaData = new SepaData();
    this.sepaData.setCountryCode(CountryEnum.DE);
  }

  @Override
  public final void start(final AcceptsOneWidget ppanel, final EventBus peventBus) {
    GWT.runAsync(new RunAsyncCallback() {

      @Override
      public void onSuccess() {
        SepaPresenterImpl.this.view = SepaPresenterImpl.this.getClientFactory().getSepaView();
        SepaPresenterImpl.this.view.setPresenter(SepaPresenterImpl.this);
        SepaPresenterImpl.this.view.fillForm(SepaPresenterImpl.this.sepaData);
        ppanel.setWidget(SepaPresenterImpl.this.view.asWidget());
        Scheduler.get().scheduleDeferred(new ScheduledCommand() {
          @Override
          public void execute() {
            SepaPresenterImpl.this.view.setFocusOnFirstWidget();
          }
        });
      }

      @Override
      public void onFailure(final Throwable preason) {
        JsHelper.forceReload();
      }
    });
  }

  @Override
  public final void tryToSend() {
    this.getClientFactory().getSepaService().sendSepa(this.sepaData, new AsyncCallback<SepaData>() {
      @Override
      public void onFailure(final Throwable pcaught) {
        try {
          throw pcaught;
        } catch (final ValidationException e) {
          SepaPresenterImpl.this.getClientFactory().getSepaView().getDriver()
              .setConstraintViolations(e.getValidationErrorSet(SepaPresenterImpl.this.sepaData));
        } catch (final Throwable e) {
          final SepaConstants constants = GWT.create(SepaConstants.class);
          SepaPresenterImpl.this.getClientFactory().getSepaView()
              .showMessage(constants.messageSepaError());
        }
      }

      @Override
      public void onSuccess(final SepaData presult) {
        final SepaConstants constants = GWT.create(SepaConstants.class);
        if (presult == null) {
          SepaPresenterImpl.this.getClientFactory().getSepaView()
              .showMessage(constants.messageSepaError());
        } else {
          SepaPresenterImpl.this.getClientFactory().getSepaView()
              .showMessage(constants.messageSepaOk());
        }
      }
    });
  }
}

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

package de.knightsoftnet.validationexample.client.ui.page.address;

import de.knightsoftnet.validationexample.client.ClientFactoryInterface;
import de.knightsoftnet.validationexample.client.JsHelper;
import de.knightsoftnet.validationexample.client.ui.basepage.AbstractPresenter;
import de.knightsoftnet.validationexample.shared.models.CountryEnum;
import de.knightsoftnet.validationexample.shared.models.PostalAddressData;
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
public class AddressPresenterImpl extends AbstractPresenter implements AddressPresenterInterface {

  /**
   * address data to remember.
   */
  private final PostalAddressData addressData;

  /**
   * view of the page.
   */
  private AddressViewInterface view;

  /**
   * the constructor of the activity/presenter.
   *
   * @param pclientFactory client factory
   */
  public AddressPresenterImpl(final ClientFactoryInterface pclientFactory) {
    super(pclientFactory);
    this.addressData = new PostalAddressData();
    final AddressConstants constants = GWT.create(AddressConstants.class);
    this.addressData.setCountryCode(CountryEnum.valueOf(constants.defaultCountry()));
  }

  @Override
  public final void start(final AcceptsOneWidget ppanel, final EventBus peventBus) {
    GWT.runAsync(new RunAsyncCallback() {

      @Override
      public void onSuccess() {
        AddressPresenterImpl.this.view =
            AddressPresenterImpl.this.getClientFactory().getAddressView();
        AddressPresenterImpl.this.view.setPresenter(AddressPresenterImpl.this);
        AddressPresenterImpl.this.view.fillForm(AddressPresenterImpl.this.addressData);
        ppanel.setWidget(AddressPresenterImpl.this.view.asWidget());
        Scheduler.get().scheduleDeferred(new ScheduledCommand() {
          @Override
          public void execute() {
            AddressPresenterImpl.this.view.setFocusOnFirstWidget();
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
    this.getClientFactory().getAddressService().sendPostalAddress(this.addressData,
        new AsyncCallback<PostalAddressData>() {
          @Override
          public void onFailure(final Throwable pcaught) {
            try {
              throw pcaught;
            } catch (final ValidationException e) {
              AddressPresenterImpl.this.getClientFactory().getAddressView().getDriver()
                  .setConstraintViolations(
                      e.getValidationErrorSet(AddressPresenterImpl.this.addressData));
            } catch (final Throwable e) {
              final AddressConstants constants = GWT.create(AddressConstants.class);
              AddressPresenterImpl.this.getClientFactory().getAddressView()
                  .showMessage(constants.messageAddressDataError());
            }
          }

          @Override
          public void onSuccess(final PostalAddressData presult) {
            final AddressConstants constants = GWT.create(AddressConstants.class);
            if (presult == null) {
              AddressPresenterImpl.this.getClientFactory().getAddressView()
                  .showMessage(constants.messageAddressDataError());
            } else {
              AddressPresenterImpl.this.getClientFactory().getAddressView()
                  .showMessage(constants.messageAddressDataOk());
            }
          }
        });
  }
}

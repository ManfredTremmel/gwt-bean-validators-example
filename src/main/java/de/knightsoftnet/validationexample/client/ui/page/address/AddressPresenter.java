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

import de.knightsoftnet.mtwidgets.shared.models.CountryEnum;
import de.knightsoftnet.validationexample.client.ui.basepage.BasePagePresenter;
import de.knightsoftnet.validationexample.client.ui.navigation.NameTokens;
import de.knightsoftnet.validationexample.client.ui.page.sepa.SepaConstants;
import de.knightsoftnet.validationexample.shared.models.PostalAddressData;
import de.knightsoftnet.validators.shared.exceptions.ValidationException;

import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.Scheduler;
import com.google.gwt.core.client.Scheduler.ScheduledCommand;
import com.google.gwt.editor.client.Editor;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.mvp.client.Presenter;
import com.gwtplatform.mvp.client.View;
import com.gwtplatform.mvp.client.annotations.NameToken;
import com.gwtplatform.mvp.client.annotations.NoGatekeeper;
import com.gwtplatform.mvp.client.annotations.ProxyCodeSplit;
import com.gwtplatform.mvp.client.proxy.ProxyPlace;

import java.util.ArrayList;

import javax.inject.Inject;
import javax.validation.ConstraintViolation;

/**
 * Presenter of the address, implementation.
 *
 * @author Manfred Tremmel
 *
 */
public class AddressPresenter extends Presenter<AddressPresenter.MyView, AddressPresenter.MyProxy> {

  public interface MyView extends View, Editor<PostalAddressData> {
    /**
     * set a reference to the presenter/activity.
     *
     * @param ppresenter reference to set
     */
    void setPresenter(AddressPresenter ppresenter);

    /**
     * fill the form with data.
     *
     * @param psepData data to fill into the form
     */
    void fillForm(PostalAddressData psepData);

    /**
     * display a message on the screen.
     *
     * @param pmessage the message to display
     */
    void showMessage(String pmessage);

    /**
     * set focus on first widget.
     */
    void setFocusOnFirstWidget();

    /**
     * display validation errors.
     *
     * @param pvalidationErrorSet list of violations
     */
    void setConstraintViolations(ArrayList<ConstraintViolation<?>> pvalidationErrorSet);
  }

  @ProxyCodeSplit
  @NameToken(NameTokens.ADDRESS)
  @NoGatekeeper
  public interface MyProxy extends ProxyPlace<AddressPresenter> {
  }

  private final PostalAddressData addressData;
  private final AddressRemoteServiceAsync service;

  /**
   * constructor injecting parameters.
   *
   * @param peventBus event bus
   * @param pview view of the page
   * @param pproxy proxy to handle page
   * @param pservice remote service to contact the server
   * @param pconstants localization constants
   */
  @Inject
  public AddressPresenter(final EventBus peventBus, final AddressPresenter.MyView pview,
      final MyProxy pproxy, final AddressRemoteServiceAsync pservice, //
      final SepaConstants pconstants) {
    super(peventBus, pview, pproxy, BasePagePresenter.SLOT_MAIN_CONTENT);
    this.service = pservice;
    this.addressData = new PostalAddressData();
    this.addressData.setCountryCode(CountryEnum.valueOf(pconstants.defaultCountry()));
    this.getView().setPresenter(this);
    this.getView().fillForm(this.addressData);
  }

  @Override
  protected void onReveal() {
    super.onReveal();
    Scheduler.get().scheduleDeferred(new ScheduledCommand() {
      @Override
      public void execute() {
        AddressPresenter.this.getView().setFocusOnFirstWidget();
      }
    });
  }

  /**
   * try to send data.
   */
  public final void tryToSend() {
    this.service.sendPostalAddress(this.addressData, new AsyncCallback<PostalAddressData>() {
      @Override
      public void onFailure(final Throwable pcaught) {
        try {
          throw pcaught;
        } catch (final ValidationException e) {
          AddressPresenter.this.getView()
              .setConstraintViolations(e.getValidationErrorSet(AddressPresenter.this.addressData));
        } catch (final Throwable e) {
          final AddressConstants constants = GWT.create(AddressConstants.class);
          AddressPresenter.this.getView().showMessage(constants.messageAddressDataError());
        }
      }

      @Override
      public void onSuccess(final PostalAddressData presult) {
        final AddressConstants constants = GWT.create(AddressConstants.class);
        if (presult == null) {
          AddressPresenter.this.getView().showMessage(constants.messageAddressDataError());
        } else {
          AddressPresenter.this.getView().showMessage(constants.messageAddressDataOk());
        }
      }
    });
  }
}

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

package de.knightsoftnet.validationexample.client.ui.page.phonenumber;

import de.knightsoftnet.mtwidgets.shared.models.CountryEnum;
import de.knightsoftnet.validationexample.client.ui.basepage.BasePagePresenter;
import de.knightsoftnet.validationexample.client.ui.navigation.NameTokens;
import de.knightsoftnet.validationexample.shared.models.PhoneNumberData;
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
 * Presenter of the Sepa, implementation.
 *
 * @author Manfred Tremmel
 *
 */
public class PhoneNumberPresenter
    extends Presenter<PhoneNumberPresenter.MyView, PhoneNumberPresenter.MyProxy> {

  public interface MyView extends View, Editor<PhoneNumberData> {
    /**
     * set a reference to the presenter/activity.
     *
     * @param ppresenter reference to set
     */
    void setPresenter(PhoneNumberPresenter ppresenter);

    /**
     * fill the form with data.
     *
     * @param pphoneNumberData data to fill into the form
     */
    void fillForm(PhoneNumberData pphoneNumberData);

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
  @NameToken(NameTokens.PHONE_NUMBER)
  @NoGatekeeper
  public interface MyProxy extends ProxyPlace<PhoneNumberPresenter> {
  }

  /**
   * phone number data to remember.
   */
  private final PhoneNumberData phoneNumberData;

  /**
   * phone number remote service.
   */
  private final PhoneNumberRemoteServiceAsync service;

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
  public PhoneNumberPresenter(final EventBus peventBus, final PhoneNumberPresenter.MyView pview,
      final MyProxy pproxy, final PhoneNumberRemoteServiceAsync pservice, //
      final PhoneNumberConstants pconstants) {
    super(peventBus, pview, pproxy, BasePagePresenter.SLOT_MAIN_CONTENT);
    this.service = pservice;
    this.phoneNumberData = new PhoneNumberData();
    this.phoneNumberData.setCountryCode(CountryEnum.valueOf(pconstants.defaultCountry()));
    this.getView().setPresenter(this);
    this.getView().fillForm(this.phoneNumberData);
  }

  @Override
  protected void onReveal() {
    super.onReveal();
    Scheduler.get().scheduleDeferred(new ScheduledCommand() {
      @Override
      public void execute() {
        PhoneNumberPresenter.this.getView().setFocusOnFirstWidget();
      }
    });
  }

  /**
   * try to send data.
   */
  public final void tryToSend() {
    this.service.sendPhoneNumber(this.phoneNumberData, new AsyncCallback<PhoneNumberData>() {
      @Override
      public void onFailure(final Throwable pcaught) {
        try {
          throw pcaught;
        } catch (final ValidationException e) {
          PhoneNumberPresenter.this.getView().setConstraintViolations(
              e.getValidationErrorSet(PhoneNumberPresenter.this.phoneNumberData));
        } catch (final Throwable e) {
          final PhoneNumberConstants constants = GWT.create(PhoneNumberConstants.class);
          PhoneNumberPresenter.this.getView().showMessage(constants.messagePhoneNumberError());
        }
      }

      @Override
      public void onSuccess(final PhoneNumberData presult) {
        final PhoneNumberConstants constants = GWT.create(PhoneNumberConstants.class);
        if (presult == null) {
          PhoneNumberPresenter.this.getView().showMessage(constants.messagePhoneNumberError());
        } else {
          PhoneNumberPresenter.this.getView().showMessage(constants.messagePhoneNumberOk());
        }
      }
    });
  }
}

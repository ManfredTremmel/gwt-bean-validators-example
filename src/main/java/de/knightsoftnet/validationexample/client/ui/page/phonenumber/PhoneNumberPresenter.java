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
import de.knightsoftnet.navigation.client.session.Session;
import de.knightsoftnet.validationexample.client.services.PhoneRestService;
import de.knightsoftnet.validationexample.client.ui.basepage.BasePagePresenter;
import de.knightsoftnet.validationexample.client.ui.navigation.NameTokens;
import de.knightsoftnet.validationexample.shared.models.PhoneNumberData;
import de.knightsoftnet.validators.client.rest.helper.AbstractRestCallback;
import de.knightsoftnet.validators.client.rest.helper.EditorWithErrorHandling;

import com.google.gwt.core.client.Scheduler;
import com.google.gwt.core.client.Scheduler.ScheduledCommand;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.dispatch.rest.client.RestDispatch;
import com.gwtplatform.mvp.client.Presenter;
import com.gwtplatform.mvp.client.annotations.NameToken;
import com.gwtplatform.mvp.client.annotations.NoGatekeeper;
import com.gwtplatform.mvp.client.annotations.ProxyCodeSplit;
import com.gwtplatform.mvp.client.proxy.ProxyPlace;

import org.apache.commons.lang3.BooleanUtils;

import javax.inject.Inject;

/**
 * Presenter of the Sepa, implementation.
 *
 * @author Manfred Tremmel
 *
 */
public class PhoneNumberPresenter
    extends Presenter<PhoneNumberPresenter.MyView, PhoneNumberPresenter.MyProxy> {

  public interface MyView extends EditorWithErrorHandling<PhoneNumberPresenter, PhoneNumberData> {
  }

  @ProxyCodeSplit
  @NameToken(NameTokens.PHONE_NUMBER)
  @NoGatekeeper
  public interface MyProxy extends ProxyPlace<PhoneNumberPresenter> {
  }

  private final PhoneNumberData phoneNumberData;

  private final PhoneNumberConstants constants;
  private final RestDispatch dispatcher;
  private final PhoneRestService phoneNumberService;
  private final Session session;

  /**
   * constructor injecting parameters.
   */
  @Inject
  public PhoneNumberPresenter(final EventBus peventBus, final PhoneNumberPresenter.MyView pview,
      final MyProxy pproxy, final PhoneNumberConstants pconstants, final RestDispatch pdispatcher,
      final PhoneRestService pphoneNumberService, final Session psession) {
    super(peventBus, pview, pproxy, BasePagePresenter.SLOT_MAIN_CONTENT);
    this.constants = pconstants;
    this.dispatcher = pdispatcher;
    this.session = psession;
    this.phoneNumberService = pphoneNumberService;
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
    this.dispatcher.execute(this.phoneNumberService.checkPhoneNumber(this.phoneNumberData),
        new AbstractRestCallback<PhoneNumberPresenter, PhoneNumberData, MyView, Boolean>(
            this.getView(), this.phoneNumberData, this.session) {

          @Override
          public void onSuccess(final Boolean presult) {
            if (BooleanUtils.isTrue(presult)) {
              this.view.showMessage(PhoneNumberPresenter.this.constants.messagePhoneNumberOk());
            } else {
              this.view.showMessage(PhoneNumberPresenter.this.constants.messagePhoneNumberError());
            }
          }

        });
  }
}

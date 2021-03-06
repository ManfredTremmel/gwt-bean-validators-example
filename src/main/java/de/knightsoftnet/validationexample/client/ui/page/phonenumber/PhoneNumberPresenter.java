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

import de.knightsoftnet.gwtp.spring.client.rest.helper.AbstractPresenterWithErrorHandling;
import de.knightsoftnet.gwtp.spring.client.rest.helper.EditorWithErrorHandling;
import de.knightsoftnet.gwtp.spring.client.rest.helper.RestCallbackBuilder;
import de.knightsoftnet.gwtp.spring.client.session.Session;
import de.knightsoftnet.navigation.client.ui.basepage.AbstractBasePagePresenter;
import de.knightsoftnet.validationexample.client.services.PhoneRestService;
import de.knightsoftnet.validationexample.client.ui.navigation.NameTokens;
import de.knightsoftnet.validationexample.shared.models.PhoneNumberData;
import de.knightsoftnet.validators.client.event.FormSubmitHandler;
import de.knightsoftnet.validators.shared.data.CountryEnum;

import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.dispatch.rest.client.RestDispatch;
import com.gwtplatform.mvp.client.annotations.NameToken;
import com.gwtplatform.mvp.client.annotations.NoGatekeeper;
import com.gwtplatform.mvp.client.annotations.ProxyCodeSplit;
import com.gwtplatform.mvp.client.proxy.ProxyPlace;

import javax.inject.Inject;

/**
 * Presenter of the Sepa, implementation.
 *
 * @author Manfred Tremmel
 *
 */
public class PhoneNumberPresenter extends
    AbstractPresenterWithErrorHandling<PhoneNumberPresenter.MyProxy, PhoneNumberPresenter.MyView, //
        PhoneNumberData> {

  public interface MyView extends EditorWithErrorHandling<PhoneNumberPresenter, PhoneNumberData>,
      FormSubmitHandler<PhoneNumberData> {
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
  public PhoneNumberPresenter(final EventBus peventBus, final MyView pview, final MyProxy pproxy,
      final PhoneNumberConstants pconstants, final RestDispatch pdispatcher,
      final PhoneRestService pphoneNumberService, final Session psession) {
    super(peventBus, pview, pproxy, AbstractBasePagePresenter.SLOT_MAIN_CONTENT);
    constants = pconstants;
    dispatcher = pdispatcher;
    session = psession;
    phoneNumberService = pphoneNumberService;
    phoneNumberData = new PhoneNumberData();
    phoneNumberData.setCountryCode(CountryEnum.valueOf(pconstants.defaultCountry()));
    getView().setPresenter(this);
    getView().fillForm(phoneNumberData);
  }

  /**
   * try to send data.
   */
  public final void tryToSend() {
    dispatcher.execute(phoneNumberService.checkPhoneNumber(phoneNumberData),
        RestCallbackBuilder.build(getView(), phoneNumberData, session,
            presult -> getView().showMessage(constants.messagePhoneNumberOk())));
  }
}

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

import de.knightsoftnet.mtwidgets.shared.models.CountryEnum;
import de.knightsoftnet.navigation.client.session.Session;
import de.knightsoftnet.navigation.client.ui.basepage.AbstractBasePagePresenter;
import de.knightsoftnet.validationexample.client.services.SepaRestService;
import de.knightsoftnet.validationexample.client.ui.navigation.NameTokens;
import de.knightsoftnet.validationexample.shared.models.SepaData;
import de.knightsoftnet.validators.client.event.FormSubmitHandler;
import de.knightsoftnet.validators.client.rest.helper.AbstractPresenterWithErrorHandling;
import de.knightsoftnet.validators.client.rest.helper.EditorWithErrorHandling;
import de.knightsoftnet.validators.client.rest.helper.RestCallbackBuilder;
import de.knightsoftnet.validators.server.data.CreateIbanLengthMapConstantsClass;
import de.knightsoftnet.validators.shared.data.IbanLengthMapSharedConstants;

import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.dispatch.rest.client.RestDispatch;
import com.gwtplatform.mvp.client.annotations.NameToken;
import com.gwtplatform.mvp.client.annotations.NoGatekeeper;
import com.gwtplatform.mvp.client.annotations.ProxyCodeSplit;
import com.gwtplatform.mvp.client.proxy.ProxyPlace;

import java.util.Collection;
import java.util.stream.Collectors;

import javax.inject.Inject;

/**
 * Presenter of the Sepa, implementation.
 *
 * @author Manfred Tremmel
 *
 */
public class SepaPresenter extends
    AbstractPresenterWithErrorHandling<SepaPresenter.MyProxy, SepaPresenter.MyView, SepaData> {

  public interface MyView
      extends EditorWithErrorHandling<SepaPresenter, SepaData>, FormSubmitHandler<SepaData> {
  }

  @ProxyCodeSplit
  @NameToken(NameTokens.SEPA)
  @NoGatekeeper
  public interface MyProxy extends ProxyPlace<SepaPresenter> {
  }

  private final SepaData sepaData;

  private final SepaConstants constants;
  private final RestDispatch dispatcher;
  private final SepaRestService sepaService;
  private final Session session;

  /**
   * constructor injecting parameters.
   */
  @Inject
  public SepaPresenter(final EventBus peventBus, final MyView pview, final MyProxy pproxy,
      final SepaConstants pconstants, final RestDispatch pdispatcher,
      final SepaRestService psepaService, final Session psession) {
    super(peventBus, pview, pproxy, AbstractBasePagePresenter.SLOT_MAIN_CONTENT);
    this.constants = pconstants;
    this.dispatcher = pdispatcher;
    this.sepaService = psepaService;
    this.session = psession;
    this.sepaData = new SepaData();
    this.sepaData.setCountryCode(CountryEnum.valueOf(pconstants.defaultCountry()));
    this.getView().setPresenter(this);
    this.getView().fillForm(this.sepaData);
  }

  /**
   * try to send data.
   */
  public final void tryToSend() {
    this.dispatcher.execute(this.sepaService.checkSepa(this.sepaData),
        RestCallbackBuilder.build(this.getView(), this.sepaData, this.session,
            presult -> this.getView().showMessage(this.constants.messageSepaOk())));
  }

  /**
   * get a list of countries which take part on sepa procedure.
   *
   * @return array of countries
   */
  public Collection<CountryEnum> getSepaCountries() {
    final IbanLengthMapSharedConstants ibanMap = CreateIbanLengthMapConstantsClass.create();
    return ibanMap.ibanLengths().keySet().stream().map(entry -> CountryEnum.valueOf(entry))
        .collect(Collectors.toList());
  }
}

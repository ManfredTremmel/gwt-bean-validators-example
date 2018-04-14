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

package de.knightsoftnet.validationexample.client.ui.page.person;

import de.knightsoftnet.navigation.client.session.Session;
import de.knightsoftnet.navigation.client.ui.basepage.AbstractBasePagePresenter;
import de.knightsoftnet.validationexample.client.services.PersonRestService;
import de.knightsoftnet.validationexample.client.ui.navigation.NameTokens;
import de.knightsoftnet.validationexample.shared.AppParameters;
import de.knightsoftnet.validationexample.shared.models.Person;
import de.knightsoftnet.validators.client.event.FormSubmitHandler;
import de.knightsoftnet.validators.client.rest.helper.EditorWithErrorHandling;
import de.knightsoftnet.validators.client.rest.helper.RestCallbackBuilder;
import de.knightsoftnet.validators.server.data.CreateIbanLengthMapConstantsClass;
import de.knightsoftnet.validators.shared.data.CountryEnum;
import de.knightsoftnet.validators.shared.data.IbanLengthMapSharedConstants;

import com.google.gwt.core.client.Scheduler;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.dispatch.rest.client.RestDispatch;
import com.gwtplatform.mvp.client.Presenter;
import com.gwtplatform.mvp.client.annotations.NameToken;
import com.gwtplatform.mvp.client.annotations.ProxyCodeSplit;
import com.gwtplatform.mvp.client.proxy.PlaceManager;
import com.gwtplatform.mvp.client.proxy.ProxyPlace;
import com.gwtplatform.mvp.shared.proxy.PlaceRequest;
import com.gwtplatform.mvp.shared.proxy.PlaceRequest.Builder;

import org.apache.commons.lang3.StringUtils;

import java.util.Objects;

import javax.inject.Inject;

/**
 * Presenter of the Sepa, implementation.
 *
 * @author Manfred Tremmel
 *
 */
public class PersonPresenter extends Presenter<PersonPresenter.MyView, PersonPresenter.MyProxy> {

  public interface MyView
      extends EditorWithErrorHandling<PersonPresenter, Person>, FormSubmitHandler<Person> {
  }

  @ProxyCodeSplit
  @NameToken(NameTokens.PERSON_WITH_ID)
  public interface MyProxy extends ProxyPlace<PersonPresenter> {
  }

  private Person personData;

  private final PlaceManager placeManager;
  private final PersonConstants constants;
  private final RestDispatch dispatcher;
  private final PersonRestService personService;
  private final Session session;

  /**
   * constructor injecting parameters.
   */
  @Inject
  public PersonPresenter(final EventBus peventBus, final MyView pview, final MyProxy pproxy,
      final PlaceManager pplaceManager, final PersonConstants pconstants,
      final RestDispatch pdispatcher, final PersonRestService ppersonService,
      final Session psession) {
    super(peventBus, pview, pproxy, AbstractBasePagePresenter.SLOT_MAIN_CONTENT);
    this.placeManager = pplaceManager;
    this.constants = pconstants;
    this.dispatcher = pdispatcher;
    this.personService = ppersonService;
    this.session = psession;
    this.getView().setPresenter(this);
    this.fillForm(new Person(), null);
  }

  @Override
  protected void onReveal() {
    super.onReveal();
    Scheduler.get().scheduleDeferred(() -> PersonPresenter.this.getView().setFocusOnFirstWidget());
  }

  /**
   * try to send data.
   */
  public final void tryToSend() {
    this.dispatcher.execute(this.personService.save(this.personData),
        RestCallbackBuilder.build(this.getView(), this.personData, this.session,
            presult -> this.fillForm(presult, this.constants.messageOk())));
  }

  /**
   * get a list of countries which take part on sepa procedure.
   *
   * @return array of countries
   */
  public CountryEnum[] getSepaCountries() {
    final IbanLengthMapSharedConstants ibanMap = CreateIbanLengthMapConstantsClass.create();
    final CountryEnum[] countryList = new CountryEnum[ibanMap.ibanLengths().size()];
    int pos = 0;
    for (final String entry : ibanMap.ibanLengths().keySet()) {
      countryList[pos++] = CountryEnum.valueOf(entry);
    }
    return countryList;
  }

  /**
   * add new empty entry into form.
   */
  public void addNewEntry() {
    this.fillForm(new Person(), null);
  }

  /**
   * read one entry.
   */
  public void readEntry(final Long pid) {
    this.dispatcher.execute(this.personService.findOne(pid), RestCallbackBuilder.build(
        this.getView(), this.personData, this.session, presult -> this.fillForm(presult, null)));
  }

  /**
   * delete entry.
   */
  public void deleteEntry(final Long pid) {
    this.dispatcher.execute(this.personService.delete(pid),
        RestCallbackBuilder.build(this.getView(), this.personData, this.session,
            presult -> this.fillForm(new Person(), null)));
  }

  @Override
  public void prepareFromRequest(final PlaceRequest prequest) {
    super.prepareFromRequest(prequest);
    final String page = prequest.getParameter(AppParameters.ID, null);
    if (StringUtils.isNumeric(page)) {
      this.readEntry(Long.valueOf(page));
    }
  }


  private void fillForm(final Person ppersonData, final String pmessage) {
    this.personData = ppersonData;
    this.getView().fillForm(this.personData);
    this.getView().showMessage(pmessage);
    final Builder placeRequestBuilder =
        new PlaceRequest.Builder().nameToken(NameTokens.PERSON_WITH_ID);
    placeRequestBuilder.with(AppParameters.ID,
        Objects.toString(this.personData.getId(), StringUtils.EMPTY));
    this.placeManager.updateHistory(placeRequestBuilder.build(), true);
  }
}

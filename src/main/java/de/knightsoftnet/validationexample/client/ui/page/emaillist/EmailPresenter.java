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

package de.knightsoftnet.validationexample.client.ui.page.emaillist;

import de.knightsoftnet.navigation.client.session.Session;
import de.knightsoftnet.navigation.client.ui.basepage.AbstractBasePagePresenter;
import de.knightsoftnet.validationexample.client.services.EmailListRestService;
import de.knightsoftnet.validationexample.client.ui.navigation.NameTokens;
import de.knightsoftnet.validationexample.shared.models.EmailListData;
import de.knightsoftnet.validators.client.event.FormSubmitHandler;
import de.knightsoftnet.validators.client.rest.helper.AbstractPresenterWithErrorHandling;
import de.knightsoftnet.validators.client.rest.helper.EditorWithErrorHandling;
import de.knightsoftnet.validators.client.rest.helper.RestCallbackBuilder;

import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.dispatch.rest.client.RestDispatch;
import com.gwtplatform.mvp.client.annotations.NameToken;
import com.gwtplatform.mvp.client.annotations.NoGatekeeper;
import com.gwtplatform.mvp.client.annotations.ProxyCodeSplit;
import com.gwtplatform.mvp.client.proxy.ProxyPlace;

import javax.inject.Inject;

/**
 * Presenter of the E-Mail list, implementation.
 *
 * @author Manfred Tremmel
 *
 */
public class EmailPresenter
    extends AbstractPresenterWithErrorHandling<EmailPresenter.MyProxy, EmailPresenter.MyView, //
        EmailListData> {

  public interface MyView extends EditorWithErrorHandling<EmailPresenter, EmailListData>,
      FormSubmitHandler<EmailListData> {
  }

  @ProxyCodeSplit
  @NameToken(NameTokens.EMAIL_LIST)
  @NoGatekeeper
  public interface MyProxy extends ProxyPlace<EmailPresenter> {
  }

  private final EmailConstants constants;
  private final EmailListData emailListData;

  private final RestDispatch dispatcher;
  private final EmailListRestService emailListService;
  private final Session session;

  /**
   * constructor injecting parameters.
   */
  @Inject
  public EmailPresenter(final EventBus peventBus, final MyView pview, final MyProxy pproxy,
      final EmailConstants pconstants, final RestDispatch pdispatcher,
      final EmailListRestService pemailListService, final Session psession) {
    super(peventBus, pview, pproxy, AbstractBasePagePresenter.SLOT_MAIN_CONTENT);
    this.dispatcher = pdispatcher;
    this.emailListService = pemailListService;
    this.constants = pconstants;
    this.session = psession;
    this.emailListData = new EmailListData();
    this.getView().setPresenter(this);
    this.getView().fillForm(this.emailListData);
  }

  /**
   * try to send data.
   */
  public final void tryToSend() {
    this.dispatcher.execute(this.emailListService.checkEmailList(this.emailListData),
        RestCallbackBuilder.build(this.getView(), this.emailListData, this.session,
            presult -> this.getView().showMessage(this.constants.messageOk())));
  }
}

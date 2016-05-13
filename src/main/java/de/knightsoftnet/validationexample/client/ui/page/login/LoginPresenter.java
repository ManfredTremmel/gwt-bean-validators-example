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

package de.knightsoftnet.validationexample.client.ui.page.login;

import de.knightsoftnet.navigation.client.session.Session;
import de.knightsoftnet.validationexample.client.services.UserRestService;
import de.knightsoftnet.validationexample.client.ui.basepage.BasePagePresenter;
import de.knightsoftnet.validationexample.client.ui.navigation.NameTokens;
import de.knightsoftnet.validationexample.shared.models.LoginData;
import de.knightsoftnet.validationexample.shared.models.UserData;
import de.knightsoftnet.validators.client.rest.helper.EditorWithErrorHandling;

import com.google.gwt.core.client.Scheduler;
import com.google.gwt.core.client.Scheduler.ScheduledCommand;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.dispatch.rest.client.RestDispatch;
import com.gwtplatform.mvp.client.Presenter;
import com.gwtplatform.mvp.client.annotations.NameToken;
import com.gwtplatform.mvp.client.annotations.NoGatekeeper;
import com.gwtplatform.mvp.client.annotations.ProxyCodeSplit;
import com.gwtplatform.mvp.client.proxy.ProxyPlace;

import javax.inject.Inject;

/**
 * Presenter of the login, implementation.
 *
 * @author Manfred Tremmel
 *
 */
public class LoginPresenter extends Presenter<LoginPresenter.MyView, LoginPresenter.MyProxy> {

  public interface MyView extends EditorWithErrorHandling<LoginPresenter, LoginData> {
  }

  @ProxyCodeSplit
  @NameToken(NameTokens.LOGIN)
  @NoGatekeeper
  public interface MyProxy extends ProxyPlace<LoginPresenter> {
  }

  private final Session session;

  private final LoginConstants constants;

  private final RestDispatch dispatcher;
  private final UserRestService userService;

  /**
   * user data to remember.
   */
  private final LoginData loginData;

  /**
   * presenter with injected parameters.
   */
  @Inject
  public LoginPresenter(final EventBus peventBus, final LoginPresenter.MyView pview,
      final MyProxy pproxy, final RestDispatch pdispatcher, final UserRestService puserService,
      final Session psession, final LoginConstants pconstants) {
    super(peventBus, pview, pproxy, BasePagePresenter.SLOT_MAIN_CONTENT);
    this.dispatcher = pdispatcher;
    this.userService = puserService;
    this.session = psession;
    this.constants = pconstants;
    this.loginData = new LoginData();
    this.getView().setPresenter(this);
    this.getView().fillForm(this.loginData);
  }

  @Override
  protected void onReveal() {
    super.onReveal();
    this.loginData.clear();
    this.getView().fillForm(this.loginData);
    Scheduler.get().scheduleDeferred(new ScheduledCommand() {
      @Override
      public void execute() {
        LoginPresenter.this.getView().setFocusOnFirstWidget();
      }
    });
  }

  /**
   * try to login.
   */
  public final void tryToLogin() {
    this.dispatcher.execute(
        this.userService.login(this.loginData.getUserName(), this.loginData.getPassword()),
        new AsyncCallback<UserData>() {

          @Override
          public void onFailure(final Throwable caught) {
            LoginPresenter.this.getView()
                .showMessage(LoginPresenter.this.constants.messageLoginError());
          }

          @Override
          public void onSuccess(final UserData presult) {
            LoginPresenter.this.session.setUser(presult);
          }
        });
  }
}

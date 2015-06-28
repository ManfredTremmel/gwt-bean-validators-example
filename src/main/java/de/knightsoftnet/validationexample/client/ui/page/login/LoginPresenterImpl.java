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

import de.knightsoftnet.validationexample.client.ClientFactoryInterface;
import de.knightsoftnet.validationexample.client.JsHelper;
import de.knightsoftnet.validationexample.client.ui.basepage.AbstractPresenter;
import de.knightsoftnet.validationexample.shared.models.LoginData;
import de.knightsoftnet.validationexample.shared.models.UserData;
import de.knightsoftnet.validators.shared.exceptions.ValidationException;

import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.RunAsyncCallback;
import com.google.gwt.core.client.Scheduler;
import com.google.gwt.core.client.Scheduler.ScheduledCommand;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.AcceptsOneWidget;

/**
 * Activity/Presenter of the login, implementation.
 *
 * @author Manfred Tremmel
 *
 */
public class LoginPresenterImpl extends AbstractPresenter implements LoginPresenterInterface {

  /**
   * user data to remember.
   */
  private final LoginData loginData;

  /**
   * place to remember.
   */
  private final LoginPlace place;

  /**
   * view of the page.
   */
  private LoginViewInterface view;

  /**
   * the constructor of the activity/presenter.
   *
   * @param pplace place of this page
   * @param pclientFactory client factory
   */
  public LoginPresenterImpl(final LoginPlace pplace, final ClientFactoryInterface pclientFactory) {
    super(pclientFactory);
    this.place = pplace;
    this.loginData = new LoginData();
  }

  @Override
  public final void start(final AcceptsOneWidget ppanel, final EventBus peventBus) {
    GWT.runAsync(new RunAsyncCallback() {

      @Override
      public void onSuccess() {
        LoginPresenterImpl.this.view = LoginPresenterImpl.this.getClientFactory().getLoginView();
        LoginPresenterImpl.this.view.setPresenter(LoginPresenterImpl.this);
        LoginPresenterImpl.this.view.fillForm(LoginPresenterImpl.this.loginData);
        ppanel.setWidget(LoginPresenterImpl.this.view.asWidget());
        Scheduler.get().scheduleDeferred(new ScheduledCommand() {
          @Override
          public void execute() {
            LoginPresenterImpl.this.view.setFocusOnFirstWidget();
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
  public final void tryToLogin() {
    this.getClientFactory().getLoginLogoutService().login(this.loginData,
        new AsyncCallback<UserData>() {
          @Override
          public void onFailure(final Throwable pcaught) {
            try {
              throw pcaught;
            } catch (final ValidationException e) {
              LoginPresenterImpl.this.getClientFactory().getLoginView().getDriver()
                  .setConstraintViolations(
                      e.getValidationErrorSet(LoginPresenterImpl.this.loginData));
            } catch (final Throwable e) {
              final LoginConstants constants = GWT.create(LoginConstants.class);
              LoginPresenterImpl.this.getClientFactory().getLoginView()
                  .showMessage(constants.messageLoginError());
            }
          }

          @Override
          public void onSuccess(final UserData presult) {
            if (presult == null) {
              final LoginConstants constants = GWT.create(LoginConstants.class);
              LoginPresenterImpl.this.getClientFactory().getLoginView()
                  .showMessage(constants.messageLoginError());
            } else {
              // loginData is ok, set it to client factory and also give the place to go to
              if (LoginPresenterImpl.this.place.getRedirectToken() == null) {
                LoginPresenterImpl.this.getClientFactory().setUser(presult, LoginPresenterImpl.this
                    .getClientFactory().getNavigationPlace().getFirstToken());
              } else {
                LoginPresenterImpl.this.getClientFactory().setUser(presult,
                    LoginPresenterImpl.this.place.getRedirectToken());
              }
            }
          }
        });
  }
}

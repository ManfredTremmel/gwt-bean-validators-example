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

import de.knightsoftnet.validationexample.client.CurrentSession;
import de.knightsoftnet.validationexample.client.ui.basepage.BasePagePresenter;
import de.knightsoftnet.validationexample.client.ui.navigation.NameTokens;
import de.knightsoftnet.validationexample.shared.models.LoginData;
import de.knightsoftnet.validationexample.shared.models.UserData;
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
 * Activity/Presenter of the login, implementation.
 *
 * @author Manfred Tremmel
 *
 */
public class LoginPresenter extends Presenter<LoginPresenter.MyView, LoginPresenter.MyProxy> {

  public interface MyView extends View, Editor<LoginData> {
    /**
     * set a reference to the presenter/activity.
     *
     * @param ppresenter reference to set
     */
    void setPresenter(LoginPresenter ppresenter);

    /**
     * fill the form with data.
     *
     * @param puser data to fill into the form
     */
    void fillForm(LoginData puser);

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
  @NameToken(NameTokens.LOGIN)
  @NoGatekeeper
  public interface MyProxy extends ProxyPlace<LoginPresenter> {
  }

  private final CurrentSession currentSession;

  private final LoginLogoutRemoteServiceAsync service;

  /**
   * user data to remember.
   */
  private final LoginData loginData;

  /**
   * presenter with injected parameters.
   *
   * @param peventBus event bus
   * @param pview view of the page
   * @param pproxy proxy of the page
   * @param pservice login/logout remote service
   * @param pcurrentSession user container
   */
  @Inject
  public LoginPresenter(final EventBus peventBus, final LoginPresenter.MyView pview,
      final MyProxy pproxy, final LoginLogoutRemoteServiceAsync pservice,
      final CurrentSession pcurrentSession) {
    super(peventBus, pview, pproxy, BasePagePresenter.SLOT_MAIN_CONTENT);
    this.service = pservice;
    this.currentSession = pcurrentSession;
    this.loginData = new LoginData();
    this.getView().setPresenter(this);
    this.getView().fillForm(this.loginData);
  }

  @Override
  protected void onReveal() {
    super.onReveal();
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
    this.service.login(this.loginData, new AsyncCallback<UserData>() {
      @Override
      public void onFailure(final Throwable pcaught) {
        try {
          throw pcaught;
        } catch (final ValidationException e) {
          LoginPresenter.this.getView().setConstraintViolations(
              e.getValidationErrorSet(LoginPresenter.this.loginData));
        } catch (final Throwable e) {
          final LoginConstants constants = GWT.create(LoginConstants.class);
          LoginPresenter.this.getView().showMessage(constants.messageLoginError());
        }
      }

      @Override
      public void onSuccess(final UserData presult) {
        if (presult == null) {
          final LoginConstants constants = GWT.create(LoginConstants.class);
          LoginPresenter.this.getView().showMessage(constants.messageLoginError());
        } else {
          // loginData is ok, set it to client factory and also give the place to go to
          LoginPresenter.this.currentSession.setUser(presult);
        }
      }
    });
  }
}

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

package de.knightsoftnet.validationexample.client.ui.page.logout;

import de.knightsoftnet.gwtp.spring.client.rest.helper.RestCallbackBuilder;
import de.knightsoftnet.gwtp.spring.client.session.Session;
import de.knightsoftnet.navigation.client.ui.basepage.AbstractBasePagePresenter;
import de.knightsoftnet.validationexample.client.services.UserRestService;
import de.knightsoftnet.validationexample.client.ui.navigation.NameTokens;

import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.dispatch.rest.client.RestDispatch;
import com.gwtplatform.mvp.client.Presenter;
import com.gwtplatform.mvp.client.View;
import com.gwtplatform.mvp.client.annotations.NameToken;
import com.gwtplatform.mvp.client.annotations.ProxyCodeSplit;
import com.gwtplatform.mvp.client.proxy.ProxyPlace;

import javax.inject.Inject;

/**
 * Presenter of the logout, implementation.
 *
 * @author Manfred Tremmel
 *
 */
public class LogoutPresenter extends Presenter<LogoutPresenter.MyView, LogoutPresenter.MyProxy> {

  public interface MyView extends View {
  }

  @ProxyCodeSplit
  @NameToken(NameTokens.LOGOUT)
  public interface MyProxy extends ProxyPlace<LogoutPresenter> {
  }

  private final RestDispatch dispatcher;
  private final UserRestService userService;

  private final Session session;

  /**
   * constructor injecting parameters.
   */
  @Inject
  public LogoutPresenter(final EventBus peventBus, final MyView pview, final MyProxy pproxy,
      final RestDispatch pdispatcher, final UserRestService puserService, final Session psession) {
    super(peventBus, pview, pproxy, AbstractBasePagePresenter.SLOT_MAIN_CONTENT);
    dispatcher = pdispatcher;
    userService = puserService;
    session = psession;
  }

  @Override
  protected void onReveal() {
    super.onReveal();
    dispatcher.execute(userService.logout(), RestCallbackBuilder.build(//
        presult -> session.readSessionData(), //
        pfailure -> session.readSessionData()));
  }
}

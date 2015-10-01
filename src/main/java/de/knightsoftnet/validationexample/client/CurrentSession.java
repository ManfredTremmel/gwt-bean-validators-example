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

package de.knightsoftnet.validationexample.client;

import de.knightsoftnet.validationexample.client.event.ChangeUserEvent;
import de.knightsoftnet.validationexample.client.ui.page.login.LoginLogoutRemoteServiceAsync;
import de.knightsoftnet.validationexample.shared.models.UserData;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.HasRpcToken;
import com.google.gwt.user.client.rpc.ServiceDefTarget;
import com.google.gwt.user.client.rpc.XsrfToken;
import com.google.gwt.user.client.rpc.XsrfTokenServiceAsync;
import com.google.web.bindery.event.shared.EventBus;

import org.apache.commons.lang3.ObjectUtils;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class CurrentSession {
  private final EventBus eventBus;
  private final XsrfTokenServiceAsync xsrf;
  private final LoginLogoutRemoteServiceAsync service;

  private UserData user;

  /**
   * constructor with injected parameters.
   *
   * @param peventBus event bus
   * @param pxsrf cross site reforgery token service
   * @param pservice login logout service
   */
  @Inject
  public CurrentSession(final EventBus peventBus, final XsrfTokenServiceAsync pxsrf,
      final LoginLogoutRemoteServiceAsync pservice) {
    super();
    this.eventBus = peventBus;
    this.xsrf = pxsrf;
    this.service = pservice;
    ((ServiceDefTarget) this.xsrf).setServiceEntryPoint(GWT.getModuleBaseURL() + "xsrf");
  }

  /**
   * read session data.
   */
  public void readSessionData() {
    // first create a session (if not already exists)
    this.service.createSession(new AsyncCallback<Void>() {
      @Override
      public void onFailure(final Throwable pcaught) {
        throw new RuntimeException(pcaught);
      }

      @Override
      public void onSuccess(final Void presult) {
        // now we need a xsrf token to protect our connection
        CurrentSession.this.xsrf.getNewXsrfToken(new AsyncCallback<XsrfToken>() {
          @Override
          public void onSuccess(final XsrfToken ptoken) {
            ((HasRpcToken) CurrentSession.this.service).setRpcToken(ptoken);

            // look if we do already have a valid user in the session
            CurrentSession.this.service.getSessionUser(new AsyncCallback<UserData>() {

              @Override
              public void onFailure(final Throwable pcaught) {
                throw new RuntimeException(pcaught);
              }

              @Override
              public void onSuccess(final UserData presult) {
                if (presult != null) {
                  CurrentSession.this.setUser(presult);
                }
              }
            });
          }

          @Override
          public void onFailure(final Throwable pcaught) {
            throw new RuntimeException(pcaught);
          }
        });
      }
    });
  }

  public UserData getUser() {
    return this.user;
  }

  /**
   * set user and fire change user event when user changes.
   *
   * @param puser user data
   */
  public void setUser(final UserData puser) {
    final boolean changed = !ObjectUtils.equals(puser, this.user);
    this.user = puser;
    if (changed) {
      this.eventBus.fireEvent(new ChangeUserEvent(this.user));
    }
  }
}

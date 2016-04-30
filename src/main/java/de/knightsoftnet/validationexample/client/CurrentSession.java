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

import de.knightsoftnet.navigation.client.session.AbstractSession;
import de.knightsoftnet.validationexample.client.services.UserRestService;
import de.knightsoftnet.validationexample.shared.models.UserData;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.dispatch.rest.client.RestDispatch;

import org.apache.commons.lang3.BooleanUtils;

import javax.inject.Inject;

public class CurrentSession extends AbstractSession {
  private final RestDispatch dispatcher;
  private final UserRestService userService;

  /**
   * constructor with injected parameters.
   *
   * @param peventBus event bus
   */
  @Inject
  public CurrentSession(final EventBus peventBus, final RestDispatch pdispatcher,
      final UserRestService puserService) {
    super(peventBus);
    this.dispatcher = pdispatcher;
    this.userService = puserService;
  }

  /**
   * read session data.
   */
  @Override
  public void readSessionData() {
    this.dispatcher.execute(this.userService.isCurrentUserLoggedIn(), new AsyncCallback<Boolean>() {

      @Override
      public void onFailure(final Throwable pcaught) {
        GWT.log("Error checking if user is logged in", pcaught);
      }

      @Override
      public void onSuccess(final Boolean presult) {
        if (BooleanUtils.isTrue(presult)) {
          // we do have a logged in user, read it
          CurrentSession.this.dispatcher.execute(CurrentSession.this.userService.getCurrentUser(),
              new AsyncCallback<UserData>() {
                @Override
                public void onFailure(final Throwable pcaught) {
                  GWT.log("Error reading session user", pcaught);
                }

                @Override
                public void onSuccess(final UserData presult) {
                  CurrentSession.this.setUser(presult);
                }
              });
        } else {
          CurrentSession.this.setUser(null);
        }
      }

    });
  }
}

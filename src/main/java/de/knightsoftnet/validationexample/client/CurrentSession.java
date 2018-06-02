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

import de.knightsoftnet.gwtp.spring.client.GwtpSpringSession;
import de.knightsoftnet.validationexample.client.services.UserRestService;
import de.knightsoftnet.validationexample.shared.models.UserData;

import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.dispatch.rest.client.RestDispatch;

import javax.inject.Inject;

/**
 * session implementation based on GwtpSpringSession.
 *
 * @author Manfred Tremmel
 *
 */
public class CurrentSession extends GwtpSpringSession<UserData> {

  /**
   * constructor with injected parameters.
   *
   * @param peventBus event bus
   */
  @Inject
  public CurrentSession(final EventBus peventBus, final RestDispatch pdispatcher,
      final UserRestService puserService) {
    super(peventBus, pdispatcher, puserService);
  }
}

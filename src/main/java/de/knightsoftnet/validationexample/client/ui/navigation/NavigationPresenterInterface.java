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

package de.knightsoftnet.validationexample.client.ui.navigation;

import de.knightsoftnet.validationexample.client.ClientFactoryInterface;

import com.google.gwt.activity.shared.Activity;
import com.google.web.bindery.event.shared.SimpleEventBus;

/**
 * Activity/Presenter of the base page interface.
 *
 * @author Manfred Tremmel
 *
 */
public interface NavigationPresenterInterface extends Activity {
  /**
   * go to another place by given navigation entry.
   *
   * @param pnavigationEntry the navigation entry which represents the place to go to
   */
  void goToNavigationEntry(final NavigationEntryInterface pnavigationEntry);

  /**
   * get the client factory.
   *
   * @return ClientFactoryInterface
   */
  ClientFactoryInterface getClientFactory();

  /**
   * get event bus.
   *
   * @return event bus
   */
  SimpleEventBus getEventBus();

  /**
   * get the current place.
   *
   * @return place data
   */
  NavigationPlace getPlace();
}

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

package de.knightsoftnet.validationexample.client.ui.basepage;

import com.google.gwt.user.client.ui.HasOneWidget;
import com.google.gwt.user.client.ui.IsWidget;

/**
 * View of the BasePage, Interface.
 *
 * @author Manfred Tremmel
 *
 */
public interface BasePageViewInterface extends IsWidget {
  /**
   * container where to place content.
   *
   * @return content container
   */
  HasOneWidget getContentContainer();

  /**
   * container where to place navigation.
   *
   * @return navigation container
   */
  HasOneWidget getNavigationContainer();

  /**
   * set a reference to the presenter/activity.
   *
   * @param pbasePagePresenterInterface reference to set
   */
  void setPresenter(BasePagePresenterInterface pbasePagePresenterInterface);
}

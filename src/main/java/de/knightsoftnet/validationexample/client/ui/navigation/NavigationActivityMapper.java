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
import com.google.gwt.activity.shared.ActivityMapper;
import com.google.gwt.place.shared.Place;

/**
 * activity manager for the validator test app.
 *
 * @author Manfred Tremmel
 *
 */
public class NavigationActivityMapper implements ActivityMapper {
  /**
   * client factory to remember.
   */
  private final ClientFactoryInterface clientFactory;

  /**
   * default presenter.
   */
  private final NavigationPresenterImpl presenter;

  /**
   * constructor.
   *
   * @param pclientFactory client factory to use
   */
  public NavigationActivityMapper(final ClientFactoryInterface pclientFactory) {
    this.clientFactory = pclientFactory;
    this.presenter =
        new NavigationPresenterImpl((NavigationPlace) this.clientFactory.getNavigationPlace(),
            pclientFactory);
  }

  @Override
  public final Activity getActivity(final Place pplace) {
    final String token = this.clientFactory.getPlaceHistoryMapper().getToken(pplace);
    final NavigationEntryInterface navEntry =
        this.clientFactory.getNavigationPlace().getNavigationForToken(token);
    if (navEntry != null) {
      // remember given parameter as last selected
      navEntry.setTokenDynamic(token);
      this.presenter.getPlace().setActiveNavigationEntryInterface(navEntry);
      if (navEntry.getParentEntry() == null) {
        this.presenter.getPlace().setNavigationList(
            this.presenter.getPlace().getFullNavigationList());
      } else {
        this.presenter.getPlace().setNavigationList(
            ((NavigationEntryFolder) navEntry.getParentEntry()).getSubEntries());
      }
    }
    return this.presenter;
  }
}

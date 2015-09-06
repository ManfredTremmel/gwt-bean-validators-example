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

import de.knightsoftnet.validationexample.shared.LoggedInAllowedRights;
import de.knightsoftnet.validationexample.shared.LoggedOutAllowedRights;
import de.knightsoftnet.validationexample.shared.UserRightsInterface;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.safehtml.shared.SafeHtmlUtils;
import com.gwtplatform.mvp.client.proxy.PlaceManager;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

/**
 * place for displaying validationexample.
 *
 * @author Manfred Tremmel
 *
 */
public class NavigationPlace extends AbstractNavigationPlace {

  /**
   * gate keeper for pages which are only visible when user is logged in.
   */
  private final UserRightsInterface loggedInGatekeeper;
  /**
   * gate keeper for pages which are only visible when user is logged out.
   */
  private final UserRightsInterface loggedOutGatekeeper;

  /**
   * default constructor.
   */
  @Inject
  public NavigationPlace(final PlaceManager pplaceManager) {
    super(pplaceManager);
    this.loggedInGatekeeper = new LoggedInAllowedRights();
    this.loggedOutGatekeeper = new LoggedOutAllowedRights();
  }

  @Override
  protected final List<NavigationEntryInterface> buildNavigation() {
    final List<NavigationEntryInterface> navigationEntries =
        new ArrayList<NavigationEntryInterface>();
    final NavigationConstants navigationConstants = GWT.create(NavigationConstants.class);

    navigationEntries.add(new NavigationEntry(SafeHtmlUtils.fromString(navigationConstants
        .menuPostalAddress()), NameTokens.ADDRESS, null));
    navigationEntries.add(new NavigationEntry(SafeHtmlUtils.fromString(navigationConstants
        .menuSepa()), NameTokens.SEPA, null));

    navigationEntries.add(new NavigationEntry(SafeHtmlUtils.fromString(navigationConstants
        .menuSettings()), NameTokens.SETTINGS, null));

    navigationEntries.add(new NavigationEntry(SafeHtmlUtils.fromString(navigationConstants
        .menuLogin()), NameTokens.LOGIN, this.loggedOutGatekeeper));
    navigationEntries.add(new NavigationEntry(SafeHtmlUtils.fromString(navigationConstants
        .menuLogout()), NameTokens.LOGOUT, this.loggedInGatekeeper));

    final NavigationEntryFolder testFolder =
        new NavigationEntryFolder(SafeHtmlUtils.fromString(navigationConstants.menuTestFolder()),
            true);
    testFolder.addSubEntry(new NavigationEntry(SafeHtmlUtils.fromString(navigationConstants
        .menuSecret()), NameTokens.SECRET, this.loggedInGatekeeper));
    navigationEntries.add(testFolder);

    return navigationEntries;
  }
}

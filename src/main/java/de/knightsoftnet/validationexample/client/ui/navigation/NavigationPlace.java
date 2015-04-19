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

import com.google.gwt.core.client.GWT;
import com.google.gwt.safehtml.shared.SafeHtmlUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * place for displaying validationexample.
 *
 * @author Manfred Tremmel
 *
 */
public class NavigationPlace extends AbstractNavigationPlace {

  /**
   * constructor initializing app place history mapper.
   *
   * @param pplaceHistoryMapper the app place history mapper to set
   */
  public NavigationPlace(final AppPlaceHistoryMapper pplaceHistoryMapper) {
    super(pplaceHistoryMapper);
  }

  @Override
  protected final List<NavigationEntryInterface> buildNavigation() {
    final List<NavigationEntryInterface> navigationEntries =
        new ArrayList<NavigationEntryInterface>();

    final NavigationConstants navigationConstants = GWT.create(NavigationConstants.class);

    navigationEntries.add(new NavigationEntry(SafeHtmlUtils.fromString(navigationConstants
        .menuPostalAddress()), "address", super.placeHistoryMapper));
    navigationEntries.add(new NavigationEntry(SafeHtmlUtils.fromString(navigationConstants
        .menuSepa()), "sepa", super.placeHistoryMapper));

    navigationEntries.add(new NavigationEntry(SafeHtmlUtils.fromString(navigationConstants
        .menuSettings()), "settings", super.placeHistoryMapper));

    navigationEntries.add(new NavigationEntry(SafeHtmlUtils.fromString(navigationConstants
        .menuLogin()), "login", super.placeHistoryMapper));
    navigationEntries.add(new NavigationEntry(SafeHtmlUtils.fromString(navigationConstants
        .menuLogout()), "logout", super.placeHistoryMapper));

    final NavigationEntryFolder testFolder =
        new NavigationEntryFolder(SafeHtmlUtils.fromString(navigationConstants.menuTestFolder()),
            true);
    testFolder.addSubEntry(new NavigationEntry(SafeHtmlUtils.fromString(navigationConstants
        .menuSecret()), "secret", super.placeHistoryMapper));
    navigationEntries.add(testFolder);

    return navigationEntries;
  }
}

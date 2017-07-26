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

import de.knightsoftnet.navigation.client.gatekeepers.LoggedInGatekeeper;
import de.knightsoftnet.navigation.client.gatekeepers.LoggedOutGatekeeper;
import de.knightsoftnet.navigation.client.ui.navigation.AbstractNavigationStructure;
import de.knightsoftnet.navigation.client.ui.navigation.NavigationEntry;
import de.knightsoftnet.navigation.client.ui.navigation.NavigationEntryFolder;
import de.knightsoftnet.navigation.client.ui.navigation.NavigationEntryInterface;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.safehtml.shared.SafeHtmlUtils;
import com.google.web.bindery.event.shared.EventBus;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * definition of the navigation structure of this application, entries are secured by gatekeepers
 * and only entries where user has access are displayed, all other are hidden.
 *
 * @author Manfred Tremmel
 *
 */
@Singleton
public class MyNavigationStructure extends AbstractNavigationStructure {

  /**
   * gate keeper for pages which are only visible when user is logged in.
   */
  @Inject
  private LoggedInGatekeeper loggedInGatekeeper;
  /**
   * gate keeper for pages which are only visible when user is logged out.
   */
  @Inject
  private LoggedOutGatekeeper loggedOutGatekeeper;

  /**
   * default constructor.
   */
  @Inject
  public MyNavigationStructure(final EventBus peventBus) {
    super(peventBus);
  }

  @Override
  protected final List<NavigationEntryInterface> buildNavigation() {
    final List<NavigationEntryInterface> navigationEntries =
        new ArrayList<>();
    final NavigationConstants navigationConstants = GWT.create(NavigationConstants.class);

    navigationEntries
        .add(new NavigationEntry(SafeHtmlUtils.fromString(navigationConstants.menuPostalAddress()),
            NameTokens.ADDRESS, null));
    navigationEntries.add(new NavigationEntry(
        SafeHtmlUtils.fromString(navigationConstants.menuSepa()), NameTokens.SEPA, null));
    navigationEntries
        .add(new NavigationEntry(SafeHtmlUtils.fromString(navigationConstants.menuPhoneNumber()),
            NameTokens.PHONE_NUMBER, null));
    navigationEntries
        .add(new NavigationEntry(SafeHtmlUtils.fromString(navigationConstants.menuEmailList()),
            NameTokens.EMAIL_LIST, null));

    navigationEntries.add(new NavigationEntry(
        SafeHtmlUtils.fromString(navigationConstants.menuSettings()), NameTokens.SETTINGS, null));

    navigationEntries
        .add(new NavigationEntry(SafeHtmlUtils.fromString(navigationConstants.menuLogin()),
            NameTokens.SECRET + "/" + NameTokens.LOGIN, this.loggedOutGatekeeper));
    navigationEntries
        .add(new NavigationEntry(SafeHtmlUtils.fromString(navigationConstants.menuLogout()),
            NameTokens.LOGOUT, this.loggedInGatekeeper));

    final NavigationEntryFolder testFolder = new NavigationEntryFolder(
        SafeHtmlUtils.fromString(navigationConstants.menuTestFolder()), true);
    testFolder
        .addSubEntry(new NavigationEntry(SafeHtmlUtils.fromString(navigationConstants.menuSecret()),
            NameTokens.SECRET, this.loggedInGatekeeper));
    testFolder
        .addSubEntry(new NavigationEntry(SafeHtmlUtils.fromString(navigationConstants.menuPerson()),
            NameTokens.PERSON, this.loggedInGatekeeper));
    navigationEntries.add(testFolder);

    return navigationEntries;
  }
}

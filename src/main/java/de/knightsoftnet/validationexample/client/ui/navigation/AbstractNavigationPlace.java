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

import de.knightsoftnet.validationexample.client.mvp.AbstractCustomPlaceHistoryMapper;
import de.knightsoftnet.validationexample.shared.models.UserData;

import com.google.gwt.place.shared.Place;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * The <code>AbstractNavigationPlace</code> defines the methods which are used to handle
 * navigation/menu entries.
 *
 * @author Manfred Tremmel
 */
public abstract class AbstractNavigationPlace extends Place {
  /**
   * place history mapper.
   */
  protected final AppPlaceHistoryMapper placeHistoryMapper;

  /**
   * first place in navigation.
   */
  private NavigationEntryInterface firstEntry;

  /**
   * map to find navigation entries for places.
   */
  private final Map<String, NavigationEntryInterface> placeMap;

  /**
   * list of all navigation entries.
   */
  private List<NavigationEntryInterface> fullNavigationList;

  /**
   * list of active navigation entries (if there are not all displayed).
   */
  private List<NavigationEntryInterface> navigationList;

  /**
   * selected navigation entry.
   */
  private NavigationEntryInterface activeNavigationEntryInterface;

  /**
   * constructor initializing app place history mapper.
   *
   * @param pplaceHistoryMapper the app place history mapper to set
   */
  public AbstractNavigationPlace(final AppPlaceHistoryMapper pplaceHistoryMapper) {
    super();
    this.placeMap = new HashMap<String, NavigationEntryInterface>();
    this.placeHistoryMapper = pplaceHistoryMapper;

    this.buildVisibleNavigation(null);
  }

  /**
   * build the visible navigation entries.
   *
   * @param puser the user to build navigation for
   */
  public final void buildVisibleNavigation(final UserData puser) {
    this.fullNavigationList = this.recursiveGetEntries(this.buildNavigation(), puser);
    this.generateMapRecursive(this.fullNavigationList);
    this.navigationList = this.fullNavigationList;
    this.activeNavigationEntryInterface = this.getFirstEntry();
  }

  /**
   * build the navigation list.
   *
   * @return list of navigation entries
   */
  protected abstract List<NavigationEntryInterface> buildNavigation();

  /**
   * create map out of the navigation list.
   *
   * @param pnavigationEntries list of navigation entries
   */
  private void generateMapRecursive(final List<NavigationEntryInterface> pnavigationEntries) {
    for (final NavigationEntryInterface entryToAdd : pnavigationEntries) {
      if (entryToAdd.getMenuValue() != null && entryToAdd.getToken() != null) {
        if (this.firstEntry == null && entryToAdd.isDisplayable(null)) {
          this.firstEntry = entryToAdd;
        }
        if (!this.placeMap.containsKey(entryToAdd.getToken())) {
          this.placeMap.put(entryToAdd.getToken(), entryToAdd);
        }
      }
      if (entryToAdd instanceof NavigationEntryFolder) {
        this.generateMapRecursive(((NavigationEntryFolder) entryToAdd).getSubEntries());
      }
    }
  }

  /**
   * get all navigation entries that can be displayed by a given user.
   *
   * @param pnavigationEntries entries to test
   * @param puser the user to test
   * @return the navigationEntries
   */
  private List<NavigationEntryInterface> recursiveGetEntries(
      final List<NavigationEntryInterface> pnavigationEntries, final UserData puser) {
    if (pnavigationEntries == null) {
      return Collections.emptyList();
    }
    final List<NavigationEntryInterface> displayEntries =
        new ArrayList<NavigationEntryInterface>(pnavigationEntries.size());
    for (final NavigationEntryInterface entryToTest : pnavigationEntries) {
      if (entryToTest.isDisplayable(puser)) {
        if (entryToTest instanceof NavigationEntryFolder) {
          displayEntries.add(new NavigationEntryFolder(entryToTest.getMenuValue(), entryToTest
              .isOpenOnStartup(), this.recursiveGetEntries(
              ((NavigationEntryFolder) entryToTest).getSubEntries(), puser)));
        } else {
          displayEntries.add(entryToTest);
        }
      }
    }
    return displayEntries;
  }

  /**
   * get first entry.
   *
   * @return the firstEntry
   */
  public final NavigationEntryInterface getFirstEntry() {
    return this.firstEntry;
  }

  /**
   * get first token.
   *
   * @return the firstPlace
   */
  public final String getFirstToken() {
    return this.firstEntry.getToken();
  }

  /**
   * get full navigation list.
   *
   * @return the fullNavigationList
   */
  public final List<NavigationEntryInterface> getFullNavigationList() {
    return this.fullNavigationList;
  }

  /**
   * get navigation list.
   *
   * @return the navigationList
   */
  public final List<NavigationEntryInterface> getNavigationList() {
    return this.navigationList;
  }

  /**
   * set navigation list.
   *
   * @param pnavigationList the navigationList to set
   */
  public final void setNavigationList(final List<NavigationEntryInterface> pnavigationList) {
    this.navigationList = pnavigationList;
  }

  /**
   * get active navigation entry interface.
   *
   * @return the activeNavigationEntryInterface
   */
  public final NavigationEntryInterface getActiveNavigationEntryInterface() {
    return this.activeNavigationEntryInterface;
  }

  /**
   * set active navigation entry interface.
   *
   * @param pactiveNavigationEntryInterface the activeNavigationEntryInterface to set
   */
  public final void setActiveNavigationEntryInterface(
      final NavigationEntryInterface pactiveNavigationEntryInterface) {
    this.activeNavigationEntryInterface = pactiveNavigationEntryInterface;
  }

  /**
   * get navigation entry for given token.
   *
   * @param ptoken the token of the place to get navigation entry for
   * @return navigation entry for place or null if none found
   */
  public final NavigationEntryInterface getNavigationForToken(final String ptoken) {
    NavigationEntryInterface entry = this.placeMap.get(ptoken);
    if (entry == null
        && ptoken != null
        && (!ptoken.endsWith(AbstractCustomPlaceHistoryMapper.DELIMITER) || ptoken
            .lastIndexOf(AbstractCustomPlaceHistoryMapper.DELIMITER.charAt(0)) != ptoken
            .indexOf(AbstractCustomPlaceHistoryMapper.DELIMITER.charAt(0)))) {
      final int posSeparator = ptoken.indexOf(AbstractCustomPlaceHistoryMapper.DELIMITER.charAt(0));
      if (posSeparator > 0) {
        entry = this.placeMap.get(ptoken.substring(0, posSeparator));
      }
    }
    return entry;
  }
}

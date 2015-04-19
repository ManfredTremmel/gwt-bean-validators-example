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

import de.knightsoftnet.validationexample.shared.models.UserData;

import com.google.gwt.safehtml.shared.SafeHtml;

import org.apache.commons.lang3.ObjectUtils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * The <code>NavigationEntryFolder</code> defines one menu folders with subentries.
 *
 * @author Manfred Tremmel
 */
public class NavigationEntryFolder implements NavigationEntryInterface {
  /**
   * entry for the menu, this one my contain text or maybe even pictures.
   */
  private final SafeHtml menuValue;

  /**
   * list of sub menu entries.
   */
  private final List<NavigationEntryInterface> subEntries;

  /**
   * parent entry of this entry (null if we are on top level).
   */
  private NavigationEntryInterface parentEntry;

  /**
   * menu entry is open on startup.
   */
  private final boolean openOnStartup;

  /**
   * constructor for menu folders.
   *
   * @param pmenuValue menu value
   * @param popenOnStartup this folder is open on startup
   */
  public NavigationEntryFolder(final SafeHtml pmenuValue, final boolean popenOnStartup) {
    super();
    menuValue = pmenuValue;
    subEntries = new ArrayList<NavigationEntryInterface>();
    openOnStartup = popenOnStartup;
    parentEntry = null;
  }

  /**
   * constructor for menu folders.
   *
   * @param pmenuValue menu value
   * @param popenOnStartup this folder is open on startup
   * @param psubEntries a collection of subentries
   */
  public NavigationEntryFolder(final SafeHtml pmenuValue, final boolean popenOnStartup,
      final Collection<NavigationEntryInterface> psubEntries) {
    super();
    menuValue = pmenuValue;
    subEntries = new ArrayList<NavigationEntryInterface>(psubEntries);
    openOnStartup = popenOnStartup;
    parentEntry = null;
    if (subEntries != null && !subEntries.isEmpty()) {
      for (final NavigationEntryInterface subEntry : subEntries) {
        subEntry.setParentEntry(this);
      }
    }
  }

  /*
   * (non-Javadoc)
   *
   * @see
   * de.knightsoftnet.validationexample.client.navigation.NavigationEntryInterface#getMenuValue()
   */
  @Override
  public final SafeHtml getMenuValue() {
    return menuValue;
  }

  /*
   * (non-Javadoc)
   *
   * @see de.knightsoftnet.validationexample.client.navigation.NavigationEntryInterface#getToken()
   */
  @Override
  public final String getToken() {
    return null;
  }

  /*
   * (non-Javadoc)
   *
   * @see
   * de.knightsoftnet.validationexample.client.navigation.NavigationEntryInterface#getFullToken()
   */
  @Override
  public final String getFullToken() {
    return null;
  }

  /*
   * (non-Javadoc)
   *
   * @see
   * de.knightsoftnet.validationexample.client.navigation.NavigationEntryInterface#getTokenDynamic()
   */
  @Override
  public final String getTokenDynamic() {
    return null;
  }

  /*
   * (non-Javadoc)
   *
   * @see
   * de.knightsoftnet.validationexample.client.navigation.NavigationEntryInterface#setTokenDynamic
   * (java .lang.String)
   */
  @Override
  public final void setTokenDynamic(final String ptokenDynamic) {
    // nothing to do
  }

  /**
   * get list of sub entries.
   *
   * @return the subEntries
   */
  public final List<NavigationEntryInterface> getSubEntries() {
    return Collections.unmodifiableList(subEntries);
  }

  /**
   * add a menu sub entry.
   *
   * @param psubEntry the sub entry to add
   */
  public final void addSubEntry(final NavigationEntryInterface psubEntry) {
    psubEntry.setParentEntry(this);
    subEntries.add(psubEntry);
  }

  /**
   * add a menu sub entries.
   *
   * @param psubEntries the sub entries to add
   */
  public final void addSubEntries(final Collection<NavigationEntryInterface> psubEntries) {
    if (psubEntries != null && !psubEntries.isEmpty()) {
      subEntries.addAll(psubEntries);
      for (final NavigationEntryInterface subEntry : subEntries) {
        subEntry.setParentEntry(this);
      }
    }
  }

  /*
   * (non-Javadoc)
   *
   * @see
   * de.knightsoftnet.validationexample.client.navigation.NavigationEntryInterface#getParentEntry()
   */
  @Override
  public final NavigationEntryInterface getParentEntry() {
    return parentEntry;
  }

  /*
   * (non-Javadoc)
   *
   * @see
   * de.knightsoftnet.validationexample.client.navigation.NavigationEntryInterface#setParentEntry
   * (de. knightsoftnet.validationexample.client .navigation.NavigationEntryInterface)
   */
  @Override
  public final void setParentEntry(final NavigationEntryInterface pparentEntry) {
    parentEntry = pparentEntry;
  }

  /*
   * (non-Javadoc)
   *
   * @see
   * de.knightsoftnet.validationexample.client.navigation.NavigationEntryInterface#isOpenOnStartup()
   */
  @Override
  public final boolean isOpenOnStartup() {
    return openOnStartup;
  }

  /*
   * (non-Javadoc)
   *
   * @see
   * de.knightsoftnet.validationexample.client.navigation.NavigationEntryInterface#isDisplayable(de.
   * knightsoftnet.validationexample.shared .UserDataInterface)
   */
  @Override
  public final boolean isDisplayable(final UserData puser) {
    boolean display = false;
    // one of the sub entries has to be displayable
    for (final NavigationEntryInterface subEntry : subEntries) {
      if (subEntry.isDisplayable(puser)) {
        display = true;
        break;
      }
    }
    return display;
  }

  @Override
  public final int hashCode() {
    return ObjectUtils.hashCode(menuValue);
  }

  @Override
  public final boolean equals(final Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null) {
      return false;
    }
    if (this.getClass() != obj.getClass()) {
      return false;
    }
    final NavigationEntryFolder other = (NavigationEntryFolder) obj;
    return Objects.equals(menuValue, other.menuValue);
  }
}

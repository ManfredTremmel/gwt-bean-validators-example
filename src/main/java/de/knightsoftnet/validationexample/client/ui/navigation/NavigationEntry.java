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
import de.knightsoftnet.validationexample.shared.UserRightsInterface;

import com.google.gwt.place.shared.Place;
import com.google.gwt.safehtml.shared.SafeHtml;

import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.Objects;

/**
 * The <code>NavigationEntry</code> defines one menu entry.
 * 
 * @author Manfred Tremmel
 */
public class NavigationEntry implements NavigationEntryInterface {
  /**
   * entry for the menu, this one my contain text or maybe even pictures.
   */
  private final SafeHtml menuValue;

  /**
   * the token to create the place.
   */
  private final String token;

  /**
   * dynamic part of the token.
   */
  private String tokenDynamic;

  /**
   * parent entry of this entry (null if we are on top level).
   */
  private NavigationEntryInterface parentEntry;

  /**
   * menu entry is open on startup.
   */
  private final boolean openOnStartup;

  /**
   * place history mapper.
   */
  private final AppPlaceHistoryMapper placeHistoryMapper;

  /**
   * constructor for menu entries.
   *
   * @param pmenuValue menu value
   * @param ptoken token to set
   * @param pplaceHistoryMapper place history mapper to set
   */
  public NavigationEntry(final SafeHtml pmenuValue, final String ptoken,
      final AppPlaceHistoryMapper pplaceHistoryMapper) {
    super();
    menuValue = pmenuValue;
    token = ptoken;
    tokenDynamic = null;
    placeHistoryMapper = pplaceHistoryMapper;
    openOnStartup = true;
    parentEntry = null;
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
    return token;
  }

  /*
   * (non-Javadoc)
   *
   * @see
   * de.knightsoftnet.validationexample.client.navigation.NavigationEntryInterface#getFullToken()
   */
  @Override
  public final String getFullToken() {
    if (tokenDynamic == null) {
      return token;
    } else {
      return token + tokenDynamic;
    }
  }

  /*
   * (non-Javadoc)
   *
   * @see
   * de.knightsoftnet.validationexample.client.navigation.NavigationEntryInterface#getTokenDynamic()
   */
  @Override
  public final String getTokenDynamic() {
    return tokenDynamic;
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
    if (token == null || ptokenDynamic == null) {
      tokenDynamic = ptokenDynamic;
    } else {
      if (ptokenDynamic.equals(token)) {
        tokenDynamic = null;
      } else if (ptokenDynamic.startsWith(token)) {
        tokenDynamic = ptokenDynamic.substring(token.length());
      } else {
        tokenDynamic = ptokenDynamic;
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
    final Place place = placeHistoryMapper.getPlace(getFullToken());
    if (place instanceof UserRightsInterface
        && !((UserRightsInterface) place).isAllowedToSee(puser)) {
      return false;
    }
    return true;
  }

  @Override
  public final int hashCode() {
    return ObjectUtils.hashCodeMulti(menuValue, token);
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
    final NavigationEntry other = (NavigationEntry) obj;
    return Objects.equals(menuValue, other.menuValue) && StringUtils.equals(token, other.token);
  }
}

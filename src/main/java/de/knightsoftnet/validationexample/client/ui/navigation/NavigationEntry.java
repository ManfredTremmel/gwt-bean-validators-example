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

import de.knightsoftnet.validationexample.shared.UserRightsInterface;
import de.knightsoftnet.validationexample.shared.models.UserData;

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
    this.menuValue = pmenuValue;
    this.token = ptoken;
    this.tokenDynamic = null;
    this.placeHistoryMapper = pplaceHistoryMapper;
    this.openOnStartup = true;
    this.parentEntry = null;
  }

  @Override
  public final SafeHtml getMenuValue() {
    return this.menuValue;
  }

  @Override
  public final String getToken() {
    return this.token;
  }

  @Override
  public final String getFullToken() {
    if (this.tokenDynamic == null) {
      return this.token;
    } else {
      return this.token + this.tokenDynamic;
    }
  }

  @Override
  public final String getTokenDynamic() {
    return this.tokenDynamic;
  }

  @Override
  public final void setTokenDynamic(final String ptokenDynamic) {
    if (this.token == null || ptokenDynamic == null) {
      this.tokenDynamic = ptokenDynamic;
    } else {
      if (ptokenDynamic.equals(this.token)) {
        this.tokenDynamic = null;
      } else if (ptokenDynamic.startsWith(this.token)) {
        this.tokenDynamic = ptokenDynamic.substring(this.token.length());
      } else {
        this.tokenDynamic = ptokenDynamic;
      }
    }
  }

  @Override
  public final NavigationEntryInterface getParentEntry() {
    return this.parentEntry;
  }

  @Override
  public final void setParentEntry(final NavigationEntryInterface pparentEntry) {
    this.parentEntry = pparentEntry;
  }

  @Override
  public final boolean isOpenOnStartup() {
    return this.openOnStartup;
  }

  @Override
  public final boolean isDisplayable(final UserData puser) {
    final Place place = this.placeHistoryMapper.getPlace(this.getFullToken());
    return !(place instanceof UserRightsInterface && !((UserRightsInterface) place)
        .isAllowedToSee(puser));
  }

  @Override
  public final int hashCode() {
    return ObjectUtils.hashCodeMulti(this.menuValue, this.token);
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
    return Objects.equals(this.menuValue, other.menuValue)
        && StringUtils.equals(this.token, other.token);
  }
}

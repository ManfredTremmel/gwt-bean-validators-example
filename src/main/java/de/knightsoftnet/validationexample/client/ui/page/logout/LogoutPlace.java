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

package de.knightsoftnet.validationexample.client.ui.page.logout;

import de.knightsoftnet.validationexample.shared.models.UserData;
import de.knightsoftnet.validationexample.shared.UserRightsInterface;

import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceTokenizer;
import com.google.gwt.place.shared.Prefix;

import org.apache.commons.lang3.StringUtils;

/**
 * place for logout functionality.
 *
 * @author Manfred Tremmel
 *
 */
public class LogoutPlace extends Place implements UserRightsInterface {

  @Override
  public final boolean isAllowedToSee(final UserData puser) {
    return puser != null && StringUtils.isNotEmpty(puser.getUserName());
  }

  @Override
  public final boolean isAllowedToChange(final UserData puser) {
    return this.isAllowedToSee(puser);
  }

  /**
   * tokenizer for the place.
   *
   * @author Manfred Tremmel
   *
   */
  @Prefix("logout")
  public static class Tokenizer implements PlaceTokenizer<LogoutPlace> {

    @Override
    public final LogoutPlace getPlace(final String token) {
      return new LogoutPlace();
    }

    @Override
    public final String getToken(final LogoutPlace place) {
      return "";
    }
  }
}

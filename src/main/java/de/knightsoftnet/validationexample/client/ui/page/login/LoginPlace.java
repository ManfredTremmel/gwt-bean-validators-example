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

package de.knightsoftnet.validationexample.client.ui.page.login;

import de.knightsoftnet.validationexample.shared.UserRightsInterface;
import de.knightsoftnet.validationexample.shared.models.UserData;

import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceTokenizer;
import com.google.gwt.place.shared.Prefix;

/**
 * place for login functionality.
 *
 * @author Manfred Tremmel
 *
 */
public class LoginPlace extends Place implements UserRightsInterface {
  /**
   * token of the page to redirect to after login.
   */
  private String redirectToken;

  /**
   * default constructor.
   */
  public LoginPlace() {
    super();
  }

  /**
   * constructor initializing the fields.
   *
   * @param predirectToken token to set
   */
  public LoginPlace(final String predirectToken) {
    super();
    this.redirectToken = predirectToken;
  }

  /**
   * get redirect token.
   *
   * @return the redirectToken
   */
  public final String getRedirectToken() {
    return this.redirectToken;
  }

  /**
   * set redirect token.
   *
   * @param predirectToken the redirectToken to set
   */
  public final void setRedirectToken(final String predirectToken) {
    this.redirectToken = predirectToken;
  }

  @Override
  public final boolean isAllowedToSee(final UserData puser) {
    return puser == null || puser.getUserName() == null;
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
  @Prefix("login")
  public static class Tokenizer implements PlaceTokenizer<LoginPlace> {

    @Override
    public final LoginPlace getPlace(final String ptoken) {
      return new LoginPlace(ptoken);
    }

    @Override
    public final String getToken(final LoginPlace pplace) {
      if (pplace.getRedirectToken() == null) {
        return "";
      } else {
        return pplace.getRedirectToken();
      }
    }
  }
}

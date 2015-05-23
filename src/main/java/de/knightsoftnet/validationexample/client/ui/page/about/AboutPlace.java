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

package de.knightsoftnet.validationexample.client.ui.page.about;

import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceTokenizer;
import com.google.gwt.place.shared.Prefix;

/**
 * place for displaying about informations.
 *
 * @author Manfred Tremmel
 *
 */
public class AboutPlace extends Place {
  /**
   * token of the page to redirect after closing about info.
   */
  private String redirectToken;

  /**
   * default constructor.
   */
  public AboutPlace() {
    super();
  }

  /**
   * constructor initializing the fields.
   *
   * @param predirectToken token to set
   */
  public AboutPlace(final String predirectToken) {
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

  /**
   * tokenizer for the place.
   *
   * @author Manfred Tremmel
   *
   */
  @Prefix("about")
  public static class Tokenizer implements PlaceTokenizer<AboutPlace> {

    @Override
    public final AboutPlace getPlace(final String ptoken) {
      return new AboutPlace(ptoken);
    }

    @Override
    public final String getToken(final AboutPlace pplace) {
      if (pplace.getRedirectToken() == null) {
        return "";
      } else {
        return pplace.getRedirectToken();
      }
    }
  }
}

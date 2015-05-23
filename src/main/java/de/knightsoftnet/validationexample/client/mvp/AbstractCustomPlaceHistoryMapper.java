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

package de.knightsoftnet.validationexample.client.mvp;

import com.google.gwt.place.impl.AbstractPlaceHistoryMapper;
import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceTokenizer;

import org.apache.commons.lang3.StringUtils;

/**
 * The <code>AbstractCustomPlaceHistoryMapper</code> is a replacement for AbstractPlaceHistoryMapper
 * using a different delimiter.
 *
 * @param <F> typ of the mapper
 * @author Manfred Tremmel
 */
public abstract class AbstractCustomPlaceHistoryMapper<F> extends AbstractPlaceHistoryMapper<F> {
  /**
   * delimiter used between prefix and token.
   */
  public static final String DELIMITER = "/";

  /**
   * custom prefix and token class using different delimiter.
   */
  public static class CustomPrefixAndToken extends PrefixAndToken {
    /**
     * constructor filling prefix and token.
     *
     * @param pprefix prefix to use
     * @param ptoken token to use
     */
    public CustomPrefixAndToken(final String pprefix, final String ptoken) {
      super(pprefix, ptoken);
      assert pprefix != null && !pprefix.contains(DELIMITER);
    }

    @Override
    public final String toString() {
      if (StringUtils.isEmpty(this.prefix)) {
        return this.token;
      }
      if (StringUtils.isEmpty(this.token)) {
        return this.prefix;
      }
      return this.prefix + DELIMITER + this.token;
    }

  }

  @Override
  public final Place getPlace(final String ptoken) {
    final int colonAt = ptoken.indexOf(DELIMITER);
    String initial;
    String rest;
    if (colonAt >= 0) {
      initial = ptoken.substring(0, colonAt);
      rest = ptoken.substring(colonAt + 1);
    } else {
      initial = ptoken;
      rest = "";
    }

    final PlaceTokenizer<?> tokenizer = this.getTokenizer(initial);
    if (tokenizer != null) {
      return tokenizer.getPlace(rest);
    }
    return null;
  }

  /**
   * get prefix and token.
   *
   * @param pplace what needs tokenizing
   * @return the token, or null
   */
  @Override
  protected abstract PrefixAndToken getPrefixAndToken(Place pplace);

  /**
   * get tokenizer.
   *
   * @param pprefix the prefix found on the history token
   * @return the PlaceTokenizer registered with that token, or null
   */
  @Override
  protected abstract PlaceTokenizer<?> getTokenizer(String pprefix);
}

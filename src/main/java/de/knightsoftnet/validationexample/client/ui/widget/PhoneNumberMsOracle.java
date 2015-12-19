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

package de.knightsoftnet.validationexample.client.ui.widget;

import de.knightsoftnet.validators.server.data.CreateClass;
import de.knightsoftnet.validators.shared.data.PhoneAreaCodeData;
import de.knightsoftnet.validators.shared.data.PhoneCountryCodeData;
import de.knightsoftnet.validators.shared.data.PhoneCountrySharedConstants;

import com.google.gwt.user.client.ui.SuggestOracle;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * suggest oracle of phone number ms suggest widget.
 *
 * @author Manfred Tremmel
 *
 */
public class PhoneNumberMsOracle extends SuggestOracle {

  private static final PhoneCountrySharedConstants COUNTRY_CONSTANTS =
      CreateClass.create(PhoneCountrySharedConstants.class);

  /**
   * default limit suggests.
   */
  private static final int LIMIT_DEFAULT = 20;

  @Override
  public final boolean isDisplayStringHTML() {
    return true;
  }

  @Override
  public final void requestSuggestions(final Request prequest, final Callback pcallback) {
    final SuggestOracle.Response response = new SuggestOracle.Response();
    if (prequest != null && StringUtils.isNotEmpty(prequest.getQuery())) {
      final int limit;
      if (prequest.getLimit() > 0) {
        limit = prequest.getLimit();
      } else {
        limit = LIMIT_DEFAULT;
      }

      final List<PhoneNumberMsItemSuggest> suggestions = new ArrayList<>(limit);
      final String cleanedPhoneNumber = this.cleanString(prequest.getQuery());
      PhoneCountryCodeData foundCounty = null;
      final List<PhoneCountryCodeData> possibleCountries = new ArrayList<>();
      for (final PhoneCountryCodeData countryCode : COUNTRY_CONSTANTS.countryCodeData()) {
        if (cleanedPhoneNumber.startsWith(countryCode.getCountryCode())) {
          foundCounty = countryCode;
          break;
        }
        if (countryCode.getCountryCode().startsWith(cleanedPhoneNumber)) {
          possibleCountries.add(countryCode);
        }
      }
      if (foundCounty == null) {
        // we don't have found a matching country, show possible countries
        for (final PhoneCountryCodeData country : possibleCountries) {
          suggestions.add(new PhoneNumberMsItemSuggest(country.getCountryCode(),
              country.getCountryCodeName(), null, null));
          if (suggestions.size() >= limit) {
            break;
          }
        }
      } else {
        // we do have a country, search for possible area codes
        final String phoneNumberWork =
            StringUtils.substring(cleanedPhoneNumber, foundCounty.getCountryCode().length());
        for (final PhoneAreaCodeData areaCode : foundCounty.getAreaCodeData()) {
          if (!areaCode.isRegEx() && areaCode.getAreaCode().startsWith(phoneNumberWork)) {
            suggestions.add(new PhoneNumberMsItemSuggest(foundCounty.getCountryCode(),
                foundCounty.getCountryCodeName(), areaCode.getAreaCode(), areaCode.getAreaName()));
            if (suggestions.size() >= limit) {
              break;
            }
          }
        }
      }
      response.setSuggestions(suggestions);
    }
    pcallback.onSuggestionsReady(prequest, response);
  }

  private String cleanString(final String pphoneNumber) {
    final StringBuilder cleanupString = new StringBuilder(pphoneNumber.length());
    for (final char character : pphoneNumber.toCharArray()) {
      if (character >= '0' && character <= '9') {
        cleanupString.append(character);
      }
    }
    return cleanupString.toString();
  }
}

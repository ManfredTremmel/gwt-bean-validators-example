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

import com.google.gwt.user.client.ui.SuggestOracle.Suggestion;

import org.apache.commons.lang3.StringUtils;

/**
 * suggest entry of phone number ms suggest widget.
 *
 * @author Manfred Tremmel
 *
 */
public class PhoneNumberMsItemSuggest implements Suggestion {

  private String countryCode;
  private String countryName;
  private String areaCode;
  private String areaName;

  /**
   * default constructor.
   */
  public PhoneNumberMsItemSuggest() {
    this(null, null, null, null);
  }

  /**
   * constructor initializing fields.
   *
   *
   * @param pcountryCode country code to set
   * @param pcountryName country name to set
   * @param pareaCode area code to set
   * @param pareaName area name to set
   */
  public PhoneNumberMsItemSuggest(final String pcountryCode, final String pcountryName,
      final String pareaCode, final String pareaName) {
    super();
    this.countryCode = pcountryCode;
    this.countryName = pcountryName;
    this.areaCode = pareaCode;
    this.areaName = pareaName;
  }

  @Override
  public String getDisplayString() {
    if (StringUtils.isEmpty(this.areaCode)) {
      return "+" + this.countryCode + " - " + this.countryName;
    }
    return "+" + this.countryCode + " (" + this.areaCode + ") - " + this.areaName;
  }

  @Override
  public String getReplacementString() {
    if (StringUtils.isEmpty(this.areaCode)) {
      return "+" + this.countryCode;
    }
    return "+" + this.countryCode + " (" + this.areaCode + ")";
  }

  protected final String getCountryCode() {
    return this.countryCode;
  }

  protected final void setCountryCode(final String pcountryCode) {
    this.countryCode = pcountryCode;
  }

  protected final String getCountryName() {
    return this.countryName;
  }

  protected final void setCountryName(final String pcountryName) {
    this.countryName = pcountryName;
  }

  protected final String getAreaCode() {
    return this.areaCode;
  }

  protected final void setAreaCode(final String pareaCode) {
    this.areaCode = pareaCode;
  }

  protected final String getAreaName() {
    return this.areaName;
  }

  protected final void setAreaName(final String pareaName) {
    this.areaName = pareaName;
  }
}

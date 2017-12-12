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

package de.knightsoftnet.validationexample.shared.models;

import de.knightsoftnet.mtwidgets.shared.models.CountryEnum;
import de.knightsoftnet.validators.shared.PhoneNumberValueRest;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * The <code>PhoneNumberData</code> class implements contains data of a phone number.
 *
 * @author Manfred Tremmel
 */
@PhoneNumberValueRest(fieldCountryCode = "countryCode", fieldPhoneNumber = "phoneNumber",
    allowDin5008 = false, allowE123 = false, allowUri = false, allowMs = true, allowCommon = false)
public class PhoneNumberData {

  @NotNull
  private CountryEnum countryCode;

  @NotEmpty
  private String phoneNumber;

  public final CountryEnum getCountryCode() {
    return this.countryCode;
  }

  public final void setCountryCode(final CountryEnum pcountryCode) {
    this.countryCode = pcountryCode;
  }

  public final String getPhoneNumber() {
    return this.phoneNumber;
  }

  public final void setPhoneNumber(final String pphoneNumber) {
    this.phoneNumber = pphoneNumber;
  }

  @Override
  public String toString() {
    return "PhoneNumberData [countryCode=" + this.countryCode + ", phoneNumber=" + this.phoneNumber
        + "]";
  }
}

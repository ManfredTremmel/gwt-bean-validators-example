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

import de.knightsoftnet.validators.shared.EmptyIfOtherIsNotEmpty;
import de.knightsoftnet.validators.shared.NotEmptyIfOtherIsEmpty;
import de.knightsoftnet.validators.shared.NotEmptyIfOtherIsNotEmpty;
import de.knightsoftnet.validators.shared.PostalCode;
import de.knightsoftnet.validators.shared.data.CountryEnum;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@EmptyIfOtherIsNotEmpty.List({
    // post office box and street can only be alternate filled, not both
    @EmptyIfOtherIsNotEmpty(field = "postOfficeBox", fieldCompare = "street"),
    @EmptyIfOtherIsNotEmpty(field = "street", fieldCompare = "postOfficeBox"),})
@NotEmptyIfOtherIsEmpty.List({
    // post office box or street must be alternate be filled
    @NotEmptyIfOtherIsEmpty(field = "street", fieldCompare = "postOfficeBox"),
    @NotEmptyIfOtherIsEmpty(field = "postOfficeBox", fieldCompare = "street"),})
@NotEmptyIfOtherIsNotEmpty.List({
    // street must be filled if street number is filled
    @NotEmptyIfOtherIsNotEmpty(field = "street", fieldCompare = "streetNumber"),
    // street number must be filled if street number additional is filled
    @NotEmptyIfOtherIsNotEmpty(field = "streetNumber", fieldCompare = "streetNumberAdditional"),})
@PostalCode(fieldCountryCode = "countryCode", fieldPostalCode = "postalCode")
public class PostalAddressData {

  /**
   * default length limit.
   */
  public static final int LENGTH_DEFAULT = 255;

  /**
   * length limit of the street name.
   */
  public static final int LENGTH_STREET_NUMBER = 10;

  /**
   * length limit postal code.
   */
  public static final int LENGTH_POSTAL_CODE = 10;

  /**
   * post office box.
   */
  @Size(max = LENGTH_DEFAULT)
  private String postOfficeBox;

  /**
   * street name.
   */
  @Size(max = LENGTH_DEFAULT)
  private String street;

  /**
   * street number.
   */
  @Size(max = LENGTH_STREET_NUMBER)
  private String streetNumber;

  /**
   * street number additional.
   */
  @Size(max = LENGTH_STREET_NUMBER)
  private String streetNumberAdditional;

  /**
   * extended address data (some thing like apartment).
   */
  @Size(max = LENGTH_DEFAULT)
  private String extended;

  /**
   * postal code (zip).
   */
  @Size(max = LENGTH_POSTAL_CODE)
  @NotEmpty
  private String postalCode;

  /**
   * localized name of the locality (city/town).
   */
  @Size(max = LENGTH_DEFAULT)
  @NotEmpty
  private String locality;

  /**
   * localized name of the region (state).
   */
  @Size(max = LENGTH_DEFAULT)
  private String region;

  /**
   * country code.
   */
  @NotNull
  private CountryEnum countryCode;


  @Valid
  private final List<EmailData> emailList;


  public PostalAddressData() {
    super();
    this.emailList = new ArrayList<>();
  }

  public String getPostOfficeBox() {
    return this.postOfficeBox;
  }

  public void setPostOfficeBox(final String ppostOfficeBox) {
    this.postOfficeBox = ppostOfficeBox;
  }

  public String getExtended() {
    return this.extended;
  }

  public void setExtended(final String pextended) {
    this.extended = pextended;
  }

  public String getStreet() {
    return this.street;
  }

  public void setStreet(final String pstreet) {
    this.street = pstreet;
  }

  public String getStreetNumber() {
    return this.streetNumber;
  }

  public void setStreetNumber(final String pstreetNumber) {
    this.streetNumber = pstreetNumber;
  }

  public String getStreetNumberAdditional() {
    return this.streetNumberAdditional;
  }

  public void setStreetNumberAdditional(final String pstreetNumberAdditional) {
    this.streetNumberAdditional = pstreetNumberAdditional;
  }

  public String getLocality() {
    return this.locality;
  }

  public void setLocality(final String plocality) {
    this.locality = plocality;
  }

  public String getRegion() {
    return this.region;
  }

  public void setRegion(final String pregion) {
    this.region = pregion;
  }

  public String getPostalCode() {
    return this.postalCode;
  }

  public void setPostalCode(final String ppostalCode) {
    this.postalCode = ppostalCode;
  }

  public CountryEnum getCountryCode() {
    return this.countryCode;
  }

  public void setCountryCode(final CountryEnum pcountryCode) {
    this.countryCode = pcountryCode;
  }

  public final List<EmailData> getEmailList() {
    return this.emailList;
  }

  /**
   * set email list.
   *
   * @param pemailList email list to set
   */
  public final void setEmailList(final List<EmailData> pemailList) {
    this.emailList.clear();
    if (pemailList != null && !this.emailList.isEmpty()) {
      this.emailList.addAll(pemailList);
    }
  }

  @Override
  public String toString() {
    return "PostalAddressData [postOfficeBox=" + this.postOfficeBox + ", street=" + this.street
        + ", streetNumber=" + this.streetNumber + ", streetNumberAdditional="
        + this.streetNumberAdditional + ", extended=" + this.extended + ", postalCode="
        + this.postalCode + ", locality=" + this.locality + ", region=" + this.region
        + ", countryCode=" + this.countryCode + "]";
  }
}

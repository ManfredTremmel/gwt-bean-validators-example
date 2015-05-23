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

import de.knightsoftnet.validators.shared.BankCountry;
import de.knightsoftnet.validators.shared.Bic;
import de.knightsoftnet.validators.shared.Iban;

import org.hibernate.validator.constraints.NotEmpty;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

/**
 * The <code>SepaData</code> class implements contains data of a sepa bank account.
 *
 * @author Manfred Tremmel
 */
@BankCountry
public class SepaData implements Serializable {
  /**
   * serial version uid.
   */
  private static final long serialVersionUID = 3006706955505446367L;

  @NotEmpty
  private String bankName;

  @NotEmpty
  private String accountOwner;

  @NotNull
  private CountryEnum countryCode;

  @Iban
  @NotEmpty
  private String iban;

  @Bic
  @NotEmpty
  private String bic;

  public String getBankName() {
    return this.bankName;
  }

  public void setBankName(final String pbankName) {
    this.bankName = pbankName;
  }

  public String getAccountOwner() {
    return this.accountOwner;
  }

  public void setAccountOwner(final String paccountOwner) {
    this.accountOwner = paccountOwner;
  }

  public CountryEnum getCountryCode() {
    return this.countryCode;
  }

  public void setCountryCode(final CountryEnum pcountryCode) {
    this.countryCode = pcountryCode;
  }

  public String getIban() {
    return this.iban;
  }

  public void setIban(final String piban) {
    this.iban = piban;
  }

  public String getBic() {
    return this.bic;
  }

  public void setBic(final String pbic) {
    this.bic = pbic;
  }
}

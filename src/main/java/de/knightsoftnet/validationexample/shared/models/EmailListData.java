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

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class EmailListData {

  /**
   * default length limit.
   */
  public static final int LENGTH_DEFAULT = 255;

  /**
   * first name.
   */
  @Size(max = LENGTH_DEFAULT)
  @NotEmpty
  private String firstname;

  /**
   * last name.
   */
  @Size(max = LENGTH_DEFAULT)
  @NotEmpty
  private String lastname;

  @Valid
  @NotNull
  @Size(min = 1)
  private List<EmailData> emailList;


  public EmailListData() {
    super();
    this.emailList = new ArrayList<>();
  }

  public final String getFirstname() {
    return this.firstname;
  }

  public final void setFirstname(final String pfirstname) {
    this.firstname = pfirstname;
  }

  public final String getLastname() {
    return this.lastname;
  }

  public final void setLastname(final String plastname) {
    this.lastname = plastname;
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
    this.emailList = pemailList;
  }

  @Override
  public String toString() {
    return "EmailListData [firstname=" + this.firstname + ", lastname=" + this.lastname
        + ", emailList=" + this.emailList + "]";
  }
}

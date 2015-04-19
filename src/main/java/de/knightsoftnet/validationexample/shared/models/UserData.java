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

import java.io.Serializable;

/**
 * The <code>UserData</code> class implements contains the data of a user.
 *
 * @author Manfred Tremmel
 */
public class UserData implements Serializable {
  /**
   * serial version uid.
   */
  private static final long serialVersionUID = 5156545253407272917L;

  /**
   * login name of the user.
   */
  private String userName;

  /**
   * password of the user.
   */
  private String password;

  /**
   * first name of the user.
   */
  private String firstName;

  /**
   * last name of the user.
   */
  private String lastName;

  /**
   * gender of the person.
   */
  private GenderEnum gender;

  /**
   * default constructor.
   */
  public UserData() {
    this(null);
  }

  /**
   * constructor initializing key field.
   *
   * @param puserName user to set
   */
  public UserData(final String puserName) {
    super();
    setUserName(puserName);
  }

  /**
   * constructor initializing all fields.
   *
   * @param puserName user to set
   * @param ppassword password to set
   * @param pfirstName first name of the user
   * @param plastName last name of the user
   * @param pgender gender of the user
   */
  public UserData(final String puserName, final String ppassword, final String pfirstName,
      final String plastName, final GenderEnum pgender) {
    super();
    setUserName(puserName);
    password = ppassword;
    firstName = pfirstName;
    lastName = plastName;
    gender = pgender;
  }

  public final String getUserName() {
    return userName;
  }

  private void setUserName(final String puserName) {
    userName = puserName;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(final String ppassword) {
    password = ppassword;
  }

  public final String getFirstName() {
    return firstName;
  }

  public final void setFirstName(final String pfirstName) {
    firstName = pfirstName;
  }

  public final String getLastName() {
    return lastName;
  }

  public final void setLastName(final String plastName) {
    lastName = plastName;
  }

  public final GenderEnum getGender() {
    return gender;
  }

  public final void setGender(final GenderEnum pgender) {
    gender = pgender;
  }
}

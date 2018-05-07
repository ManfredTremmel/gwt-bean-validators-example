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

import de.knightsoftnet.gwtp.spring.shared.models.User;

import org.apache.commons.lang3.StringUtils;

import java.util.Objects;

/**
 * The <code>UserData</code> class implements contains the data of a user.
 *
 * @author Manfred Tremmel
 */
public class UserData implements User {

  /**
   * login name of the user.
   */
  private String userName;

  /**
   * password of the user.
   */
  private String password;

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
    this(puserName, null);
  }

  /**
   * constructor initializing all fields.
   *
   * @param puserName user to set
   * @param ppassword password to set
   */
  public UserData(final String puserName, final String ppassword) {
    super();
    this.userName = puserName;
    this.password = ppassword;
  }

  @Override
  public final String getUserName() {
    return this.userName;
  }

  @Override
  public final void setUserName(final String puserName) {
    this.userName = puserName;
  }

  @Override
  public boolean isLoggedIn() {
    return StringUtils.isNotEmpty(this.userName);
  }

  public String getPassword() {
    return this.password;
  }

  public void setPassword(final String ppassword) {
    this.password = ppassword;
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(this.userName);
  }

  @Override
  public boolean equals(final Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null) {
      return false;
    }
    if (this.getClass() != obj.getClass()) {
      return false;
    }
    final UserData other = (UserData) obj;
    return StringUtils.equals(this.userName, other.getUserName());
  }
}

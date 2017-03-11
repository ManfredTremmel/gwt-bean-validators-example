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

import de.knightsoftnet.validators.shared.LevenshteinDistance;
import de.knightsoftnet.validators.shared.MustBeEqual;
import de.knightsoftnet.validators.shared.Password;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.validator.constraints.NotEmpty;

import java.util.Objects;

import javax.validation.constraints.Size;

/**
 * The <code>LoginData</code> class implements the LoginDataInterface with dummy functions.
 *
 * @author Manfred Tremmel
 */
@LevenshteinDistance.List({
    @LevenshteinDistance(field1 = "userName", field2 = "newPassword", minDistance = 3,
        addErrorToField1 = false),
    @LevenshteinDistance(field1 = "password", field2 = "newPassword", minDistance = 3,
        addErrorToField1 = false)})
@MustBeEqual(field1 = "newPassword", addErrorToField1 = false, field2 = "newPasswordRepeat")
public class LoginData {

  /**
   * maximum length of the user and password field.
   */
  private static final int LENGTH_LIMIT = 50;

  /**
   * name of the user.
   */
  @NotEmpty
  @Size(max = LENGTH_LIMIT)
  private String userName;

  /**
   * password of the user.
   */
  @NotEmpty
  @Size(max = LENGTH_LIMIT)
  private String password;

  /**
   * new password, if password should be changed.
   */
  @Password(minRules = 3)
  @Size(min = 8, max = LENGTH_LIMIT)
  private String newPassword;

  /**
   * new password repeated, if password should be changed.
   */
  @Size(max = LENGTH_LIMIT)
  private String newPasswordRepeat;

  public final String getUserName() {
    return this.userName;
  }

  public final void setUserName(final String puserName) {
    this.userName = puserName;
  }

  public final void setPassword(final String ppassword) {
    this.password = ppassword;
  }

  public final String getPassword() {
    return this.password;
  }

  public String getNewPassword() {
    return this.newPassword;
  }

  public void setNewPassword(final String pnewPassword) {
    this.newPassword = pnewPassword;
  }

  public String getNewPasswordRepeat() {
    return this.newPasswordRepeat;
  }

  public void setNewPasswordRepeat(final String pnewPasswordRepeat) {
    this.newPasswordRepeat = pnewPasswordRepeat;
  }

  /**
   * clear all data.
   */
  public void clear() {
    this.userName = null;
    this.password = null;
    this.newPassword = null;
    this.newPasswordRepeat = null;
  }

  @Override
  public final int hashCode() {
    return Objects.hashCode(this.userName);
  }

  @Override
  public final boolean equals(final Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null) {
      return false;
    }
    if (this.getClass() != obj.getClass()) {
      return false;
    }
    final LoginData other = (LoginData) obj;
    return StringUtils.equals(this.userName, other.userName);
  }

  @Override
  public String toString() {
    return "LoginData [userName=" + this.userName + "]";
  }
}

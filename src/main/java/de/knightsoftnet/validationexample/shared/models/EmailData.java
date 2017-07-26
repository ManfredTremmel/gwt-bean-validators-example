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

import de.knightsoftnet.validators.shared.Email;

import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;

public class EmailData {

  @Email
  @NotEmpty
  private String email;

  @NotNull
  private EmailTypeEnum type;

  public final void setEmail(final String pemail) {
    this.email = pemail;
  }

  public String getEmail() {
    return this.email;
  }

  public final EmailTypeEnum getType() {
    return this.type;
  }

  public final void setType(final EmailTypeEnum ptype) {
    this.type = ptype;
  }

  @Override
  public String toString() {
    return "EmailData [email=" + this.email + ", type=" + this.type + "]";
  }
}

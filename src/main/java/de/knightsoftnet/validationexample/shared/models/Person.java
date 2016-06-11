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

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Past;

/**
 * person data.
 *
 * @author Manfred Tremmel
 */
@Entity
public class Person {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  @NotEmpty
  private String firstName;
  @NotEmpty
  private String lastName;
  @Email
  private String email;
  @Past
  private Date birthday;

  public final Long getId() {
    return this.id;
  }

  public final void setId(final Long pid) {
    this.id = pid;
  }

  public final String getFirstName() {
    return this.firstName;
  }

  public final void setFirstName(final String pfirstName) {
    this.firstName = pfirstName;
  }

  public final String getLastName() {
    return this.lastName;
  }

  public final void setLastName(final String plastName) {
    this.lastName = plastName;
  }

  public final String getEmail() {
    return this.email;
  }

  public final void setEmail(final String pemail) {
    this.email = pemail;
  }

  public final Date getBirthday() {
    return this.birthday;
  }

  public final void setBirthday(final Date pbirthday) {
    this.birthday = pbirthday;
  }

  @Override
  public String toString() {
    return "Person [id=" + this.id + ", firstName=" + this.firstName + ", lastName=" + this.lastName
        + ", email=" + this.email + ", birthday=" + this.birthday + "]";
  }
}

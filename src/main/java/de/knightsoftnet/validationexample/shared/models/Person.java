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

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;

/**
 * person data.
 *
 * @author Manfred Tremmel
 */
@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
public class Person {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  @NotNull
  private SalutationEnum salutation;
  @NotEmpty
  private String firstName;
  @NotEmpty
  private String lastName;
  @Email
  private String email;
  @Past
  private Date birthday;

  public Long getId() {
    return id;
  }

  public void setId(final Long pid) {
    id = pid;
  }

  public SalutationEnum getSalutation() {
    return salutation;
  }

  public void setSalutation(final SalutationEnum psalutation) {
    salutation = psalutation;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(final String pfirstName) {
    firstName = pfirstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(final String plastName) {
    lastName = plastName;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(final String pemail) {
    email = pemail;
  }

  public Date getBirthday() {
    return birthday;
  }

  public void setBirthday(final Date pbirthday) {
    birthday = pbirthday;
  }

  @Override
  public String toString() {
    return "Person [id=" + id + ", salutation=" + salutation + ", firstName=" + firstName
        + ", lastName=" + lastName + ", email=" + email + ", birthday=" + birthday + "]";
  }
}

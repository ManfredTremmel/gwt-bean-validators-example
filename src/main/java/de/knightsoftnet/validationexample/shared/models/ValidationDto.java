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

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.validation.ConstraintViolation;

@JsonFormat
public class ValidationDto implements ValidationInterface {

  private String message;
  private String propertyPath;

  /**
   * default constructor.
   */
  public ValidationDto() {
    super();
  }

  /**
   * constructor fills data from violation.
   *
   * @param pviolation violation to fill date from
   */
  public ValidationDto(final ConstraintViolation<?> pviolation) {
    super();
    this.message = pviolation.getMessage();
    this.propertyPath = pviolation.getPropertyPath().toString();
  }

  @Override
  public final String getMessage() {
    return this.message;
  }

  @Override
  public final void setMessage(final String pmessage) {
    this.message = pmessage;
  }

  @Override
  public final String getPropertyPath() {
    return this.propertyPath;
  }

  @Override
  public final void setPropertyPath(final String ppropertyPath) {
    this.propertyPath = ppropertyPath;
  }
}

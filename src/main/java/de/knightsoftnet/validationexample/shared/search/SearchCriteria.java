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

package de.knightsoftnet.validationexample.shared.search;

public class SearchCriteria {
  private String key;
  private SearchOperation operation;
  private Object value;

  /**
   * default constructor.
   */
  public SearchCriteria() {
    super();
  }

  /**
   * constructor initializing fields.
   */
  public SearchCriteria(final String pkey, final SearchOperation poperation, final Object pvalue) {
    super();
    key = pkey;
    operation = poperation;
    value = pvalue;
  }

  public final String getKey() {
    return key;
  }

  public final void setKey(final String pkey) {
    key = pkey;
  }

  public final SearchOperation getOperation() {
    return operation;
  }

  public final void setOperation(final SearchOperation poperation) {
    operation = poperation;
  }

  public final Object getValue() {
    return value;
  }

  public final void setValue(final Object pvalue) {
    value = pvalue;
  }
}

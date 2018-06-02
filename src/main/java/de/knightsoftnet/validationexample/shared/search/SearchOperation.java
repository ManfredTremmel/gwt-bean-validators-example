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

public enum SearchOperation {
  EQUALITY, // :
  NEGATION, // !
  GREATER_THAN, // <
  LESS_THAN, // >
  LIKE, // ~
  STARTS_WITH, //
  ENDS_WITH, //
  CONTAINS;

  public static final String[] SIMPLE_OPERATION_SET = { //
      ":", //
      "!", //
      ">", //
      "<", //
      "~"};

  /**
   * get simple search operation from representing character.
   *
   * @param pinput character
   * @return SearchOperation enum or null if non matches
   */
  public static SearchOperation getSimpleOperation(final char pinput) {
    switch (pinput) {
      case ':':
        return EQUALITY;
      case '!':
        return NEGATION;
      case '>':
        return GREATER_THAN;
      case '<':
        return LESS_THAN;
      case '~':
        return LIKE;
      default:
        return null;
    }
  }
}

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

package de.knightsoftnet.validationexample.shared;

import de.knightsoftnet.validators.shared.ResourcePaths;

/**
 * definition of rest url pathes.
 *
 * @author Manfred Tremmel
 *
 */
public class AppResourcePaths extends ResourcePaths {

  public static final String PERSON_SUFFIX = "person";
  public static final String SEARCH_SUFFIX = "search";

  public static final String PHONE_NUMBER = API_BASE_DIR + "/phone";
  public static final String POSTAL_ADDRESS = API_BASE_DIR + "/postaladdress";
  public static final String SEPA = API_BASE_DIR + "/sepa";
  public static final String PERSON = API_BASE_DIR + "/" + PERSON_SUFFIX;
  public static final String PERSON_SEARCH = PERSON + "/" + SEARCH_SUFFIX;
}

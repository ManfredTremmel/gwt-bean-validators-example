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

package de.knightsoftnet.validationexample.server.converter.impl;

import de.knightsoftnet.navigation.shared.models.User;
import de.knightsoftnet.validationexample.shared.models.UserData;
import de.knightsoftnet.validators.server.converter.UserDetailsConverter;

import org.springframework.security.core.userdetails.UserDetails;

public class UserDetailsConverterImpl implements UserDetailsConverter {

  @Override
  public User convert(final UserDetails psource) {
    return new UserData(psource.getUsername(), psource.getPassword());
  }
}

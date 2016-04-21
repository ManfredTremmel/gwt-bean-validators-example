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

package de.knightsoftnet.validationexample.server.controller;

import static org.springframework.http.ResponseEntity.ok;

import de.knightsoftnet.validationexample.server.service.UserService;
import de.knightsoftnet.validationexample.shared.ResourcePaths.User;
import de.knightsoftnet.validationexample.shared.models.UserData;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.security.PermitAll;

/**
 * user web service. based on the work of https://github.com/imrabti/gwtp-spring-security
 *
 * @author Manfred Tremmel
 */
@RestController
@RequestMapping(value = User.ROOT, produces = MediaType.APPLICATION_JSON_VALUE)
public class UserController {
  private final UserService userService;

  @Autowired
  UserController(final UserService puserService) {
    this.userService = puserService;
  }

  @RequestMapping(value = User.LOGIN, method = RequestMethod.GET)
  @PermitAll
  ResponseEntity<Boolean> isCurrentUserLoggedIn() {
    return new ResponseEntity<>(this.userService.isCurrentUserLoggedIn(), HttpStatus.OK);
  }

  @RequestMapping(method = RequestMethod.GET)
  ResponseEntity<UserData> getCurrentUser() {
    return ok(this.userService.getCurrentUser());
  }
}

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

package de.knightsoftnet.validationexample.client.ui.page.login;

import de.knightsoftnet.validationexample.shared.models.LoginData;
import de.knightsoftnet.validationexample.shared.models.UserData;
import de.knightsoftnet.validators.shared.exceptions.ValidationException;

import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.google.gwt.user.client.rpc.XsrfProtectedService;
import com.google.gwt.user.server.rpc.NoXsrfProtect;
import com.google.gwt.user.server.rpc.XsrfProtect;

/**
 * Definition of the login/logout remote services.
 * 
 * @author Manfred Tremmel
 */
@RemoteServiceRelativePath("LoginLogout")
public interface LoginLogoutRemoteService extends XsrfProtectedService {
  /**
   * login function.
   *
   * @param loginData login data (user and password)
   * @return user data
   * @throws ValidationException if login data are not valid
   */
  @XsrfProtect
  UserData login(LoginData loginData) throws ValidationException;

  /**
   * logout function.
   */
  @XsrfProtect
  void logout();

  /**
   * get the session user.
   *
   * @return user data or null, if no user is logged in
   */
  @XsrfProtect
  UserData getSessionUser();

  /**
   * create a new session.
   */
  @NoXsrfProtect
  void createSession();
}

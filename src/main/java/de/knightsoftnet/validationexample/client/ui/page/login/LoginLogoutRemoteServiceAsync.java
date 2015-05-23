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

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.XsrfProtectedServiceAsync;

/**
 * Definition of the login/logout remote services (asynchron).
 *
 * @author Manfred Tremmel
 */
public interface LoginLogoutRemoteServiceAsync extends XsrfProtectedServiceAsync {
  /**
   * login function.
   *
   * @param loginData login data (user and password)
   * @param callback the user data
   */
  void login(LoginData loginData, AsyncCallback<UserData> callback);

  /**
   * logout function.
   *
   * @param callback we needn't a return value
   */
  void logout(AsyncCallback<Void> callback);

  /**
   * get the session user.
   *
   * @param callback the user data
   */
  void getSessionUser(AsyncCallback<UserData> callback);

  /**
   * create a new session.
   *
   * @param callback we needn't a return value
   */
  void createSession(AsyncCallback<Void> callback);

}

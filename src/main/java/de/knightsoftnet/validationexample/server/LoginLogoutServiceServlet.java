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

package de.knightsoftnet.validationexample.server;

import de.knightsoftnet.validationexample.client.ui.page.login.LoginLogoutRemoteService;
import de.knightsoftnet.validationexample.shared.models.GenderEnum;
import de.knightsoftnet.validationexample.shared.models.LoginData;
import de.knightsoftnet.validationexample.shared.models.UserData;
import de.knightsoftnet.validators.shared.exceptions.ValidationException;

import com.google.gwt.user.server.rpc.XsrfProtectedServiceServlet;

import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpSession;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;

/**
 * Definition of the login/logout remote services.
 *
 * @author Manfred Tremmel
 */
@WebServlet(urlPatterns = {"/gwtBeanValidatorsExample/LoginLogout"})
public class LoginLogoutServiceServlet extends XsrfProtectedServiceServlet
    implements LoginLogoutRemoteService {
  /**
   * name of the user data store in the session.
   */
  public static final String LOGGED_IN_USER = "LOGGED_IN_USER";

  /**
   * serial version unique id.
   */
  private static final long serialVersionUID = -2643836042061269911L;

  /**
   * map of users, in a real application, users would be in LDAP or a database, but this is only a
   * test application.
   */
  private static final Map<String, UserData> USER_MAP;

  static {
    USER_MAP = new HashMap<String, UserData>();
    USER_MAP.put("test1", new UserData("test1", "test1", "Herbert", "Mustermann", GenderEnum.M));
    USER_MAP.put("test2", new UserData("test2", "test2", "Maria", "Musterfrau", GenderEnum.F));
  }

  /**
   * This is only a dummy implementation. Don't use this in production!
   *
   * {@inheritDoc}
   */
  @Override
  public final UserData login(final LoginData ploginData) throws ValidationException {
    final HttpSession session = this.getSession(true);

    UserData thisUser = null;

    // validate input data, don't trust the client validation
    final Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

    final Set<ConstraintViolation<LoginData>> cv1 = validator.validate(ploginData);

    // no error, fine ;-)
    if (cv1.isEmpty()) {
      thisUser = USER_MAP.get(ploginData.getUserName());
      if (thisUser == null //
          || !StringUtils.equals(thisUser.getPassword(), ploginData.getPassword())) {
        // user exists, but password is wrong, null out the user
        thisUser = null;
      } else if (StringUtils.isNotEmpty(ploginData.getNewPassword())) {
        thisUser.setPassword(ploginData.getNewPassword());
      }

      // if we do have a session and a user, store it.
      if (session != null) {
        if (thisUser == null) {
          session.removeAttribute(LoginLogoutServiceServlet.LOGGED_IN_USER);
        } else {
          session.setAttribute(LoginLogoutServiceServlet.LOGGED_IN_USER, thisUser);
        }
      }
    } else {
      throw new ValidationException(cv1);
    }
    return thisUser;
  }

  @Override
  public final void logout() {
    final HttpSession session = this.getSession(false);

    if (session != null) {
      session.removeAttribute(LoginLogoutServiceServlet.LOGGED_IN_USER);
    }
  }

  @Override
  public final UserData getSessionUser() {
    final HttpSession session = this.getSession(false);
    if (session == null) {
      return null;
    } else {
      return (UserData) session.getAttribute(LoginLogoutServiceServlet.LOGGED_IN_USER);
    }
  }

  @Override
  public final void createSession() {
    this.getSession(true);
  }

  private HttpSession getSession(final boolean pcreateNew) {
    final HttpSession session;
    if (this.getThreadLocalRequest() == null) {
      session = null;
    } else {
      session = this.getThreadLocalRequest().getSession(pcreateNew);
    }
    return session;
  }
}

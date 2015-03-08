package de.knightsoftnet.validationexample.client.ui.login;

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

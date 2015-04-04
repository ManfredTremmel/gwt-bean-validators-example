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

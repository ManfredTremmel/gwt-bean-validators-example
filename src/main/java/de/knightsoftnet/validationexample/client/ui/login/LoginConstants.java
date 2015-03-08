package de.knightsoftnet.validationexample.client.ui.login;

import com.google.gwt.i18n.client.Constants;

/**
 * Localized values for the form.
 *
 * @author Manfred Tremmel
 *
 */
public interface LoginConstants extends Constants {
  /**
   * test for the header of the login view.
   *
   * @return header
   */
  String header();

  /**
   * text for the user label.
   *
   * @return labelUser
   */
  String labelUser();

  /**
   * text for the password label.
   *
   * @return labelPassword
   */
  String labelPassword();

  /**
   * text for change password.
   *
   * @return changePassword
   */
  String changePassword();

  /**
   * text for the new password label.
   *
   * @return labelNewPassword
   */
  String labelNewPassword();

  /**
   * text for the new password repeat label.
   *
   * @return labelNewPasswordRepeat
   */
  String labelNewPasswordRepeat();

  /**
   * text for the login button.
   *
   * @return buttonLogin
   */
  String buttonLogin();

  /**
   * text to display when login is not correct.
   *
   * @return messageLoginError
   */
  String messageLoginError();
}

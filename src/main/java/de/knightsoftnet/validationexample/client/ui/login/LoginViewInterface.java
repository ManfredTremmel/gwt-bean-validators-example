package de.knightsoftnet.validationexample.client.ui.login;

import de.knightsoftnet.validationexample.shared.models.LoginData;
import de.knightsoftnet.validators.client.editor.BeanValidationEditorDriver;

import com.google.gwt.editor.client.Editor;
import com.google.gwt.user.client.ui.IsWidget;

/**
 * View of the login view application interface.
 *
 * @author Manfred Tremmel
 *
 */
public interface LoginViewInterface extends Editor<LoginData> {
  /**
   * set a reference to the presenter/activity.
   *
   * @param ploginPresenterInterface reference to set
   */
  void setPresenter(LoginPresenterInterface ploginPresenterInterface);

  /**
   * fill the form with data.
   *
   * @param puser data to fill into the form
   */
  void fillForm(LoginData puser);

  /**
   * getter for the driver.
   *
   * @return the driver
   */
  BeanValidationEditorDriver<LoginData, ? extends LoginViewInterface> getDriver();

  /**
   * display a message on the screen.
   *
   * @param pmessage the message to display
   */
  void showMessage(String pmessage);

  /**
   * set focus on first widget.
   */
  void setFocusOnFirstWidget();

  /**
   * give back this view as widget.
   *
   * @return IsWidget
   */
  IsWidget asWidget();
}

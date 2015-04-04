package de.knightsoftnet.validationexample.client.ui.page.login;

import de.knightsoftnet.validationexample.shared.models.LoginData;
import de.knightsoftnet.validators.client.decorators.UniversalDecoratorWithIcons;
import de.knightsoftnet.validators.client.editor.BeanValidationEditorDriver;
import de.knightsoftnet.validators.client.editor.CheckTimeEnum;
import de.knightsoftnet.validators.client.event.FormSubmitEvent;
import de.knightsoftnet.validators.client.event.FormSubmitHandler;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;

/**
 * View of the validator test login.
 *
 * @author Manfred Tremmel
 *
 */
public class LoginViewGwtImpl extends Composite implements LoginViewInterface {

  /**
   * bind ui.
   */
  private static LoginViewUiBinder uiBinder = GWT.create(LoginViewUiBinder.class);

  /**
   * view interface.
   */
  interface LoginViewUiBinder extends UiBinder<Widget, LoginViewGwtImpl> {
  }

  /**
   * user name.
   */
  @UiField
  UniversalDecoratorWithIcons<String> userName;

  /**
   * password.
   */
  @UiField
  UniversalDecoratorWithIcons<String> password;

  /**
   * new password.
   */
  @UiField
  UniversalDecoratorWithIcons<String> newPassword;

  /**
   * new password repeat.
   */
  @UiField
  UniversalDecoratorWithIcons<String> newPasswordRepeat;

  /**
   * label to display messages.
   */
  @Ignore
  @UiField
  Label logMessages;

  /**
   * login button.
   */
  @Ignore
  @UiField
  Button loginButton;

  /**
   * interface of the driver to combine ui and bean.
   */
  interface Driver extends BeanValidationEditorDriver<LoginData, LoginViewGwtImpl> {
  }

  /**
   * create driver out of the interface.
   */
  private final Driver driver = GWT.create(Driver.class);

  /**
   * reference to the activity.
   */
  private LoginPresenterInterface activity;

  /**
   * default constructor.
   */
  public LoginViewGwtImpl() {
    super();
    this.initWidget(uiBinder.createAndBindUi(this));
    this.driver.initialize(this);
    this.driver.setSubmitButton(this.loginButton);
    this.driver.setCheckTime(CheckTimeEnum.ON_CHANGE);
    this.driver.addFormSubmitHandler(new FormSubmitHandler<LoginData>() {
      @Override
      public void onFormSubmit(final FormSubmitEvent<LoginData> pevent) {
        LoginViewGwtImpl.this.activity.tryToLogin();
      }
    });
  }

  @Override
  public final void setPresenter(final LoginPresenterInterface ploginPresenter) {
    this.activity = ploginPresenter;
  }

  @Override
  public final void fillForm(final LoginData puser) {
    this.driver.edit(puser);
  }

  @Override
  public final void showMessage(final String pmessage) {
    this.logMessages.setText(pmessage);
  }

  @Override
  public final BeanValidationEditorDriver<LoginData, ? extends LoginViewInterface> getDriver() {
    return this.driver;
  }

  @Override
  public final void setFocusOnFirstWidget() {
    this.userName.setFocus(true);
  }
}

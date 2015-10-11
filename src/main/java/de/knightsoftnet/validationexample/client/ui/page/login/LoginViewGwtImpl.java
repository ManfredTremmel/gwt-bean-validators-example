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
import de.knightsoftnet.validators.client.decorators.UniversalDecoratorWithIcons;
import de.knightsoftnet.validators.client.editor.BeanValidationEditorDriver;
import de.knightsoftnet.validators.client.event.FormSubmitEvent;
import de.knightsoftnet.validators.client.event.FormSubmitHandler;

import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;
import com.gwtplatform.mvp.client.ViewImpl;

import java.util.ArrayList;

import javax.inject.Inject;
import javax.validation.ConstraintViolation;

/**
 * View of the validator test login.
 *
 * @author Manfred Tremmel
 *
 */
public class LoginViewGwtImpl extends ViewImpl implements LoginPresenter.MyView,
    FormSubmitHandler<LoginData> {

  /**
   * view interface.
   */
  interface Binder extends UiBinder<Widget, LoginViewGwtImpl> {
  }

  /**
   * interface of the driver to combine ui and bean.
   */
  interface Driver extends BeanValidationEditorDriver<LoginData, LoginViewGwtImpl> {
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
   * create driver out of the interface.
   */
  private final Driver driver;

  /**
   * reference to the activity.
   */
  private LoginPresenter presenter;

  /**
   * constructor with injected data.
   *
   * @param pdriver driver
   * @param puiBinder ui binder
   */
  @Inject
  public LoginViewGwtImpl(final Driver pdriver, final Binder puiBinder) {
    super();
    this.initWidget(puiBinder.createAndBindUi(this));
    this.driver = pdriver;
    this.driver.initialize(this);
    this.driver.setSubmitButton(this.loginButton);
    this.driver.addFormSubmitHandler(this);
  }

  @Override
  public final void setPresenter(final LoginPresenter ppresenter) {
    this.presenter = ppresenter;
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
  public final void setFocusOnFirstWidget() {
    this.userName.setFocus(true);
  }

  @Override
  public void onFormSubmit(final FormSubmitEvent<LoginData> pevent) {
    this.presenter.tryToLogin();
  }

  @Override
  public void setConstraintViolations(final ArrayList<ConstraintViolation<?>> pvalidationErrorSet) {
    this.driver.setConstraintViolations(pvalidationErrorSet);
  }
}

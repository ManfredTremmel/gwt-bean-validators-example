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

package de.knightsoftnet.validationexample.client.ui.page.sepa;

import de.knightsoftnet.validationexample.shared.models.CountryEnum;
import de.knightsoftnet.validationexample.shared.models.SepaData;
import de.knightsoftnet.validators.client.decorators.UniversalDecoratorWithIcons;
import de.knightsoftnet.validators.client.editor.BeanValidationEditorDriver;
import de.knightsoftnet.validators.client.event.FormSubmitEvent;
import de.knightsoftnet.validators.client.event.FormSubmitHandler;
import de.knightsoftnet.validators.client.handlers.HandlerFactory;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;

/**
 * View of the validator test Sepa.
 *
 * @author Manfred Tremmel
 *
 */
public class SepaViewGwtImpl extends Composite implements SepaViewInterface {

  /**
   * bind ui.
   */
  private static SepaViewUiBinder uiBinder = GWT.create(SepaViewUiBinder.class);

  /**
   * view interface.
   */
  interface SepaViewUiBinder extends UiBinder<Widget, SepaViewGwtImpl> {
  }

  /**
   * bank name.
   */
  @UiField
  UniversalDecoratorWithIcons<String> bankName;

  /**
   * password.
   */
  @UiField
  UniversalDecoratorWithIcons<String> accountOwner;

  /**
   * country code.
   */
  @UiField
  UniversalDecoratorWithIcons<CountryEnum> countryCode;

  /**
   * iban.
   */
  @UiField
  UniversalDecoratorWithIcons<String> iban;

  /**
   * bic.
   */
  @UiField
  UniversalDecoratorWithIcons<String> bic;

  /**
   * label to display messages.
   */
  @Ignore
  @UiField
  Label logMessages;

  /**
   * Sepa button.
   */
  @Ignore
  @UiField
  Button sepaButton;

  /**
   * interface of the driver to combine ui and bean.
   */
  interface Driver extends BeanValidationEditorDriver<SepaData, SepaViewGwtImpl> {
  }

  /**
   * create driver out of the interface.
   */
  private final Driver driver = GWT.create(Driver.class);

  /**
   * reference to the activity.
   */
  private SepaPresenterInterface activity;

  /**
   * default constructor.
   */
  public SepaViewGwtImpl() {
    super();
    this.initWidget(uiBinder.createAndBindUi(this));
    this.driver.initialize(this);
    this.driver.setSubmitButton(this.sepaButton);
    this.driver.addFormSubmitHandler(new FormSubmitHandler<SepaData>() {
      @Override
      public void onFormSubmit(final FormSubmitEvent<SepaData> pevent) {
        SepaViewGwtImpl.this.activity.tryToSend();
      }
    });
    this.iban.addKeyPressHandler(HandlerFactory.getIbanKeyPressHandler());
    this.iban.addKeyUpHandler(HandlerFactory.getIbanKeyUpHandler());
    this.bic.addKeyPressHandler(HandlerFactory.getNumericAndUpperAsciiKeyPressHandler());
  }

  @Override
  public final void setPresenter(final SepaPresenterInterface psepaPresenter) {
    this.activity = psepaPresenter;
  }

  @Override
  public final void fillForm(final SepaData puser) {
    this.driver.edit(puser);
  }

  @Override
  public final void showMessage(final String pmessage) {
    this.logMessages.setText(pmessage);
  }

  @Override
  public final BeanValidationEditorDriver<SepaData, ? extends SepaViewInterface> getDriver() {
    return this.driver;
  }

  @Override
  public final void setFocusOnFirstWidget() {
    this.bankName.setFocus(true);
  }
}

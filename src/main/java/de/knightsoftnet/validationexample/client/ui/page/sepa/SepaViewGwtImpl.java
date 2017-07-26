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

import de.knightsoftnet.mtwidgets.client.ui.widget.BicSuggestBox;
import de.knightsoftnet.mtwidgets.client.ui.widget.CountryListBox;
import de.knightsoftnet.mtwidgets.client.ui.widget.IbanTextBox;
import de.knightsoftnet.mtwidgets.client.ui.widget.TextBox;
import de.knightsoftnet.validationexample.shared.models.SepaData;
import de.knightsoftnet.validators.client.editor.BeanValidationEditorDriver;
import de.knightsoftnet.validators.client.event.FormSubmitEvent;
import de.knightsoftnet.validators.client.rest.helper.AbstractViewWithErrorHandling;

import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;

import javax.inject.Inject;

/**
 * View of the validator test Sepa.
 *
 * @author Manfred Tremmel
 *
 */
public class SepaViewGwtImpl extends AbstractViewWithErrorHandling<SepaPresenter, SepaData>
    implements SepaPresenter.MyView {

  interface Binder extends UiBinder<Widget, SepaViewGwtImpl> {
  }

  interface Driver extends BeanValidationEditorDriver<SepaData, SepaViewGwtImpl> {
  }

  @UiField
  Label bankName;
  @UiField
  TextBox accountOwner;
  @UiField
  CountryListBox countryCode;
  @UiField
  IbanTextBox iban;
  @UiField
  BicSuggestBox bic;

  @Ignore
  @UiField
  Label logMessages;
  @Ignore
  @UiField
  Button sepaButton;

  /**
   * constructor with injected parameters.
   *
   * @param pdriver editor driver
   * @param puiBinder ui binder
   */
  @Inject
  public SepaViewGwtImpl(final Driver pdriver, final Binder puiBinder) {
    super(pdriver);
    this.initWidget(puiBinder.createAndBindUi(this));
    this.driver.initialize(this);
    this.driver.setSubmitButton(this.sepaButton);
    this.driver.addFormSubmitHandler(this);
  }

  @Override
  public final void setPresenter(final SepaPresenter ppresenter) {
    super.setPresenter(ppresenter);
    // limit possible countries to sepa countries
    this.countryCode.fillEntries(this.presenter.getSepaCountries());
  }

  @Override
  public final void showMessage(final String pmessage) {
    this.logMessages.setText(pmessage);
  }

  @Override
  public void onFormSubmit(final FormSubmitEvent<SepaData> pevent) {
    this.presenter.tryToSend();
  }
}

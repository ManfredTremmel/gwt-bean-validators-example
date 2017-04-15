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

package de.knightsoftnet.validationexample.client.ui.page.address;

import de.knightsoftnet.mtwidgets.client.ui.widget.CountryListBox;
import de.knightsoftnet.mtwidgets.client.ui.widget.PostalCodeTextBox;
import de.knightsoftnet.mtwidgets.client.ui.widget.TextBox;
import de.knightsoftnet.validationexample.shared.models.PostalAddressData;
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
 * View of the validator test - postal address.
 *
 * @author Manfred Tremmel
 *
 */
public class AddressViewGwtImpl extends ViewImpl
    implements AddressPresenter.MyView, FormSubmitHandler<PostalAddressData> {

  interface Binder extends UiBinder<Widget, AddressViewGwtImpl> {
  }

  interface Driver extends BeanValidationEditorDriver<PostalAddressData, AddressViewGwtImpl> {
  }

  @UiField
  TextBox postOfficeBox;
  @UiField
  TextBox street;
  @UiField
  TextBox streetNumber;
  @UiField
  TextBox streetNumberAdditional;
  @UiField
  TextBox extended;
  @UiField
  PostalCodeTextBox postalCode;
  @UiField
  TextBox locality;
  @UiField
  TextBox region;
  @UiField
  CountryListBox countryCode;

  @Ignore
  @UiField
  Label logMessages;
  @Ignore
  @UiField
  Button addressButton;

  private final Driver driver;

  private AddressPresenter presenter;

  /**
   * constructor with injected parameters.
   *
   * @param pdriver editor driver
   * @param puiBinder ui binder
   */
  @Inject
  public AddressViewGwtImpl(final Driver pdriver, final Binder puiBinder) {
    super();
    this.initWidget(puiBinder.createAndBindUi(this));
    this.driver = pdriver;
    this.driver.initialize(this);
    this.driver.setSubmitButton(this.addressButton);
    this.driver.addFormSubmitHandler(this);
  }

  @Override
  public final void setPresenter(final AddressPresenter ppresenter) {
    this.presenter = ppresenter;
  }

  @Override
  public final void fillForm(final PostalAddressData puser) {
    this.driver.edit(puser);
  }

  @Override
  public final void showMessage(final String pmessage) {
    this.logMessages.setText(pmessage);
  }

  @Override
  public final void setFocusOnFirstWidget() {
    this.street.setFocus(true);
  }

  @Override
  public final void onFormSubmit(final FormSubmitEvent<PostalAddressData> pevent) {
    this.presenter.tryToSend();
  }

  @Override
  public void setConstraintViolations(final ArrayList<ConstraintViolation<?>> pvalidationErrorSet) {
    this.driver.setConstraintViolations(pvalidationErrorSet);
  }
}

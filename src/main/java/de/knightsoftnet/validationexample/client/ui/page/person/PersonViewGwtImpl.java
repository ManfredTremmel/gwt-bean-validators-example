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

package de.knightsoftnet.validationexample.client.ui.page.person;

import de.knightsoftnet.mtwidgets.client.ui.widget.LongBox;
import de.knightsoftnet.validationexample.shared.models.Person;
import de.knightsoftnet.validationexample.shared.models.SalutationEnum;
import de.knightsoftnet.validators.client.decorators.UniversalDecoratorWithIcons;
import de.knightsoftnet.validators.client.editor.BeanValidationEditorDriver;
import de.knightsoftnet.validators.client.event.FormSubmitEvent;
import de.knightsoftnet.validators.client.event.FormSubmitHandler;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.user.datepicker.client.DateBox;
import com.gwtplatform.mvp.client.ViewImpl;

import java.util.ArrayList;
import java.util.Date;

import javax.inject.Inject;
import javax.validation.ConstraintViolation;

/**
 * View of the validator test Sepa.
 *
 * @author Manfred Tremmel
 *
 */
public class PersonViewGwtImpl extends ViewImpl
    implements PersonPresenter.MyView, FormSubmitHandler<Person> {

  interface Binder extends UiBinder<Widget, PersonViewGwtImpl> {
  }

  interface Driver extends BeanValidationEditorDriver<Person, PersonViewGwtImpl> {
  }

  @Ignore
  @UiField
  Button newButton;
  @Ignore
  @UiField
  LongBox goToNumber;
  @Ignore
  @UiField
  Button goToButton;
  @Ignore
  @UiField
  Button deleteButton;

  @UiField
  UniversalDecoratorWithIcons<Long> id;
  @UiField
  UniversalDecoratorWithIcons<SalutationEnum> salutation;
  @UiField
  UniversalDecoratorWithIcons<String> firstName;
  @UiField
  UniversalDecoratorWithIcons<String> lastName;
  @UiField
  UniversalDecoratorWithIcons<String> email;
  @UiField
  UniversalDecoratorWithIcons<Date> birthday;

  @Ignore
  @UiField
  Label logMessages;
  @Ignore
  @UiField
  Button submitButton;

  private final Driver driver;

  private PersonPresenter presenter;

  /**
   * constructor with injected parameters.
   *
   * @param pdriver editor driver
   * @param puiBinder ui binder
   */
  @Inject
  public PersonViewGwtImpl(final Driver pdriver, final Binder puiBinder) {
    super();
    new DateBox();
    this.initWidget(puiBinder.createAndBindUi(this));
    this.driver = pdriver;
    this.driver.initialize(this);
    this.driver.setSubmitButton(this.submitButton);
    this.driver.addFormSubmitHandler(this);
  }

  @Override
  public final void setPresenter(final PersonPresenter ppresenter) {
    this.presenter = ppresenter;
  }

  @Override
  public final void fillForm(final Person person) {
    this.driver.edit(person);
  }

  @Override
  public final void showMessage(final String pmessage) {
    this.logMessages.setText(pmessage);
  }

  @Override
  public final void setFocusOnFirstWidget() {
    this.firstName.setFocus(true);
  }

  @Override
  public void onFormSubmit(final FormSubmitEvent<Person> pevent) {
    this.presenter.tryToSend();
  }

  @Override
  public void setConstraintViolations(final ArrayList<ConstraintViolation<?>> pvalidationErrorSet) {
    this.driver.setConstraintViolations(pvalidationErrorSet);
  }

  @UiHandler("newButton")
  public void addNewEntry(final ClickEvent pevent) {
    this.presenter.addNewEntry();
  }

  @UiHandler("goToButton")
  public void goToByClick(final ClickEvent pevent) {
    this.presenter.readEntry(this.goToNumber.getValue());
  }

  @UiHandler("deleteButton")
  public void deleteByClick(final ClickEvent pevent) {
    this.presenter.deleteEntry(this.id.getValue());
  }
}

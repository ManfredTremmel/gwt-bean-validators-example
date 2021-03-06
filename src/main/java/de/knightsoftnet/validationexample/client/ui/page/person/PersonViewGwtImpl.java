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

import de.knightsoftnet.gwtp.spring.client.rest.helper.AbstractViewWithErrorHandling;
import de.knightsoftnet.mtwidgets.client.ui.widget.DateBox;
import de.knightsoftnet.mtwidgets.client.ui.widget.EmailTextBox;
import de.knightsoftnet.mtwidgets.client.ui.widget.LongBox;
import de.knightsoftnet.mtwidgets.client.ui.widget.TextBox;
import de.knightsoftnet.validationexample.client.ui.widget.SalutationRadioButton;
import de.knightsoftnet.validationexample.shared.models.Person;
import de.knightsoftnet.validators.client.editor.BeanValidationEditorDriver;
import de.knightsoftnet.validators.client.event.FormSubmitEvent;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;

import org.apache.commons.lang3.time.DateUtils;

import java.util.Calendar;
import java.util.Date;

import javax.inject.Inject;

/**
 * View of the validator test Sepa.
 *
 * @author Manfred Tremmel
 *
 */
public class PersonViewGwtImpl extends AbstractViewWithErrorHandling<PersonPresenter, Person>
    implements PersonPresenter.MyView {

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
  LongBox id;
  @UiField
  SalutationRadioButton salutation;
  @UiField
  TextBox firstName;
  @UiField
  TextBox lastName;
  @UiField
  EmailTextBox email;
  @UiField
  DateBox birthday;

  @Ignore
  @UiField
  Label logMessages;
  @Ignore
  @UiField
  Button submitButton;

  /**
   * constructor with injected parameters.
   *
   * @param pdriver editor driver
   * @param puiBinder ui binder
   */
  @Inject
  public PersonViewGwtImpl(final Driver pdriver, final Binder puiBinder) {
    super(pdriver);
    initWidget(puiBinder.createAndBindUi(this));
    driver.initialize(this);
    driver.setSubmitButton(submitButton);
    driver.addFormSubmitHandler(this);
    birthday.setMax(DateUtils.truncate(DateUtils.addYears(new Date(), -18), Calendar.DAY_OF_MONTH));
  }

  @Override
  public final void showMessage(final String pmessage) {
    logMessages.setText(pmessage);
  }

  @Override
  public void onFormSubmit(final FormSubmitEvent<Person> pevent) {
    presenter.tryToSend();
  }

  @UiHandler("newButton")
  public void addNewEntry(final ClickEvent pevent) {
    presenter.addNewEntry();
  }

  @UiHandler("goToButton")
  public void goToByClick(final ClickEvent pevent) {
    presenter.readEntry(goToNumber.getValue());
  }

  @UiHandler("deleteButton")
  public void deleteByClick(final ClickEvent pevent) {
    presenter.deleteEntry(id.getValue());
  }
}

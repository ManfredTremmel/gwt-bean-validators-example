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

package de.knightsoftnet.validationexample.client.ui.page.emaillist;

import de.knightsoftnet.mtwidgets.client.ui.widget.TextBox;
import de.knightsoftnet.validationexample.shared.models.EmailListData;
import de.knightsoftnet.validators.client.editor.BeanValidationEditorDriver;
import de.knightsoftnet.validators.client.event.FormSubmitEvent;
import de.knightsoftnet.validators.client.rest.helper.AbstractViewWithErrorHandling;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;

import javax.inject.Inject;

/**
 * View of the validator test - E-Mail List.
 *
 * @author Manfred Tremmel
 *
 */
public class EmailViewGwtImpl extends AbstractViewWithErrorHandling<EmailPresenter, EmailListData>
    implements EmailPresenter.MyView {

  interface Binder extends UiBinder<Widget, EmailViewGwtImpl> {
  }

  interface Driver extends BeanValidationEditorDriver<EmailListData, EmailViewGwtImpl> {
  }

  @UiField
  TextBox firstname;
  @UiField
  TextBox lastname;

  @UiField
  EmailListEditor emailList;

  @Ignore
  @UiField
  Label logMessages;
  @Ignore
  @UiField
  Button addressButton;

  /**
   * constructor with injected parameters.
   *
   * @param pdriver editor driver
   * @param puiBinder ui binder
   */
  @Inject
  public EmailViewGwtImpl(final Driver pdriver, final Binder puiBinder) {
    super(pdriver);
    this.initWidget(puiBinder.createAndBindUi(this));
    this.emailList.setParentDriver(this.driver);
    this.driver.initialize(this);
    this.driver.setSubmitButton(this.addressButton);
    this.driver.addFormSubmitHandler(this);
  }

  @Override
  public final void showMessage(final String pmessage) {
    this.logMessages.setText(pmessage);
  }

  @Override
  public final void setFocusOnFirstWidget() {
    this.firstname.setFocus(true);
  }

  @Override
  public final void onFormSubmit(final FormSubmitEvent<EmailListData> pevent) {
    this.presenter.tryToSend();
  }

  @UiHandler("addEmail")
  public void addNewEmail(final ClickEvent pevent) {
    this.emailList.addNewEntry();
  }
}

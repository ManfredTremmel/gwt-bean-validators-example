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

import de.knightsoftnet.mtwidgets.client.ui.widget.EmailTextBox;
import de.knightsoftnet.validationexample.client.event.EditorDeleteEvent;
import de.knightsoftnet.validationexample.client.event.EditorDeleteEvent.EditorDeleteHandler;
import de.knightsoftnet.validationexample.client.ui.widget.EmailTypeListBox;
import de.knightsoftnet.validationexample.shared.models.EmailData;
import de.knightsoftnet.validators.client.editor.BeanValidationEditorDriver;
import de.knightsoftnet.validators.client.editor.HasParentDriverSetter;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.editor.client.EditorDelegate;
import com.google.gwt.editor.client.HasEditorDelegate;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;
import com.google.web.bindery.event.shared.HandlerRegistration;


/**
 * View of the email item, gwt implementation.
 *
 * @author Manfred Tremmel
 * @version $Rev$, $Date$
 */
public class EmailItemViewImpl extends Composite
    implements HasEditorDelegate<EmailData>, EditorDeleteEvent.EditorDeleteHandlers<EmailData> {

  interface Binder extends UiBinder<Widget, EmailItemViewImpl> {
  }

  private static Binder uiBinder = GWT.create(Binder.class);

  @UiField
  EmailTextBox email;

  @UiField
  EmailTypeListBox type;

  private final BeanValidationEditorDriver<?, ?> parentDriver;

  /**
   * constructor with parent driver.
   *
   * @param pparentDriver parent driver to use.
   */
  public EmailItemViewImpl(final BeanValidationEditorDriver<?, ?> pparentDriver) {
    super();
    this.parentDriver = pparentDriver;
    this.initWidget(EmailItemViewImpl.uiBinder.createAndBindUi(this));
  }

  @Override
  public void setDelegate(final EditorDelegate<EmailData> pdelegate) {
    if (pdelegate instanceof HasParentDriverSetter) {
      ((HasParentDriverSetter) pdelegate).setParentDriver(this.parentDriver);
    }
    pdelegate.subscribe();
  }

  @UiHandler("deleteRow")
  public void onDeleteRow(final ClickEvent pevent) {
    this.fireEvent(new EditorDeleteEvent<>(this));
  }

  @Override
  public HandlerRegistration addEditorDeleteHandler(final EditorDeleteHandler<EmailData> phandler) {
    return this.addHandler(phandler, EditorDeleteEvent.getType());
  }
}

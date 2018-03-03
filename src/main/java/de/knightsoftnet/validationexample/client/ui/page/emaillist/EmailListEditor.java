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

import de.knightsoftnet.mtwidgets.client.ui.widget.helper.AbstractItemEditorSource;
import de.knightsoftnet.mtwidgets.client.ui.widget.helper.AbstractListEditor;
import de.knightsoftnet.validationexample.shared.models.EmailData;
import de.knightsoftnet.validators.client.editor.impl.ListValidationEditor;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.editor.client.SimpleBeanEditorDriver;

import java.util.List;

/**
 * editor to show a list of search result entries.
 *
 * @author Manfred Tremmel
 */
public class EmailListEditor extends AbstractListEditor<EmailData, EmailItemViewImpl> {

  /**
   * interface of the driver to combine ui and bean.
   */
  interface Driver extends SimpleBeanEditorDriver<List<EmailData>, //
      ListValidationEditor<EmailData, EmailItemViewImpl>> {
  }

  private static Driver driver = GWT.create(Driver.class);

  private class EmailDataItemEditorSource
      extends AbstractItemEditorSource<EmailData, EmailItemViewImpl> {

    public EmailDataItemEditorSource() {
      super(EmailListEditor.this);
    }

    @Override
    protected EmailItemViewImpl createItemView() {
      return new EmailItemViewImpl();
    }
  }

  private final ListValidationEditor<EmailData, EmailItemViewImpl> editor =
      ListValidationEditor.of(new EmailDataItemEditorSource());

  /**
   * constructor.
   *
   */
  public EmailListEditor() {
    super();
    EmailListEditor.driver.initialize(this.editor);
  }

  @Override
  public ListValidationEditor<EmailData, EmailItemViewImpl> asEditor() {
    return this.editor;
  }

  @Override
  protected EmailData createData() {
    return new EmailData();
  }
}

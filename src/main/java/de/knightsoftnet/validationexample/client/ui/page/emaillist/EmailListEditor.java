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

import de.knightsoftnet.validationexample.shared.models.EmailData;
import de.knightsoftnet.validators.client.editor.BeanValidationEditorDriver;
import de.knightsoftnet.validators.client.editor.impl.ListValidationEditor;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.editor.client.EditorError;
import com.google.gwt.editor.client.HasEditorErrors;
import com.google.gwt.editor.client.IsEditor;
import com.google.gwt.editor.client.adapters.EditorSource;
import com.google.gwt.event.logical.shared.HasValueChangeHandlers;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HTMLPanel;

import org.apache.commons.lang3.StringUtils;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * editor to show a list of search result entries.
 *
 * @author Manfred Tremmel
 *
 */
public class EmailListEditor extends FlowPanel
    implements IsEditor<ListValidationEditor<EmailData, EmailItemViewImpl>>,
    HasEditorErrors<List<EmailData>>, HasValueChangeHandlers<List<EmailData>> {

  /**
   * interface of the driver to combine ui and bean.
   */
  interface Driver extends BeanValidationEditorDriver<List<EmailData>, //
      ListValidationEditor<EmailData, EmailItemViewImpl>> {
  }

  private static Driver driver = GWT.create(Driver.class);

  private BeanValidationEditorDriver<?, ?> parentDriver;

  private HTMLPanel validationMessageElement;

  private class EmailDataItemEditorSource extends EditorSource<EmailItemViewImpl> {
    @Override
    public EmailItemViewImpl create(final int index) {
      final EmailItemViewImpl subEditor = new EmailItemViewImpl(EmailListEditor.this.parentDriver);
      EmailListEditor.this.insert(subEditor, index);
      subEditor.addEditorDeleteHandler(event -> EmailListEditor.this.removeEntry(index));
      return subEditor;
    }

    @Override
    public void dispose(final EmailItemViewImpl subEditor) {
      subEditor.removeFromParent();
    }

    @Override
    public void setIndex(final EmailItemViewImpl editor, final int index) {
      EmailListEditor.this.insert(editor, index);
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

  public final void setParentDriver(final BeanValidationEditorDriver<?, ?> pparentDriver) {
    this.editor.setParentDriver(pparentDriver);
    this.parentDriver = pparentDriver;
  }

  @Override
  public ListValidationEditor<EmailData, EmailItemViewImpl> asEditor() {
    return this.editor;
  }

  public void removeEntry(final int ppos) {
    this.editor.getList().remove(ppos);
    ValueChangeEvent.fire(this, this.editor.getList());
  }

  public void addNewEntry() {
    this.editor.getList().add(new EmailData());
    ValueChangeEvent.fire(this, this.editor.getList());
  }

  @Override
  public void showErrors(final List<EditorError> perrors) {
    final Set<String> messages = new HashSet<>();
    for (final EditorError error : perrors) {
      if (this.editorErrorMatches(error)) {
        messages.add(error.getMessage());
      }
    }
    if (messages.isEmpty()) {
      if (this.validationMessageElement != null) {
        this.validationMessageElement.getElement().setInnerText(StringUtils.EMPTY);
      }
    } else {
      final SafeHtmlBuilder sbList = new SafeHtmlBuilder();
      sbList.appendHtmlConstant("<ul>");
      for (final String message : messages) {
        sbList.appendHtmlConstant("<li>");
        sbList.appendEscaped(message);
        sbList.appendHtmlConstant("</li>");
      }
      sbList.appendHtmlConstant("</ul>");
      if (this.validationMessageElement == null) {
        GWT.log(sbList.toSafeHtml().asString());
      } else {
        this.validationMessageElement.getElement().setInnerSafeHtml(sbList.toSafeHtml());
      }
    }
  }

  public void setValidationMessageElement(final HTMLPanel pelement) {
    this.validationMessageElement = pelement;
  }

  /**
   * Checks if a error belongs to this widget.
   *
   * @param perror editor error to check
   * @return true if the error belongs to this widget
   */
  protected boolean editorErrorMatches(final EditorError perror) {
    return perror != null && perror.getEditor() != null
        && (this.equals(perror.getEditor()) || perror.getEditor().equals(this.asEditor()));
  }

  @Override
  public HandlerRegistration addValueChangeHandler(
      final ValueChangeHandler<List<EmailData>> phandler) {
    return this.addHandler(phandler, ValueChangeEvent.getType());
  }
}

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

package de.knightsoftnet.validationexample.client.ui.widget;

import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.i18n.client.LocaleInfo;
import com.google.gwt.user.client.ui.HasValue;
import com.google.gwt.user.client.ui.ListBox;

import org.apache.commons.lang3.StringUtils;

public class LanguageSelectorWidget extends ListBox implements HasValue<String> {
  private boolean valueChangeHandlerInitialized;

  /**
   * default constructor.
   */
  public LanguageSelectorWidget() {
    super();
    this.setVisibleItemCount(1);

    for (final String lang : LocaleInfo.getAvailableLocaleNames()) {
      if (!"default".equals(lang)) {
        this.addItem(LocaleInfo.getLocaleNativeDisplayName(lang), lang);
      }
    }
    this.setValue(LocaleInfo.getCurrentLocale().getLocaleName(), false);
  }

  @Override
  public HandlerRegistration addValueChangeHandler(final ValueChangeHandler<String> phandler) {
    // Is this the first value change handler? If so, time to add handlers
    if (!this.valueChangeHandlerInitialized) {
      this.ensureDomEventHandlers();
      this.valueChangeHandlerInitialized = true;
    }
    return this.addHandler(phandler, ValueChangeEvent.getType());
  }

  protected void ensureDomEventHandlers() {
    this.addChangeHandler(new ChangeHandler() {
      @Override
      public void onChange(final ChangeEvent event) {
        ValueChangeEvent.fire(LanguageSelectorWidget.this, LanguageSelectorWidget.this.getValue());
      }
    });
  }

  @Override
  public String getValue() {
    final int currentSelection = this.getSelectedIndex();
    if (currentSelection < 0) {
      return null;
    }
    return this.getValue(currentSelection);
  }

  @Override
  public void setValue(final String pvalue) {
    this.setValue(pvalue, true);
  }

  @Override
  public void setValue(final String pvalue, final boolean pfireEvents) {
    if (!StringUtils.equals(this.getValue(), pvalue)) {
      int newIndex = 0;
      for (int i = 0; i < this.getItemCount(); i++) {
        if (StringUtils.equals(this.getValue(i), pvalue)) {
          newIndex = i;
          break;
        }
      }
      this.setSelectedIndex(newIndex);
      if (pfireEvents) {
        ValueChangeEvent.fire(this, pvalue);
      }
    }
  }
}

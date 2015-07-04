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

import de.knightsoftnet.validators.client.data.BicMapConstants;
import de.knightsoftnet.validators.client.handlers.HandlerFactory;
import de.knightsoftnet.validators.shared.impl.BicValueValidator;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.SuggestBox;

import org.apache.commons.lang3.StringUtils;

/**
 * BIC suggest widget.
 *
 * @author Manfred Tremmel
 *
 */
public class BicWidget extends SuggestBox {

  /**
   * map of the bic values.
   */
  private static final BicMapConstants BIC_MAP = GWT.create(BicMapConstants.class);

  /**
   * bank name widget to fill.
   */
  private HasText bankNameWidget;

  /**
   * default constructor.
   */
  public BicWidget() {
    super(new BicOracle());
    this.setWidth("11em");
    this.getValueBox().addKeyPressHandler(HandlerFactory.getNumericAndUpperAsciiKeyPressHandler());

    this.getValueBox().addValueChangeHandler(new ValueChangeHandler<String>() {

      @Override
      public void onValueChange(final ValueChangeEvent<String> pevent) {
        BicWidget.this.fillBankName(pevent.getValue());
      }
    });
  }

  @Override
  public void setText(final String ptext) {
    super.setText(ptext);
    this.fillBankName(ptext);
  }

  @Override
  public void setValue(final String pnewValue) {
    super.setValue(pnewValue);
    this.fillBankName(pnewValue);
  }

  @Override
  public void setValue(final String pvalue, final boolean pfireEvents) {
    super.setValue(pvalue, pfireEvents);
    this.fillBankName(pvalue);
  }


  protected void fillBankName(final String pvalue) {
    if (this.bankNameWidget != null) {
      if (BIC_MAP.bics().containsKey(pvalue)) {
        this.bankNameWidget.setText(BIC_MAP.bics().get(pvalue));
      } else if (BIC_MAP.bics()
          .containsKey(StringUtils.substring(pvalue, 0, BicValueValidator.BIC_LENGTH_MIN))) {
        this.bankNameWidget.setText(
            BIC_MAP.bics().get(StringUtils.substring(pvalue, 0, BicValueValidator.BIC_LENGTH_MIN)));
      } else {
        this.bankNameWidget.setText(StringUtils.EMPTY);
      }
    }
  }

  public HasText getBankNameWidget() {
    return this.bankNameWidget;
  }

  public void setBankNameWidget(final HasText pbankNameWidget) {
    this.bankNameWidget = pbankNameWidget;
  }
}

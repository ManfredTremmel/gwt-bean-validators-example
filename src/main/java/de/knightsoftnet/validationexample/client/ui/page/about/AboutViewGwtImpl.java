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

package de.knightsoftnet.validationexample.client.ui.page.about;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

/**
 * View of the about page, gwt implementation.
 *
 * @author Manfred Tremmel
 *
 */
public class AboutViewGwtImpl extends Composite implements AboutViewInterface {

  /**
   * bind ui.
   */
  private static SecretViewUiBinder uiBinder = GWT.create(SecretViewUiBinder.class);

  /**
   * view interface.
   */
  interface SecretViewUiBinder extends UiBinder<Widget, AboutViewGwtImpl> {
  }

  /**
   * the button to close the about view.
   */
  @UiField
  Button closeButton;

  /**
   * presenter to remember.
   */
  private AboutPresenterInterface presenter;

  /**
   * default constructor.
   */
  public AboutViewGwtImpl() {
    super();
    this.initWidget(uiBinder.createAndBindUi(this));
  }

  @Override
  public final void setPresenter(final AboutPresenterInterface paboutPresenterInterface) {
    this.presenter = paboutPresenterInterface;
  }

  /**
   * click on the login button.
   *
   * @param pclickEvent click event.
   */
  @UiHandler("closeButton")
  final void onClick(final ClickEvent pclickEvent) {
    this.presenter.goBackToPreviousPage();
  }
}

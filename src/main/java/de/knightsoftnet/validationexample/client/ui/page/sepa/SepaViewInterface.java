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

package de.knightsoftnet.validationexample.client.ui.page.sepa;

import de.knightsoftnet.validationexample.shared.models.SepaData;
import de.knightsoftnet.validators.client.editor.BeanValidationEditorDriver;

import com.google.gwt.editor.client.Editor;
import com.google.gwt.user.client.ui.IsWidget;

/**
 * View of the sepa view application interface.
 *
 * @author Manfred Tremmel
 *
 */
public interface SepaViewInterface extends Editor<SepaData> {
  /**
   * set a reference to the presenter/activity.
   *
   * @param psepaPresenterInterface reference to set
   */
  void setPresenter(SepaPresenterInterface psepaPresenterInterface);

  /**
   * fill the form with data.
   *
   * @param psepData data to fill into the form
   */
  void fillForm(SepaData psepData);

  /**
   * getter for the driver.
   *
   * @return the driver
   */
  BeanValidationEditorDriver<SepaData, ? extends SepaViewInterface> getDriver();

  /**
   * display a message on the screen.
   *
   * @param pmessage the message to display
   */
  void showMessage(String pmessage);

  /**
   * set focus on first widget.
   */
  void setFocusOnFirstWidget();

  /**
   * give back this view as widget.
   *
   * @return IsWidget
   */
  IsWidget asWidget();
}

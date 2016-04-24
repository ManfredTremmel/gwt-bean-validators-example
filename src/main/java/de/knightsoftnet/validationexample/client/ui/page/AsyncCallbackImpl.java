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

package de.knightsoftnet.validationexample.client.ui.page;

import de.knightsoftnet.validationexample.client.converter.ValidationResultDataConverter;
import de.knightsoftnet.validationexample.shared.models.ValidationResultData;

import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * Async callback implementation with error handling.
 *
 * @author Manfred Tremmel
 *
 */
public class AsyncCallbackImpl<P, D, V extends EditorWithErrorHandling<P, D>>
    implements AsyncCallback<ValidationResultData> {

  private final V view;
  private final D data;
  private final String errorMessage;
  private final String okMessage;
  private final ValidationResultDataConverter<D> validationConverter;

  /**
   * constructor.
   *
   * @param pview view
   * @param pdata date to handle
   * @param perrorMessage message to display when error occurs
   * @param pokMessage message to display when everything is ok
   */
  public AsyncCallbackImpl(final V pview, final D pdata, final String perrorMessage,
      final String pokMessage) {
    super();
    this.view = pview;
    this.data = pdata;
    this.errorMessage = perrorMessage;
    this.okMessage = pokMessage;
    this.validationConverter = new ValidationResultDataConverter<>();
  }

  @Override
  public void onFailure(final Throwable pcaught) {
    this.view.showMessage(this.errorMessage);
  }

  @Override
  public void onSuccess(final ValidationResultData presult) {
    if (presult == null || presult.getValidationErrorSet() == null
        || presult.getValidationErrorSet().isEmpty()) {
      this.view.showMessage(this.okMessage);
    } else {
      this.view.setConstraintViolations(this.validationConverter.convert(presult, this.data));
    }
  }
}

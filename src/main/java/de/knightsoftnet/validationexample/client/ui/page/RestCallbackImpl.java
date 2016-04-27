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

import de.knightsoftnet.navigation.client.session.Session;

/**
 * Async callback implementation with error handling.
 *
 * @author Manfred Tremmel
 *
 */
public class RestCallbackImpl<P, D, V extends EditorWithErrorHandling<P, D>>
    extends AbstractRestCallback<P, D, V, Boolean> {

  private final String okMessage;

  /**
   * constructor.
   *
   * @param pview view
   * @param pdata date to handle
   * @param pokMessage message to display when everything is ok
   * @param psession session data
   */
  public RestCallbackImpl(final V pview, final D pdata, final String pokMessage,
      final Session psession) {
    super(pview, pdata, psession);
    this.okMessage = pokMessage;
  }

  @Override
  public void onSuccess(final Boolean presult) {
    this.view.showMessage(this.okMessage);
  }
}

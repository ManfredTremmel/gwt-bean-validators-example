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

package de.knightsoftnet.validationexample.client.ui.page.secret;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

/**
 * View of the secret page, gwt implementation.
 *
 * @author Manfred Tremmel
 *
 */
public class SecretViewGwtImpl extends Composite implements SecretViewInterface {

  /**
   * bind ui.
   */
  private static SecretViewUiBinder uiBinder = GWT.create(SecretViewUiBinder.class);

  /**
   * view interface.
   */
  interface SecretViewUiBinder extends UiBinder<Widget, SecretViewGwtImpl> {
  }

  /**
   * reference to the presenter.
   */
  // private SecretPresenterInterface presenter;

  /**
   * default constructor.
   */
  public SecretViewGwtImpl() {
    super();
    this.initWidget(uiBinder.createAndBindUi(this));
  }

  @Override
  public final void setPresenter(final SecretPresenterInterface psecretPresenterInterface) {
    // we don't need it here
    // this.presenter = pSecretPresenterInterface;
  }
}

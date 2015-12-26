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

package de.knightsoftnet.validationexample.client.ui.page.settings;

import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Widget;
import com.gwtplatform.mvp.client.ViewImpl;

import javax.inject.Inject;

/**
 * View of the validator test Settings.
 *
 * @author Manfred Tremmel
 *
 */
public class SettingsViewGwtImpl extends ViewImpl implements SettingsPresenter.MyView {

  interface Binder extends UiBinder<Widget, SettingsViewGwtImpl> {
  }

  private SettingsPresenter presenter;

  /**
   * default constructor.
   */
  @Inject
  public SettingsViewGwtImpl(final Binder puiBinder) {
    super();
    this.initWidget(puiBinder.createAndBindUi(this));
  }

  @Override
  public final void setPresenter(final SettingsPresenter ppresenter) {
    this.presenter = ppresenter;
  }

  /**
   * language changes.
   *
   * @param pchangeEvent change event.
   */
  @UiHandler("language")
  final void onChange(final ValueChangeEvent<String> pchangeEvent) {
    this.presenter.changeLanguage(pchangeEvent.getValue());
  }
}

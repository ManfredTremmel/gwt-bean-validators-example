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

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Widget;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.mvp.client.PopupViewImpl;

import javax.inject.Inject;

/**
 * View of the about page, gwt implementation.
 *
 * @author Manfred Tremmel
 *
 */
public class AboutViewGwtImpl extends PopupViewImpl implements AboutPresenter.MyView {

  interface Binder extends UiBinder<Widget, AboutViewGwtImpl> {
  }

  private AboutPresenter presenter;

  /**
   * constructor with injected parameters.
   *
   * @param puiBinder ui binder
   * @param peventBus event bus
   */
  @Inject
  public AboutViewGwtImpl(final Binder puiBinder, final EventBus peventBus) {
    super(peventBus);
    this.initWidget(puiBinder.createAndBindUi(this));
  }

  @Override
  public final void setPresenter(final AboutPresenter ppresenter) {
    this.presenter = ppresenter;
  }

  /**
   * click on the login button.
   *
   * @param pclickEvent click event.
   */
  @UiHandler("closeButton")
  final void onClick(final ClickEvent pclickEvent) {
    this.presenter.removeFromParentSlot();
  }
}

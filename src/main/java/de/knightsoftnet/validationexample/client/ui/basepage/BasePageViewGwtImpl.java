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

package de.knightsoftnet.validationexample.client.ui.basepage;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.Widget;
import com.gwtplatform.mvp.client.ViewImpl;

import javax.inject.Inject;

/**
 * View of the BasePage.
 *
 * @author Manfred Tremmel
 *
 */
public class BasePageViewGwtImpl extends ViewImpl implements BasePagePresenter.MyView {

  /**
   * view interface.
   */
  interface BasePageViewUiBinder extends UiBinder<Widget, BasePageViewGwtImpl> {
  }

  /**
   * content container.
   */
  @UiField
  SimplePanel container;

  /**
   * navigation.
   */
  @UiField
  SimplePanel navigation;

  /**
   * copyright text should be uses as link to info page.
   */
  @UiField
  HTML info;

  /**
   * remember the presenter.
   */
  private BasePagePresenter presenter;

  /**
   * constructor getting parameters injected.
   *
   * @param puiBinder ui binder
   */
  @Inject
  public BasePageViewGwtImpl(final BasePageViewUiBinder puiBinder) {
    super();

    this.initWidget(puiBinder.createAndBindUi(this));

    this.bindSlot(BasePagePresenter.SLOT_MAIN_CONTENT, this.container);
    this.bindSlot(BasePagePresenter.SLOT_NAVIGATION, this.navigation);
  }

  /**
   * click on the login button.
   *
   * @param pclickEvent click event.
   */
  @UiHandler("info")
  final void showInfos(final ClickEvent pclickEvent) {
    this.presenter.showInfo();
  }

  @Override
  public final void setPresenter(final BasePagePresenter ppresenter) {
    this.presenter = ppresenter;
  }
}

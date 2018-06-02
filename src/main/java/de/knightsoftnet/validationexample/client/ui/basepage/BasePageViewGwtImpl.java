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

import de.knightsoftnet.navigation.client.ui.basepage.AbstractBasePagePresenter;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
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
  interface Binder extends UiBinder<Widget, BasePageViewGwtImpl> {
  }

  /**
   * content container.
   */
  @UiField
  SimplePanel container;

  /**
   * navigation container.
   */
  @UiField
  SimplePanel navigation;

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
  public BasePageViewGwtImpl(final Binder puiBinder) {
    super();

    initWidget(puiBinder.createAndBindUi(this));

    bindSlot(AbstractBasePagePresenter.SLOT_MAIN_CONTENT, container);
    bindSlot(BasePagePresenter.SLOT_NAVIGATION, navigation);
  }

  /**
   * click on the info text.
   *
   * @param pclickEvent click event.
   */
  @UiHandler("info")
  final void showInfos(final ClickEvent pclickEvent) {
    presenter.showInfo();
  }

  @Override
  public final void setPresenter(final BasePagePresenter ppresenter) {
    presenter = ppresenter;
  }
}

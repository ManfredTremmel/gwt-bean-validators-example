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

import de.knightsoftnet.validationexample.client.ClientFactoryInterface;
import de.knightsoftnet.validationexample.client.ui.page.about.AboutViewInterface;

import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.Window;

/**
 * Activity/Presenter of the base page, implementation.
 *
 * @author Manfred Tremmel
 *
 */
public class BasePagePresenterImpl extends AbstractPresenter implements BasePagePresenterInterface {
  /**
   * dialog copyright box.
   */
  private DialogBox dialogCopyrightBox;

  /**
   * the constructor of the activity/presenter.
   *
   * @param pclientFactory client factory
   */
  public BasePagePresenterImpl(final ClientFactoryInterface pclientFactory) {
    super(pclientFactory);
  }

  @Override
  public final void start(final AcceptsOneWidget ppanel, final EventBus peventBus) {
    final BasePageViewInterface basePageView = getClientFactory().getBasePageView();
    basePageView.setPresenter(this);
    final AboutViewInterface aboutPageView = getClientFactory().getAboutView();
    aboutPageView.setPresenter(this);
    ppanel.setWidget(basePageView.asWidget());

    dialogCopyrightBox = new DialogBox();
    dialogCopyrightBox.hide();
    dialogCopyrightBox.setWidget(aboutPageView.asWidget());
  }

  @Override
  public final void goBackToPreviousPage() {
    hideInfo();
  }

  @Override
  public final void showInfo() {
    final int seven = 7;
    final int ten = 10;
    dialogCopyrightBox.setWidth(Integer.toString(Window.getClientWidth() * seven / ten) + "px");
    dialogCopyrightBox.center();
    dialogCopyrightBox.show();
  }

  @Override
  public final void hideInfo() {
    dialogCopyrightBox.hide();
  }

  @Override
  public final String mayStop() {
    // Nothing to do
    return null;
  }

  @Override
  public final void onCancel() {
    // Nothing to do
  }

  @Override
  public final void onStop() {
    // Nothing to do
  }
}

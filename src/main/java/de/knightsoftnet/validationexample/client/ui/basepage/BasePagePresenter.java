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

import de.knightsoftnet.validationexample.client.ui.page.about.AboutPresenterInterface;
import de.knightsoftnet.validationexample.client.ui.page.about.AboutViewInterface;

import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.mvp.client.Presenter;
import com.gwtplatform.mvp.client.annotations.NoGatekeeper;
import com.gwtplatform.mvp.client.annotations.ProxyStandard;
import com.gwtplatform.mvp.client.presenter.slots.NestedSlot;
import com.gwtplatform.mvp.client.proxy.Proxy;

import javax.inject.Inject;

/**
 * Activity/Presenter of the base page, implementation.
 *
 * @author Manfred Tremmel
 *
 */
public class BasePagePresenter extends Presenter<BasePageViewInterface, BasePagePresenter.MyProxy>
    implements AboutPresenterInterface {

  @ProxyStandard
  @NoGatekeeper
  public interface MyProxy extends Proxy<BasePagePresenter> {
  }

  /**
   * dialog copyright box.
   */
  private final DialogBox dialogCopyrightBox;

  /**
   * Use this in leaf presenters, inside their {@link #revealInParent} method.
   */
  public static final NestedSlot SLOT_MAIN_CONTENT = new NestedSlot();

  /**
   * constructor getting parameters injected.
   *
   * @param peventBus event bus
   * @param pview view of this page
   * @param paboutView view of the about page
   * @param pproxy proxy to handle page
   */
  @Inject
  public BasePagePresenter(final EventBus peventBus, final BasePageViewInterface pview,
      final AboutViewInterface paboutView, final MyProxy pproxy) {
    super(peventBus, pview, pproxy, RevealType.Root);
    pview.setPresenter(this);
    paboutView.setPresenter(this);

    this.dialogCopyrightBox = new DialogBox();
    this.dialogCopyrightBox.hide();
    this.dialogCopyrightBox.setWidget(paboutView.asWidget());
  }

  /**
   * hide about info.
   */
  @Override
  public final void goBackToPreviousPage() {
    this.hideInfo();
  }

  /**
   * show info dialog.
   */
  public final void showInfo() {
    this.dialogCopyrightBox.setWidth(Integer.toString(Window.getClientWidth() * 7 / 10) + "px");
    this.dialogCopyrightBox.center();
    this.dialogCopyrightBox.show();
  }

  /**
   * hide info dialog.
   */
  public final void hideInfo() {
    this.dialogCopyrightBox.hide();
  }
}

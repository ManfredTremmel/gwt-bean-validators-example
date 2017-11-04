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
import de.knightsoftnet.navigation.client.ui.navigation.NavigationPresenter;
import de.knightsoftnet.validationexample.client.ui.basepage.about.AboutPresenter;

import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.mvp.client.View;
import com.gwtplatform.mvp.client.annotations.NoGatekeeper;
import com.gwtplatform.mvp.client.annotations.ProxyStandard;
import com.gwtplatform.mvp.client.presenter.slots.PermanentSlot;
import com.gwtplatform.mvp.client.proxy.Proxy;

import javax.inject.Inject;

/**
 * Presenter of the base page, which embeds the other components.
 *
 * @author Manfred Tremmel
 *
 */
public class BasePagePresenter
    extends AbstractBasePagePresenter<BasePagePresenter.MyView, BasePagePresenter.MyProxy> {

  public interface MyView extends View {
    void setPresenter(BasePagePresenter ppresenter);
  }

  @ProxyStandard
  @NoGatekeeper
  public interface MyProxy extends Proxy<BasePagePresenter> {
  }

  public static final PermanentSlot<NavigationPresenter> SLOT_NAVIGATION = new PermanentSlot<>();

  private final NavigationPresenter navigationPresenter;

  private final AboutPresenter aboutPresenter;

  /**
   * constructor getting parameters injected.
   *
   * @param peventBus event bus
   * @param pview view of this page
   * @param paboutPresenter presenter of the about popup
   * @param pproxy proxy to handle page
   * @param pnavigationPresenter navigation presenter
   */
  @Inject
  public BasePagePresenter(final EventBus peventBus, final MyView pview,
      final AboutPresenter paboutPresenter, final MyProxy pproxy,
      final NavigationPresenter pnavigationPresenter) {
    super(peventBus, pview, pproxy);
    this.aboutPresenter = paboutPresenter;
    this.navigationPresenter = pnavigationPresenter;
    pview.setPresenter(this);
  }

  @Override
  protected void onBind() {
    this.setInSlot(BasePagePresenter.SLOT_NAVIGATION, this.navigationPresenter);
  }

  /**
   * show info dialog.
   */
  public final void showInfo() {
    this.addToPopupSlot(this.aboutPresenter);
  }
}

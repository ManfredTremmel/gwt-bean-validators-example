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

package de.knightsoftnet.validationexample.client;

import de.knightsoftnet.validationexample.client.ui.basepage.BasePagePresenterImpl;
import de.knightsoftnet.validationexample.client.ui.basepage.BasePagePresenterInterface;
import de.knightsoftnet.validationexample.client.ui.basepage.BasePageViewInterface;
import de.knightsoftnet.validationexample.client.ui.navigation.NavigationActivityMapper;

import com.google.gwt.activity.shared.ActivityManager;
import com.google.gwt.activity.shared.ActivityMapper;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.HasOneWidget;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.UIObject;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 *
 * @author Manfred Tremmel
 */
public class ValidationExampleGwtImpl extends AbstractValidationExample implements EntryPoint {
  @Override
  protected final void createDisplay(final ClientFactoryInterface pclientFactory) {
    final SimplePanel container = new SimplePanel();
    final BasePagePresenterInterface presenter = new BasePagePresenterImpl(pclientFactory);
    final BasePageViewInterface view = pclientFactory.getBasePageView();

    // set up navigation panel
    final HasOneWidget navContainer = view.getNavigationContainer();
    ((UIObject) navContainer).getElement().setId("nav");
    final ActivityMapper navActivityMapper = new NavigationActivityMapper(pclientFactory);
    final ActivityManager navActivityManager =
        new ActivityManager(navActivityMapper, pclientFactory.getEventBus());
    navActivityManager.setDisplay(navContainer);

    // set up main content panel
    final HasOneWidget mainContainer = view.getContentContainer();
    ((UIObject) mainContainer).getElement().setId("main");
    final AppActivityMapper mainActivityMapper = new AppActivityMapper(pclientFactory);
    final ActivityManager mainActivityManager =
        new ActivityManager(mainActivityMapper, pclientFactory.getEventBus());
    mainActivityManager.setDisplay(mainContainer);

    RootPanel.get().add(container);
    presenter.start(container, null);
  }

  @Override
  protected final ClientFactoryInterface createClientFactory() {
    return new ClientFactoryGwtImpl();
  }
}

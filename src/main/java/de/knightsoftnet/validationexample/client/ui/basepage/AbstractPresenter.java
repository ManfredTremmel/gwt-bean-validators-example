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
import de.knightsoftnet.validationexample.client.ui.page.about.AboutPlace;

import com.google.gwt.activity.shared.AbstractActivity;
import com.google.web.bindery.event.shared.SimpleEventBus;

/**
 * Activity/Presenter, abstract implementation.
 *
 * @author Manfred Tremmel
 *
 */
public abstract class AbstractPresenter extends AbstractActivity implements BasePresenterInterface {
  /**
   * client factory.
   */
  private final ClientFactoryInterface clientFactory;

  /**
   * the constructor of the activity/presenter.
   *
   * @param pclientFactory client factory
   */
  public AbstractPresenter(final ClientFactoryInterface pclientFactory) {
    super();
    clientFactory = pclientFactory;
  }

  @Override
  public final SimpleEventBus getEventBus() {
    return clientFactory.getEventBus();
  }

  @Override
  public final void viewAbout() {
    final AboutPlace aboutPlace =
        new AboutPlace(clientFactory.getPlaceHistoryMapper().getToken(
            clientFactory.getPlaceController().getWhere()));
    clientFactory.getPlaceController().goTo(aboutPlace);
  }

  /**
   * get the client factory.
   *
   * @return the clientFactory
   */
  public final ClientFactoryInterface getClientFactory() {
    return clientFactory;
  }

  @Override
  public final void showNavigation() {
    clientFactory.getPlaceController().goTo(clientFactory.getNavigationPlace());
  }
}

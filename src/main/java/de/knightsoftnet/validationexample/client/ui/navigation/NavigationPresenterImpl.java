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

package de.knightsoftnet.validationexample.client.ui.navigation;

import de.knightsoftnet.validationexample.client.ClientFactoryInterface;
import de.knightsoftnet.validationexample.client.event.ChangeUserEvent;
import de.knightsoftnet.validationexample.client.event.ChangeUserEventHandler;

import com.google.gwt.event.shared.EventBus;
import com.google.gwt.place.shared.Place;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.web.bindery.event.shared.SimpleEventBus;

/**
 * Activity/Presenter of the validator base page.
 *
 * @author Manfred Tremmel
 *
 */
public class NavigationPresenterImpl implements NavigationPresenterInterface {
  /**
   * client factory.
   */
  private final ClientFactoryInterface clientFactory;

  /**
   * place to go to.
   */
  private final NavigationPlace place;

  /**
   * the constructor of the activity/presenter.
   *
   * @param pplace place data to fill with
   * @param pclientFactory client factory
   */
  public NavigationPresenterImpl(final NavigationPlace pplace,
      final ClientFactoryInterface pclientFactory) {
    super();
    this.place = pplace;
    this.clientFactory = pclientFactory;
  }

  @Override
  public final void start(final AcceptsOneWidget ppanel, final EventBus peventBus) {
    final NavigationViewInterface view = this.clientFactory.getNavigationView();
    view.setPresenter(this);
    view.createNavigation(this.place);

    peventBus.addHandler(ChangeUserEvent.getType(), new ChangeUserEventHandler() {
      @Override
      public void onChangeUser(final ChangeUserEvent pevent) {
        NavigationPresenterImpl.this.place.buildVisibleNavigation(pevent.getUser());
        NavigationPresenterImpl.this.place
            .setActiveNavigationEntryInterface(NavigationPresenterImpl.this.place
                .getNavigationForToken(pevent.getPlaceToken()));
        NavigationPresenterImpl.this.clientFactory.getNavigationView().createNavigation(
            NavigationPresenterImpl.this.place);
        NavigationPresenterImpl.this.clientFactory.getPlaceController().goTo(
            NavigationPresenterImpl.this.clientFactory.getPlaceHistoryMapper().getPlace(
                pevent.getPlaceToken()));
      }
    });

    ppanel.setWidget(view.asWidget());
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

  @Override
  public final void goToNavigationEntry(final NavigationEntryInterface pnavigationEntry) {
    if (pnavigationEntry != null && pnavigationEntry.getFullToken() != null
        && pnavigationEntry.isDisplayable(this.clientFactory.getUser())) {
      final Place placeToGo =
          this.clientFactory.getPlaceHistoryMapper().getPlace(pnavigationEntry.getFullToken());
      if (placeToGo != null) {
        this.clientFactory.getPlaceController().goTo(placeToGo);
      }
    }
  }

  @Override
  public final ClientFactoryInterface getClientFactory() {
    return this.clientFactory;
  }

  @Override
  public final SimpleEventBus getEventBus() {
    return this.clientFactory.getEventBus();
  }

  @Override
  public final NavigationPlace getPlace() {
    return this.place;
  }
}

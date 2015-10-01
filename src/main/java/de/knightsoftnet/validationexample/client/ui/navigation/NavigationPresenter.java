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

import de.knightsoftnet.validationexample.client.CurrentSession;
import de.knightsoftnet.validationexample.client.event.ChangePlaceEvent;
import de.knightsoftnet.validationexample.client.event.ChangePlaceEvent.ChangePlaceHandler;
import de.knightsoftnet.validationexample.client.event.ChangeUserEvent;
import de.knightsoftnet.validationexample.client.event.ChangeUserEvent.ChangeUserHandler;

import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.mvp.client.Presenter;
import com.gwtplatform.mvp.client.View;
import com.gwtplatform.mvp.client.annotations.NoGatekeeper;
import com.gwtplatform.mvp.client.annotations.ProxyStandard;
import com.gwtplatform.mvp.client.proxy.PlaceManager;
import com.gwtplatform.mvp.client.proxy.Proxy;
import com.gwtplatform.mvp.shared.proxy.PlaceRequest;

import org.apache.commons.lang3.StringUtils;

/**
 * Activity/Presenter of the navigation page.
 *
 * @author Manfred Tremmel
 * @version $Rev$, $Date$
 *
 */
public class NavigationPresenter extends
    Presenter<NavigationPresenter.MyView, NavigationPresenter.MyProxy> //
    implements ChangeUserHandler, ChangePlaceHandler {

  public interface MyView extends View {
    /**
     * set a reference to the presenter/activity.
     *
     * @param ppresenter reference to set
     */
    void setPresenter(NavigationPresenter ppresenter);

    /**
     * create navigation.
     *
     * @param pplace the place with the navigation data
     */
    void createNavigation(final NavigationPlace pplace);

    /**
     * set new active entry.
     *
     * @param pnewEntry new active entry
     */
    void setSelectedItem(final NavigationEntryInterface pnewEntry);
  }

  @ProxyStandard
  @NoGatekeeper
  public interface MyProxy extends Proxy<NavigationPresenter> {
  }

  private final PlaceManager placeManager;
  private final NavigationPlace navigationPlace;

  /**
   * constructor with injected parameters.
   *
   * @param peventBus event bus
   * @param pview navigation view
   * @param pproxy navigation proxy
   * @param pplaceManager place manager
   * @param pcurrentSession session data
   * @param pnavigationPlace place data
   */
  @Inject
  public NavigationPresenter(final EventBus peventBus, final NavigationPresenter.MyView pview,
      final MyProxy pproxy, final PlaceManager pplaceManager, final CurrentSession pcurrentSession,
      final NavigationPlace pnavigationPlace) {
    super(peventBus, pview, pproxy);
    pview.setPresenter(this);
    this.placeManager = pplaceManager;
    this.navigationPlace = pnavigationPlace;

    peventBus.addHandler(ChangeUserEvent.getType(), this);
    pnavigationPlace.buildVisibleNavigation(null);

    this.getView().createNavigation(this.navigationPlace);

    peventBus.addHandler(ChangePlaceEvent.getType(), this);

    pcurrentSession.readSessionData();
  }

  @Override
  public void onChangeUser(final ChangeUserEvent pevent) {
    if (pevent.getUser() == null || StringUtils.isEmpty(pevent.getUser().getUserName())) {
      if (StringUtils.equals(this.placeManager.getCurrentPlaceRequest().getNameToken(),
          NameTokens.LOGOUT)) {
        final PlaceRequest loginPlaceRequest =
            new PlaceRequest.Builder().nameToken(NameTokens.LOGIN).build();
        this.placeManager.revealPlace(loginPlaceRequest);
      } else {
        this.placeManager.revealCurrentPlace();
      }
    } else {
      if (this.placeManager.getHierarchyDepth() > 1) {
        this.placeManager.revealRelativePlace(-1);
      } else if (StringUtils.equals(this.placeManager.getCurrentPlaceRequest().getNameToken(),
          NameTokens.LOGIN)) {
        this.placeManager.revealDefaultPlace();
      }
    }
    this.navigationPlace.buildVisibleNavigation(pevent.getUser());
    this.getView().createNavigation(this.navigationPlace);
  }

  @Override
  public void onChangePlace(final ChangePlaceEvent pevent) {
    if (pevent != null && StringUtils.isNotEmpty(pevent.getToken())) {
      final NavigationEntryInterface newEntry =
          this.navigationPlace.getNavigationForToken(pevent.getToken());
      if (newEntry == null) {
        return;
      }
      this.getView().setSelectedItem(newEntry);
    }
  }
}

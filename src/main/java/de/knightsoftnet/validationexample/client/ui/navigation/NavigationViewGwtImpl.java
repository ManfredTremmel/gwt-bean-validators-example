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

import de.knightsoftnet.validationexample.client.event.ChangeUserEvent;
import de.knightsoftnet.validationexample.client.event.ChangeUserEventHandler;

import com.google.gwt.event.logical.shared.SelectionEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.InlineHyperlink;
import com.google.gwt.user.client.ui.Tree;
import com.google.gwt.user.client.ui.TreeItem;
import com.google.gwt.user.client.ui.Widget;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.mvp.client.ViewImpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

/**
 * View of the validator Navigation.
 *
 * @author Manfred Tremmel
 *
 */
public class NavigationViewGwtImpl extends ViewImpl implements NavigationViewInterface {

  /**
   * view interface.
   */
  interface NavigationViewUiBinder extends UiBinder<Widget, NavigationViewGwtImpl> {
  }

  /**
   * navigation tree.
   */
  @UiField
  Tree navTree;

  /**
   * navigation tree main entry.
   */
  @UiField
  HTMLPanel navigationMainPoint;

  /**
   * navigation tree test entry.
   */
  @UiField
  TreeItem firstItem;

  /**
   * map between menu entries and navigation.
   */
  private final Map<TreeItem, NavigationEntryInterface> navigationMap;

  /**
   * the selected tree item.
   */
  private TreeItem selectedItem;

  /**
   * constructor with injected parameters.
   *
   * @param peventBus event bus
   * @param puiBinder ui binder
   * @param pplace place
   */
  @Inject
  public NavigationViewGwtImpl(final NavigationViewUiBinder puiBinder, //
      final NavigationPlace pplace, final EventBus peventBus) {
    super();

    this.initWidget(puiBinder.createAndBindUi(this));
    this.navigationMap = new HashMap<TreeItem, NavigationEntryInterface>();
    pplace.buildVisibleNavigation(null);
    this.createNavigation(pplace);
    peventBus.addHandler(ChangeUserEvent.getType(), new ChangeUserEventHandler() {
      @Override
      public void onChangeUser(final ChangeUserEvent pevent) {
        pplace.buildVisibleNavigation(pevent.getUser());
        pplace.setActiveNavigationEntryInterface(pplace.getNavigationForToken(pevent
            .getPlaceToken()));
        NavigationViewGwtImpl.this.createNavigation(pplace);
      }
    });
  }

  @Override
  public final void createNavigation(final NavigationPlace pplace) {
    this.navigationMap.clear();
    this.firstItem.removeItems();
    this.selectedItem = null;
    this.createRecursiveNavigation(this.firstItem, pplace.getFullNavigationList(),
        pplace.getActiveNavigationEntryInterface());
    this.firstItem.setState(true, false);
    if (this.selectedItem != null) {
      this.selectedItem.setSelected(true);
      for (TreeItem openItem = this.selectedItem.getParentItem(); openItem != null; openItem =
          openItem.getParentItem()) {
        openItem.setState(true, false);
      }
    }
  }

  /**
   * create navigation in a recursive way.
   *
   * @param pitem the item to add new items
   * @param plist the list of the navigation entries
   * @param pactiveEntry the active entry
   */
  private void createRecursiveNavigation(final TreeItem pitem,
      final List<NavigationEntryInterface> plist, final NavigationEntryInterface pactiveEntry) {
    for (final NavigationEntryInterface navEntry : plist) {
      final TreeItem newItem;
      if (navEntry instanceof NavigationEntryFolder) {
        newItem = new TreeItem(navEntry.getMenuValue());
        this.createRecursiveNavigation(newItem, ((NavigationEntryFolder) navEntry).getSubEntries(),
            pactiveEntry);
        newItem.setState(navEntry.isOpenOnStartup());
      } else if (navEntry.getToken() == null) {
        newItem = null;
      } else {
        final InlineHyperlink entryPoint =
            new InlineHyperlink(navEntry.getMenuValue(), navEntry.getFullToken());
        newItem = new TreeItem(entryPoint);
        this.navigationMap.put(newItem, navEntry);
      }
      if (newItem != null) {
        pitem.addItem(newItem);
        if (pactiveEntry != null && pactiveEntry.equals(navEntry)) {
          this.selectedItem = newItem;
        }
      }
    }
  }

  /**
   * menu item is selected in the menu tree.
   *
   * @param pselectionEvent selection event.
   */
  @UiHandler("navTree")
  final void menuItemSelected(final SelectionEvent<TreeItem> pselectionEvent) {
    if (this.selectedItem != null && !this.selectedItem.equals(pselectionEvent.getSelectedItem())) {
      // workaround, first selected entry isn't unselected when activating another
      this.selectedItem.setSelected(false);
      this.selectedItem = null;
    }
  }
}

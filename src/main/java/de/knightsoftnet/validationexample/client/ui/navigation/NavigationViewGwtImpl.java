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

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.logical.shared.SelectionEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Tree;
import com.google.gwt.user.client.ui.TreeItem;
import com.google.gwt.user.client.ui.Widget;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * View of the validator Navigation.
 *
 * @author Manfred Tremmel
 *
 */
public class NavigationViewGwtImpl extends Composite implements NavigationViewInterface {

  /**
   * bind ui.
   */
  private static NavigationViewUiBinder uiBinder = GWT.create(NavigationViewUiBinder.class);

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
   * reference to the activity.
   */
  private NavigationPresenterInterface activity;

  /**
   * map between menu entries and navigation.
   */
  private final Map<TreeItem, NavigationEntryInterface> navigationMap;

  /**
   * the selected tree item.
   */
  private TreeItem selectedItem;

  /**
   * default constructor.
   */
  public NavigationViewGwtImpl() {
    super();

    this.initWidget(uiBinder.createAndBindUi(this));
    this.navigationMap = new HashMap<TreeItem, NavigationEntryInterface>();
  }

  @Override
  public final void setPresenter(final NavigationPresenterInterface pnavigationPresenterInterface) {
    this.activity = pnavigationPresenterInterface;
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
        newItem = new TreeItem(navEntry.getMenuValue());
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
    this.activity.goToNavigationEntry(this.navigationMap.get(pselectionEvent.getSelectedItem()));
  }
}

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

import com.google.gwt.event.logical.shared.SelectionEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Tree;
import com.google.gwt.user.client.ui.TreeItem;
import com.google.gwt.user.client.ui.Widget;
import com.gwtplatform.mvp.client.ViewImpl;

import javax.inject.Inject;

/**
 * View of the validator Navigation.
 *
 * @author Manfred Tremmel
 *
 */
public class NavigationViewGwtImpl extends ViewImpl implements NavigationPresenter.MyView {

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
   * the selected tree item.
   */
  private TreeItem selectedItem;

  /**
   * reference to the presenter.
   */
  private NavigationPresenter presenter;

  /**
   * constructor with injected parameters.
   *
   * @param puiBinder ui binder
   */
  @Inject
  public NavigationViewGwtImpl(final NavigationViewUiBinder puiBinder) {
    super();

    this.initWidget(puiBinder.createAndBindUi(this));
  }


  @Override
  public void setPresenter(final NavigationPresenter ppresenter) {
    this.presenter = ppresenter;
  }

  @Override
  public final void createNavigation(final NavigationPlace pplace) {
    this.firstItem.removeItems();
    this.selectedItem = null;
    this.presenter.createRecursiveNavigation(this.firstItem, pplace.getFullNavigationList(),
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


  @Override
  public void setSelectedItem(final TreeItem pnewItem) {
    this.selectedItem = pnewItem;
    this.selectedItem.setSelected(true);
  }
}

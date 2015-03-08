package de.knightsoftnet.validationexample.client.navigation;

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

    initWidget(uiBinder.createAndBindUi(this));
    navigationMap = new HashMap<TreeItem, NavigationEntryInterface>();
  }

  @Override
  public final void setPresenter(final NavigationPresenterInterface pnavigationPresenterInterface) {
    activity = pnavigationPresenterInterface;
  }

  @Override
  public final void createNavigation(final NavigationPlace pplace) {
    navigationMap.clear();
    firstItem.removeItems();
    selectedItem = null;
    createRecursiveNavigation(firstItem, pplace.getFullNavigationList(),
        pplace.getActiveNavigationEntryInterface());
    firstItem.setState(true, false);
    if (selectedItem != null) {
      selectedItem.setSelected(true);
      for (TreeItem openItem = selectedItem.getParentItem(); openItem != null; openItem =
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
        createRecursiveNavigation(newItem, ((NavigationEntryFolder) navEntry).getSubEntries(),
            pactiveEntry);
        newItem.setState(navEntry.isOpenOnStartup());
      } else if (navEntry.getToken() == null) {
        newItem = null;
      } else {
        newItem = new TreeItem(navEntry.getMenuValue());
        navigationMap.put(newItem, navEntry);
      }
      if (newItem != null) {
        pitem.addItem(newItem);
        if (pactiveEntry != null && pactiveEntry.equals(navEntry)) {
          selectedItem = newItem;
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
    if (selectedItem != null && !selectedItem.equals(pselectionEvent.getSelectedItem())) {
      // workaround, first selected entry isn't unselected when activating another
      selectedItem.setSelected(false);
      selectedItem = null;
    }
    activity.goToNavigationEntry(navigationMap.get(pselectionEvent.getSelectedItem()));
  }
}

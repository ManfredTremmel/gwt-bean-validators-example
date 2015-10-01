package de.knightsoftnet.validationexample.client.ui.navigation;

import de.knightsoftnet.validationexample.client.CurrentSession;
import de.knightsoftnet.validationexample.client.event.ChangePlaceEvent;
import de.knightsoftnet.validationexample.client.event.ChangePlaceEvent.ChangePlaceHandler;
import de.knightsoftnet.validationexample.client.event.ChangeUserEvent;
import de.knightsoftnet.validationexample.client.event.ChangeUserEvent.ChangeUserHandler;

import com.google.gwt.user.client.ui.InlineHyperlink;
import com.google.gwt.user.client.ui.TreeItem;
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

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

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

    void setSelectedItem(TreeItem pnewItem);

  }

  @ProxyStandard
  @NoGatekeeper
  public interface MyProxy extends Proxy<NavigationPresenter> {
  }

  /**
   * map between menu entries and navigation.
   */
  private final Map<TreeItem, NavigationEntryInterface> navigationMap;

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
    this.navigationMap = new HashMap<TreeItem, NavigationEntryInterface>();

    peventBus.addHandler(ChangeUserEvent.getType(), this);
    pnavigationPlace.buildVisibleNavigation(null);

    // this.navigationPlace.setActiveNavigationEntryInterface(this.navigationPlace
    // .getNavigationForToken(this.placeManager.getCurrentPlaceRequest().getNameToken()));
    this.navigationMap.clear();
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
      } else {
        this.placeManager.revealDefaultPlace();
      }
    }
    this.navigationPlace.buildVisibleNavigation(pevent.getUser());
    this.navigationMap.clear();
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
      for (final Entry<TreeItem, NavigationEntryInterface> entry : this.navigationMap.entrySet()) {
        entry.getKey().setSelected(newEntry.equals(entry.getValue()));
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
  public void createRecursiveNavigation(final TreeItem pitem,
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
          this.getView().setSelectedItem(newItem);
        }
      }
    }
  }
}

package de.knightsoftnet.validationexample.client.ui.navigation;

import de.knightsoftnet.validationexample.client.ClientFactoryInterface;

import com.google.gwt.activity.shared.Activity;
import com.google.gwt.activity.shared.ActivityMapper;
import com.google.gwt.place.shared.Place;

/**
 * activity manager for the validator test app.
 *
 * @author Manfred Tremmel
 *
 */
public class NavigationActivityMapper implements ActivityMapper {
  /**
   * client factory to remember.
   */
  private final ClientFactoryInterface clientFactory;

  /**
   * default presenter.
   */
  private final NavigationPresenterImpl presenter;

  /**
   * constructor.
   *
   * @param pclientFactory client factory to use
   */
  public NavigationActivityMapper(final ClientFactoryInterface pclientFactory) {
    this.clientFactory = pclientFactory;
    this.presenter =
        new NavigationPresenterImpl((NavigationPlace) this.clientFactory.getNavigationPlace(),
            pclientFactory);
  }

  @Override
  public final Activity getActivity(final Place pplace) {
    final String token = this.clientFactory.getPlaceHistoryMapper().getToken(pplace);
    final NavigationEntryInterface navEntry =
        this.clientFactory.getNavigationPlace().getNavigationForToken(token);
    if (navEntry != null) {
      // remember given parameter as last selected
      navEntry.setTokenDynamic(token);
      this.presenter.getPlace().setActiveNavigationEntryInterface(navEntry);
      if (navEntry.getParentEntry() == null) {
        this.presenter.getPlace().setNavigationList(
            this.presenter.getPlace().getFullNavigationList());
      } else {
        this.presenter.getPlace().setNavigationList(
            ((NavigationEntryFolder) navEntry.getParentEntry()).getSubEntries());
      }
    }
    return this.presenter;
  }
}

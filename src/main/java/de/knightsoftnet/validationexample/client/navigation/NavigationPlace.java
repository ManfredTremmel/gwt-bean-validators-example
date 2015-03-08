package de.knightsoftnet.validationexample.client.navigation;

import com.google.gwt.core.client.GWT;
import com.google.gwt.safehtml.shared.SafeHtmlUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * place for displaying validationexample.
 *
 * @author Manfred Tremmel
 *
 */
public class NavigationPlace extends AbstractNavigationPlace {

  /**
   * constructor initializing app place history mapper.
   *
   * @param pplaceHistoryMapper the app place history mapper to set
   */
  public NavigationPlace(final AppPlaceHistoryMapper pplaceHistoryMapper) {
    super(pplaceHistoryMapper);
  }

  @Override
  protected final List<NavigationEntryInterface> buildNavigation() {
    final List<NavigationEntryInterface> navigationEntries =
        new ArrayList<NavigationEntryInterface>();

    final NavigationConstants navigationConstants = GWT.create(NavigationConstants.class);

    navigationEntries.add(new NavigationEntry(SafeHtmlUtils.fromString(navigationConstants
        .menuPostalAddress()), "address", super.placeHistoryMapper));
    navigationEntries.add(new NavigationEntry(SafeHtmlUtils.fromString(navigationConstants
        .menuSepa()), "sepa", super.placeHistoryMapper));

    navigationEntries.add(new NavigationEntry(SafeHtmlUtils.fromString(navigationConstants
        .menuSettings()), "settings", super.placeHistoryMapper));

    navigationEntries.add(new NavigationEntry(SafeHtmlUtils.fromString(navigationConstants
        .menuLogin()), "login", super.placeHistoryMapper));
    navigationEntries.add(new NavigationEntry(SafeHtmlUtils.fromString(navigationConstants
        .menuLogout()), "logout", super.placeHistoryMapper));

    final NavigationEntryFolder testFolder =
        new NavigationEntryFolder(SafeHtmlUtils.fromString(navigationConstants.menuTestFolder()),
            true);
    testFolder.addSubEntry(new NavigationEntry(SafeHtmlUtils.fromString(navigationConstants
        .menuSecret()), "secret", super.placeHistoryMapper));
    navigationEntries.add(testFolder);

    return navigationEntries;
  }
}

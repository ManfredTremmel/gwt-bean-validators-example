package de.knightsoftnet.validationexample.client.ui.navigation;

import de.knightsoftnet.validationexample.client.mvp.AbstractCustomPlaceHistoryMapper;
import de.knightsoftnet.validationexample.shared.models.UserData;

import com.google.gwt.place.shared.Place;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * The <code>AbstractNavigationPlace</code> defines the methods which are used to handle
 * navigation/menu entries.
 *
 * @author Manfred Tremmel
 */
public abstract class AbstractNavigationPlace extends Place {
  /**
   * place history mapper.
   */
  protected final AppPlaceHistoryMapper placeHistoryMapper;

  /**
   * first place in navigation.
   */
  private NavigationEntryInterface firstEntry;

  /**
   * map to find navigation entries for places.
   */
  private final Map<String, NavigationEntryInterface> placeMap;

  /**
   * list of all navigation entries.
   */
  private List<NavigationEntryInterface> fullNavigationList;

  /**
   * list of active navigation entries (if there are not all displayed).
   */
  private List<NavigationEntryInterface> navigationList;

  /**
   * selected navigation entry.
   */
  private NavigationEntryInterface activeNavigationEntryInterface;

  /**
   * constructor initializing app place history mapper.
   *
   * @param pplaceHistoryMapper the app place history mapper to set
   */
  public AbstractNavigationPlace(final AppPlaceHistoryMapper pplaceHistoryMapper) {
    super();
    placeMap = new HashMap<String, NavigationEntryInterface>();
    placeHistoryMapper = pplaceHistoryMapper;

    buildVisibleNavigation(null);
  }

  /**
   * build the visible navigation entries.
   *
   * @param puser the user to build navigation for
   */
  public final void buildVisibleNavigation(final UserData puser) {
    fullNavigationList = recursiveGetEntries(buildNavigation(), puser);
    generateMapRecursive(fullNavigationList);
    navigationList = fullNavigationList;
    activeNavigationEntryInterface = getFirstEntry();
  }

  /**
   * build the navigation list.
   *
   * @return list of navigation entries
   */
  protected abstract List<NavigationEntryInterface> buildNavigation();

  /**
   * create map out of the navigation list.
   *
   * @param pnavigationEntries list of navigation entries
   */
  private void generateMapRecursive(final List<NavigationEntryInterface> pnavigationEntries) {
    for (final NavigationEntryInterface entryToAdd : pnavigationEntries) {
      if (entryToAdd.getMenuValue() != null && entryToAdd.getToken() != null) {
        if (firstEntry == null && entryToAdd.isDisplayable(null)) {
          firstEntry = entryToAdd;
        }
        if (!placeMap.containsKey(entryToAdd.getToken())) {
          placeMap.put(entryToAdd.getToken(), entryToAdd);
        }
      }
      if (entryToAdd instanceof NavigationEntryFolder) {
        generateMapRecursive(((NavigationEntryFolder) entryToAdd).getSubEntries());
      }
    }
  }

  /**
   * get all navigation entries that can be displayed by a given user.
   *
   * @param pnavigationEntries entries to test
   * @param puser the user to test
   * @return the navigationEntries
   */
  private List<NavigationEntryInterface> recursiveGetEntries(
      final List<NavigationEntryInterface> pnavigationEntries, final UserData puser) {
    if (pnavigationEntries == null) {
      return Collections.emptyList();
    }
    final List<NavigationEntryInterface> displayEntries =
        new ArrayList<NavigationEntryInterface>(pnavigationEntries.size());
    for (final NavigationEntryInterface entryToTest : pnavigationEntries) {
      if (entryToTest.isDisplayable(puser)) {
        if (entryToTest instanceof NavigationEntryFolder) {
          displayEntries.add(new NavigationEntryFolder(entryToTest.getMenuValue(), entryToTest
              .isOpenOnStartup(), recursiveGetEntries(
              ((NavigationEntryFolder) entryToTest).getSubEntries(), puser)));
        } else {
          displayEntries.add(entryToTest);
        }
      }
    }
    return displayEntries;
  }

  /**
   * get first entry.
   *
   * @return the firstEntry
   */
  public final NavigationEntryInterface getFirstEntry() {
    return firstEntry;
  }

  /**
   * get first token.
   *
   * @return the firstPlace
   */
  public final String getFirstToken() {
    return firstEntry.getToken();
  }

  /**
   * get full navigation list.
   *
   * @return the fullNavigationList
   */
  public final List<NavigationEntryInterface> getFullNavigationList() {
    return fullNavigationList;
  }

  /**
   * get navigation list.
   *
   * @return the navigationList
   */
  public final List<NavigationEntryInterface> getNavigationList() {
    return navigationList;
  }

  /**
   * set navigation list.
   *
   * @param pnavigationList the navigationList to set
   */
  public final void setNavigationList(final List<NavigationEntryInterface> pnavigationList) {
    navigationList = pnavigationList;
  }

  /**
   * get active navigation entry interface.
   *
   * @return the activeNavigationEntryInterface
   */
  public final NavigationEntryInterface getActiveNavigationEntryInterface() {
    return activeNavigationEntryInterface;
  }

  /**
   * set active navigation entry interface.
   *
   * @param pactiveNavigationEntryInterface the activeNavigationEntryInterface to set
   */
  public final void setActiveNavigationEntryInterface(
      final NavigationEntryInterface pactiveNavigationEntryInterface) {
    activeNavigationEntryInterface = pactiveNavigationEntryInterface;
  }

  /**
   * get navigation entry for given token.
   *
   * @param ptoken the token of the place to get navigation entry for
   * @return navigation entry for place or null if none found
   */
  public final NavigationEntryInterface getNavigationForToken(final String ptoken) {
    NavigationEntryInterface entry = placeMap.get(ptoken);
    if (entry == null
        && ptoken != null
        && (!ptoken.endsWith(AbstractCustomPlaceHistoryMapper.DELIMITER) || ptoken
            .lastIndexOf(AbstractCustomPlaceHistoryMapper.DELIMITER.charAt(0)) != ptoken
            .indexOf(AbstractCustomPlaceHistoryMapper.DELIMITER.charAt(0)))) {
      final int posSeparator = ptoken.indexOf(AbstractCustomPlaceHistoryMapper.DELIMITER.charAt(0));
      if (posSeparator > 0) {
        entry = placeMap.get(ptoken.substring(0, posSeparator));
      }
    }
    return entry;
  }
}

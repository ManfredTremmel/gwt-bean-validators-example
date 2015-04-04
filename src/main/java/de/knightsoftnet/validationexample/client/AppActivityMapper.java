package de.knightsoftnet.validationexample.client;

import de.knightsoftnet.validationexample.client.ui.navigation.NavigationPlace;
import de.knightsoftnet.validationexample.client.ui.navigation.NavigationPresenterImpl;
import de.knightsoftnet.validationexample.client.ui.page.about.AboutPlace;
import de.knightsoftnet.validationexample.client.ui.page.about.AboutPresenterImpl;
import de.knightsoftnet.validationexample.client.ui.page.address.AddressPlace;
import de.knightsoftnet.validationexample.client.ui.page.address.AddressPresenterImpl;
import de.knightsoftnet.validationexample.client.ui.page.login.LoginPlace;
import de.knightsoftnet.validationexample.client.ui.page.login.LoginPresenterImpl;
import de.knightsoftnet.validationexample.client.ui.page.logout.LogoutPlace;
import de.knightsoftnet.validationexample.client.ui.page.logout.LogoutPresenterImpl;
import de.knightsoftnet.validationexample.client.ui.page.secret.SecretPlace;
import de.knightsoftnet.validationexample.client.ui.page.secret.SecretPresenterImpl;
import de.knightsoftnet.validationexample.client.ui.page.sepa.SepaPlace;
import de.knightsoftnet.validationexample.client.ui.page.sepa.SepaPresenterImpl;
import de.knightsoftnet.validationexample.client.ui.page.settings.SettingsPlace;
import de.knightsoftnet.validationexample.client.ui.page.settings.SettingsPresenterImpl;
import de.knightsoftnet.validationexample.shared.UserRightsInterface;

import com.google.gwt.activity.shared.Activity;
import com.google.gwt.activity.shared.ActivityMapper;
import com.google.gwt.place.shared.Place;

/**
 * activity manager for the validator test app.
 *
 * @author Manfred Tremmel
 *
 */
public class AppActivityMapper implements ActivityMapper {
  /**
   * client factory to remember.
   */
  private final ClientFactoryInterface clientFactory;

  /**
   * constructor.
   *
   * @param pclientFactory client factory to use
   */
  public AppActivityMapper(final ClientFactoryInterface pclientFactory) {
    this.clientFactory = pclientFactory;
  }

  @Override
  public final Activity getActivity(final Place pplace) {
    // check if the user is allowed to see this place
    if (pplace instanceof UserRightsInterface && !(pplace instanceof LogoutPlace)
        && !((UserRightsInterface) pplace).isAllowedToSee(this.clientFactory.getUser())) {
      // not allowed? redirect to login place, if no user is logged in
      final LoginPlace loginPlace =
          new LoginPlace(this.clientFactory.getPlaceHistoryMapper().getToken(pplace));
      if (loginPlace.isAllowedToSee(this.clientFactory.getUser())) {
        this.clientFactory.getPlaceController().goTo(loginPlace);
        return this.getActivity(loginPlace);
      }
      // skip the rest
      return null;
    }
    if (pplace instanceof SettingsPlace) {
      return new SettingsPresenterImpl(this.clientFactory);
    }
    if (pplace instanceof LoginPlace) {
      return new LoginPresenterImpl((LoginPlace) pplace, this.clientFactory);
    }
    if (pplace instanceof LogoutPlace) {
      return new LogoutPresenterImpl(this.clientFactory);
    }
    if (pplace instanceof SecretPlace) {
      return new SecretPresenterImpl(this.clientFactory);
    }
    if (pplace instanceof AboutPlace) {
      return new AboutPresenterImpl((AboutPlace) pplace, this.clientFactory);
    }
    if (pplace instanceof SepaPlace) {
      return new SepaPresenterImpl(this.clientFactory);
    }
    if (pplace instanceof AddressPlace) {
      return new AddressPresenterImpl(this.clientFactory);
    }
    if (pplace instanceof NavigationPlace) {
      return new NavigationPresenterImpl((NavigationPlace) pplace, this.clientFactory);
    }
    return null;
  }
}

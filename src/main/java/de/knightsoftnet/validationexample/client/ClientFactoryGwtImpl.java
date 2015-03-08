package de.knightsoftnet.validationexample.client;

import de.knightsoftnet.validationexample.client.basepage.BasePageViewGwtImpl;
import de.knightsoftnet.validationexample.client.basepage.BasePageViewInterface;
import de.knightsoftnet.validationexample.client.navigation.NavigationViewGwtImpl;
import de.knightsoftnet.validationexample.client.navigation.NavigationViewInterface;
import de.knightsoftnet.validationexample.client.ui.about.AboutViewGwtImpl;
import de.knightsoftnet.validationexample.client.ui.about.AboutViewInterface;
import de.knightsoftnet.validationexample.client.ui.address.AddressViewGwtImpl;
import de.knightsoftnet.validationexample.client.ui.address.AddressViewInterface;
import de.knightsoftnet.validationexample.client.ui.login.LoginViewGwtImpl;
import de.knightsoftnet.validationexample.client.ui.login.LoginViewInterface;
import de.knightsoftnet.validationexample.client.ui.secret.SecretViewGwtImpl;
import de.knightsoftnet.validationexample.client.ui.secret.SecretViewInterface;
import de.knightsoftnet.validationexample.client.ui.sepa.SepaViewGwtImpl;
import de.knightsoftnet.validationexample.client.ui.sepa.SepaViewInterface;
import de.knightsoftnet.validationexample.client.ui.settings.SettingsViewGwtImpl;
import de.knightsoftnet.validationexample.client.ui.settings.SettingsViewInterface;

/**
 * Client factory implementation.
 *
 * @author Manfred Tremmel
 *
 */
public class ClientFactoryGwtImpl extends AbstractClientFactoryImpl implements
    ClientFactoryInterface {
  /**
   * view of the base page.
   */
  private BasePageViewInterface basePageView;

  /**
   * view of the navigation part.
   */
  private NavigationViewInterface navigationView;

  /**
   * settings view.
   */
  private SettingsViewInterface settingsView;

  /**
   * login view.
   */
  private LoginViewInterface loginView;

  /**
   * secret view.
   */
  private SecretViewInterface secretView;

  /**
   * about view.
   */
  private AboutViewInterface aboutView;

  /**
   * sepa view.
   */
  private SepaViewInterface sepaView;

  /**
   * address view.
   */
  private AddressViewInterface addressView;

  @Override
  public final BasePageViewInterface getBasePageView() {
    if (this.basePageView == null) {
      this.basePageView = new BasePageViewGwtImpl();
    }
    return this.basePageView;
  }

  @Override
  public final NavigationViewInterface getNavigationView() {
    if (this.navigationView == null) {
      this.navigationView = new NavigationViewGwtImpl();
    }
    return this.navigationView;
  }

  @Override
  public final SettingsViewInterface getSettingsView() {
    if (this.settingsView == null) {
      this.settingsView = new SettingsViewGwtImpl();
    }
    return this.settingsView;
  }

  @Override
  public final LoginViewInterface getLoginView() {
    if (this.loginView == null) {
      this.loginView = new LoginViewGwtImpl();
    }
    return this.loginView;
  }

  @Override
  public final SecretViewInterface getSecretView() {
    if (this.secretView == null) {
      this.secretView = new SecretViewGwtImpl();
    }
    return this.secretView;
  }

  @Override
  public final AboutViewInterface getAboutView() {
    if (this.aboutView == null) {
      this.aboutView = new AboutViewGwtImpl();
    }
    return this.aboutView;
  }

  @Override
  public final SepaViewInterface getSepaView() {
    if (this.sepaView == null) {
      this.sepaView = new SepaViewGwtImpl();
    }
    return this.sepaView;
  }

  @Override
  public final AddressViewInterface getAddressView() {
    if (this.addressView == null) {
      this.addressView = new AddressViewGwtImpl();
    }
    return this.addressView;
  }
}

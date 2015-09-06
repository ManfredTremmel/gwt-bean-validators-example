package de.knightsoftnet.validationexample.client.injection;


import de.knightsoftnet.validationexample.client.CurrentSession;
import de.knightsoftnet.validationexample.client.ui.basepage.BasePagePresenter;
import de.knightsoftnet.validationexample.client.ui.basepage.BasePageViewGwtImpl;
import de.knightsoftnet.validationexample.client.ui.basepage.BasePageViewInterface;
import de.knightsoftnet.validationexample.client.ui.navigation.NavigationPlace;
import de.knightsoftnet.validationexample.client.ui.navigation.NavigationViewGwtImpl;
import de.knightsoftnet.validationexample.client.ui.navigation.NavigationViewInterface;
import de.knightsoftnet.validationexample.client.ui.page.about.AboutPresenter;
import de.knightsoftnet.validationexample.client.ui.page.about.AboutViewGwtImpl;
import de.knightsoftnet.validationexample.client.ui.page.about.AboutViewInterface;
import de.knightsoftnet.validationexample.client.ui.page.address.AddressPresenter;
import de.knightsoftnet.validationexample.client.ui.page.address.AddressRemoteServiceAsync;
import de.knightsoftnet.validationexample.client.ui.page.address.AddressViewGwtImpl;
import de.knightsoftnet.validationexample.client.ui.page.address.AddressViewInterface;
import de.knightsoftnet.validationexample.client.ui.page.login.LoginLogoutRemoteServiceAsync;
import de.knightsoftnet.validationexample.client.ui.page.login.LoginPresenter;
import de.knightsoftnet.validationexample.client.ui.page.login.LoginViewGwtImpl;
import de.knightsoftnet.validationexample.client.ui.page.login.LoginViewInterface;
import de.knightsoftnet.validationexample.client.ui.page.logout.LogoutPresenter;
import de.knightsoftnet.validationexample.client.ui.page.logout.LogoutViewGwtImpl;
import de.knightsoftnet.validationexample.client.ui.page.logout.LogoutViewInterface;
import de.knightsoftnet.validationexample.client.ui.page.secret.SecretPresenter;
import de.knightsoftnet.validationexample.client.ui.page.secret.SecretViewGwtImpl;
import de.knightsoftnet.validationexample.client.ui.page.secret.SecretViewInterface;
import de.knightsoftnet.validationexample.client.ui.page.sepa.SepaPresenter;
import de.knightsoftnet.validationexample.client.ui.page.sepa.SepaRemoteServiceAsync;
import de.knightsoftnet.validationexample.client.ui.page.sepa.SepaViewGwtImpl;
import de.knightsoftnet.validationexample.client.ui.page.sepa.SepaViewInterface;
import de.knightsoftnet.validationexample.client.ui.page.settings.SettingsPresenter;
import de.knightsoftnet.validationexample.client.ui.page.settings.SettingsViewGwtImpl;
import de.knightsoftnet.validationexample.client.ui.page.settings.SettingsViewInterface;

import com.google.inject.Singleton;
import com.gwtplatform.mvp.client.gin.AbstractPresenterModule;

public class ApplicationModule extends AbstractPresenterModule {

  @Override
  protected void configure() {
    this.bindPresenter(BasePagePresenter.class, BasePageViewInterface.class,
        BasePageViewGwtImpl.class, BasePagePresenter.MyProxy.class);

    this.bind(NavigationViewInterface.class).to(NavigationViewGwtImpl.class).in(Singleton.class);

    this.bindPresenter(AboutPresenter.class, AboutViewInterface.class, AboutViewGwtImpl.class,
        AboutPresenter.MyProxy.class);
    this.bindPresenter(AddressPresenter.class, AddressViewInterface.class,
        AddressViewGwtImpl.class, AddressPresenter.MyProxy.class);
    this.bindPresenter(LoginPresenter.class, LoginViewInterface.class, LoginViewGwtImpl.class,
        LoginPresenter.MyProxy.class);
    this.bindPresenter(LogoutPresenter.class, LogoutViewInterface.class, LogoutViewGwtImpl.class,
        LogoutPresenter.MyProxy.class);
    this.bindPresenter(SecretPresenter.class, SecretViewInterface.class, SecretViewGwtImpl.class,
        SecretPresenter.MyProxy.class);
    this.bindPresenter(SepaPresenter.class, SepaViewInterface.class, SepaViewGwtImpl.class,
        SepaPresenter.MyProxy.class);
    this.bindPresenter(SettingsPresenter.class, SettingsViewInterface.class,
        SettingsViewGwtImpl.class, SettingsPresenter.MyProxy.class);

    this.bind(CurrentSession.class).in(Singleton.class);
    this.bind(NavigationPlace.class).in(Singleton.class);
    this.bind(AddressRemoteServiceAsync.class).in(Singleton.class);
    this.bind(LoginLogoutRemoteServiceAsync.class).in(Singleton.class);
    this.bind(SepaRemoteServiceAsync.class).in(Singleton.class);
  }
}

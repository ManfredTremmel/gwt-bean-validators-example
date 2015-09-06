package de.knightsoftnet.validationexample.client.injection;

import de.knightsoftnet.validationexample.client.ui.navigation.NameTokens;

import com.gwtplatform.mvp.client.gin.AbstractPresenterModule;
import com.gwtplatform.mvp.client.gin.DefaultModule;

public class ClientModule extends AbstractPresenterModule {

  @Override
  protected void configure() {
    this.install(new DefaultModule.Builder().defaultPlace(NameTokens.ADDRESS)
        .errorPlace(NameTokens.LOGIN).unauthorizedPlace(NameTokens.LOGIN).build());

    this.install(new ApplicationModule());
  }
}

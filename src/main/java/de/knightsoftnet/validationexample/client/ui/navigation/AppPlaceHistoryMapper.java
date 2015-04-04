package de.knightsoftnet.validationexample.client.ui.navigation;

import de.knightsoftnet.validationexample.client.ui.page.about.AboutPlace;
import de.knightsoftnet.validationexample.client.ui.page.address.AddressPlace;
import de.knightsoftnet.validationexample.client.ui.page.login.LoginPlace;
import de.knightsoftnet.validationexample.client.ui.page.logout.LogoutPlace;
import de.knightsoftnet.validationexample.client.ui.page.secret.SecretPlace;
import de.knightsoftnet.validationexample.client.ui.page.sepa.SepaPlace;
import de.knightsoftnet.validationexample.client.ui.page.settings.SettingsPlace;

import com.google.gwt.place.shared.PlaceHistoryMapper;
import com.google.gwt.place.shared.WithTokenizers;

/**
 * Application history mapper.
 *
 * @author Manfred Tremmel
 *
 */
@WithTokenizers({SettingsPlace.Tokenizer.class, LoginPlace.Tokenizer.class,
    LogoutPlace.Tokenizer.class, SecretPlace.Tokenizer.class, AboutPlace.Tokenizer.class,
    SepaPlace.Tokenizer.class, AddressPlace.Tokenizer.class})
public interface AppPlaceHistoryMapper extends PlaceHistoryMapper {
  // nothing to do
}

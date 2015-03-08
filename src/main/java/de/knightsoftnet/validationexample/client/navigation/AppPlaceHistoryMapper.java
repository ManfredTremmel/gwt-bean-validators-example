package de.knightsoftnet.validationexample.client.navigation;

import de.knightsoftnet.validationexample.client.ui.about.AboutPlace;
import de.knightsoftnet.validationexample.client.ui.address.AddressPlace;
import de.knightsoftnet.validationexample.client.ui.login.LoginPlace;
import de.knightsoftnet.validationexample.client.ui.logout.LogoutPlace;
import de.knightsoftnet.validationexample.client.ui.secret.SecretPlace;
import de.knightsoftnet.validationexample.client.ui.sepa.SepaPlace;
import de.knightsoftnet.validationexample.client.ui.settings.SettingsPlace;

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

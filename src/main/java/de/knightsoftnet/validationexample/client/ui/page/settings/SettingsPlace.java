package de.knightsoftnet.validationexample.client.ui.page.settings;

import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceTokenizer;
import com.google.gwt.place.shared.Prefix;

/**
 * place for displaying settings.
 *
 * @author Manfred Tremmel
 *
 */
public class SettingsPlace extends Place {
  /**
   * tokenizer for the place.
   *
   * @author Manfred Tremmel
   *
   */
  @Prefix("settings")
  public static class Tokenizer implements PlaceTokenizer<SettingsPlace> {

    @Override
    public final SettingsPlace getPlace(final String token) {
      return new SettingsPlace();
    }

    @Override
    public final String getToken(final SettingsPlace place) {
      return "";
    }
  }
}

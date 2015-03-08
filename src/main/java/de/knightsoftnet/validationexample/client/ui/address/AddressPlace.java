package de.knightsoftnet.validationexample.client.ui.address;

import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceTokenizer;
import com.google.gwt.place.shared.Prefix;

/**
 * place for postal address functionality.
 *
 * @author Manfred Tremmel
 *
 */
public class AddressPlace extends Place {
  /**
   * tokenizer for the place.
   *
   * @author Manfred Tremmel
   *
   */
  @Prefix("address")
  public static class Tokenizer implements PlaceTokenizer<AddressPlace> {

    @Override
    public final AddressPlace getPlace(final String ptoken) {
      return new AddressPlace();
    }

    @Override
    public final String getToken(final AddressPlace pplace) {
      return "";
    }
  }
}

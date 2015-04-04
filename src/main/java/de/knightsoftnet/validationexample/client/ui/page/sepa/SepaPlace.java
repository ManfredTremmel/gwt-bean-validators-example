package de.knightsoftnet.validationexample.client.ui.page.sepa;

import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceTokenizer;
import com.google.gwt.place.shared.Prefix;

/**
 * place for sepa bank account functionality.
 *
 * @author Manfred Tremmel
 *
 */
public class SepaPlace extends Place {
  /**
   * tokenizer for the place.
   *
   * @author Manfred Tremmel
   *
   */
  @Prefix("sepa")
  public static class Tokenizer implements PlaceTokenizer<SepaPlace> {

    @Override
    public final SepaPlace getPlace(final String ptoken) {
      return new SepaPlace();
    }

    @Override
    public final String getToken(final SepaPlace pplace) {
      return "";
    }
  }
}

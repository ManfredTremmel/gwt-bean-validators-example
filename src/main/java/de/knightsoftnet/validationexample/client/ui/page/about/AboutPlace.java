package de.knightsoftnet.validationexample.client.ui.page.about;

import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceTokenizer;
import com.google.gwt.place.shared.Prefix;

/**
 * place for displaying about informations.
 *
 * @author Manfred Tremmel
 *
 */
public class AboutPlace extends Place {
  /**
   * token of the page to redirect after closing about info.
   */
  private String redirectToken;

  /**
   * default constructor.
   */
  public AboutPlace() {
    super();
  }

  /**
   * constructor initializing the fields.
   *
   * @param predirectToken token to set
   */
  public AboutPlace(final String predirectToken) {
    super();
    this.redirectToken = predirectToken;
  }

  /**
   * get redirect token.
   * 
   * @return the redirectToken
   */
  public final String getRedirectToken() {
    return this.redirectToken;
  }

  /**
   * set redirect token.
   * 
   * @param predirectToken the redirectToken to set
   */
  public final void setRedirectToken(final String predirectToken) {
    this.redirectToken = predirectToken;
  }

  /**
   * tokenizer for the place.
   *
   * @author Manfred Tremmel
   *
   */
  @Prefix("about")
  public static class Tokenizer implements PlaceTokenizer<AboutPlace> {

    @Override
    public final AboutPlace getPlace(final String ptoken) {
      return new AboutPlace(ptoken);
    }

    @Override
    public final String getToken(final AboutPlace pplace) {
      if (pplace.getRedirectToken() == null) {
        return "";
      } else {
        return pplace.getRedirectToken();
      }
    }
  }
}

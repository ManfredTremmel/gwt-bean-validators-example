package de.knightsoftnet.validationexample.client.ui.login;

import de.knightsoftnet.validationexample.shared.models.UserData;
import de.knightsoftnet.validationexample.shared.UserRightsInterface;

import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceTokenizer;
import com.google.gwt.place.shared.Prefix;

/**
 * place for login functionality.
 *
 * @author Manfred Tremmel
 *
 */
public class LoginPlace extends Place implements UserRightsInterface {
  /**
   * token of the page to redirect to after login.
   */
  private String redirectToken;

  /**
   * default constructor.
   */
  public LoginPlace() {
    super();
  }

  /**
   * constructor initializing the fields.
   *
   * @param predirectToken token to set
   */
  public LoginPlace(final String predirectToken) {
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

  @Override
  public final boolean isAllowedToSee(final UserData puser) {
    return puser == null || puser.getUserName() == null;
  }

  @Override
  public final boolean isAllowedToChange(final UserData puser) {
    return this.isAllowedToSee(puser);
  }

  /**
   * tokenizer for the place.
   *
   * @author Manfred Tremmel
   *
   */
  @Prefix("login")
  public static class Tokenizer implements PlaceTokenizer<LoginPlace> {

    @Override
    public final LoginPlace getPlace(final String ptoken) {
      return new LoginPlace(ptoken);
    }

    @Override
    public final String getToken(final LoginPlace pplace) {
      if (pplace.getRedirectToken() == null) {
        return "";
      } else {
        return pplace.getRedirectToken();
      }
    }
  }
}

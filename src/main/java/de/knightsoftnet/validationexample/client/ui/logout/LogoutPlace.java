package de.knightsoftnet.validationexample.client.ui.logout;

import de.knightsoftnet.validationexample.shared.models.UserData;
import de.knightsoftnet.validationexample.shared.UserRightsInterface;

import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceTokenizer;
import com.google.gwt.place.shared.Prefix;

import org.apache.commons.lang3.StringUtils;

/**
 * place for logout functionality.
 *
 * @author Manfred Tremmel
 *
 */
public class LogoutPlace extends Place implements UserRightsInterface {

  @Override
  public final boolean isAllowedToSee(final UserData puser) {
    return puser != null && StringUtils.isNotEmpty(puser.getUserName());
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
  @Prefix("logout")
  public static class Tokenizer implements PlaceTokenizer<LogoutPlace> {

    @Override
    public final LogoutPlace getPlace(final String token) {
      return new LogoutPlace();
    }

    @Override
    public final String getToken(final LogoutPlace place) {
      return "";
    }
  }
}

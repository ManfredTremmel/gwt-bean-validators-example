package de.knightsoftnet.validationexample.shared;

import de.knightsoftnet.validationexample.shared.models.UserData;

import org.apache.commons.lang3.StringUtils;

/**
 * The <code>LoggedOutAllowedRights</code> class can be used for entries which are open for every
 * user who's not logged in.
 *
 * @author Manfred Tremmel
 */
public class LoggedOutAllowedRights implements UserRightsInterface {

  @Override
  public final boolean isAllowedToSee(final UserData puser) {
    return puser == null || StringUtils.isEmpty(puser.getUserName());
  }

  @Override
  public final boolean isAllowedToChange(final UserData puser) {
    return isAllowedToSee(puser);
  }
}

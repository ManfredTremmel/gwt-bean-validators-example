package de.knightsoftnet.validationexample.shared;

import de.knightsoftnet.validationexample.shared.models.UserData;

import org.apache.commons.lang3.StringUtils;

/**
 * The <code>LoggedInAllowedRights</code> class can be used for entries which are open for every
 * logged in user.
 *
 * @author Manfred Tremmel
 */
public class LoggedInAllowedRights implements UserRightsInterface {

  @Override
  public final boolean isAllowedToSee(final UserData puser) {
    return puser != null && StringUtils.isNotEmpty(puser.getUserName());
  }

  @Override
  public final boolean isAllowedToChange(final UserData puser) {
    return isAllowedToSee(puser);
  }
}

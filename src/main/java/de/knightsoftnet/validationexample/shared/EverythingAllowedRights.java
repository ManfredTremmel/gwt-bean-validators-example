package de.knightsoftnet.validationexample.shared;

import de.knightsoftnet.validationexample.shared.models.UserData;

/**
 * The <code>EverythingAllowedRights</code> class can be used for entries which are open for
 * everyone.
 *
 * @author Manfred Tremmel
 */
public class EverythingAllowedRights implements UserRightsInterface {

  @Override
  public final boolean isAllowedToSee(final UserData puser) {
    return true;
  }

  @Override
  public final boolean isAllowedToChange(final UserData puser) {
    return true;
  }
}

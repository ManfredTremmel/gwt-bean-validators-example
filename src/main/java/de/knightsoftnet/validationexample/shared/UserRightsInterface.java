package de.knightsoftnet.validationexample.shared;

import de.knightsoftnet.validationexample.shared.models.UserData;

/**
 * The <code>UserRightsInterface</code> interface defines the minimum data for a logged in user.
 *
 * @author Manfred Tremmel
 */
public interface UserRightsInterface {
  /**
   * detect if the user is allowed to see this application.
   *
   * @param puser the user to test
   * @return true if he is allowed
   */
  boolean isAllowedToSee(final UserData puser);

  /**
   * detect if the user is allowed to make changes in this application.
   *
   * @param puser the user to test
   * @return true if he is allowed
   */
  boolean isAllowedToChange(final UserData puser);
}

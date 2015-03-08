package de.knightsoftnet.validationexample.client.event;

import de.knightsoftnet.validationexample.shared.models.UserData;

import com.google.gwt.event.shared.GwtEvent;

/**
 * event for user changes.
 *
 * @author Manfred Tremmel
 *
 */
public class ChangeUserEvent extends GwtEvent<ChangeUserEventHandler> {
  /**
   * type of the event.
   */
  public static final Type<ChangeUserEventHandler> TYPE = new Type<ChangeUserEventHandler>();

  /**
   * user to switch to.
   */
  private UserData user;

  /**
   * place to go after switch.
   */
  private String placeToken;

  /**
   * constructor filling user.
   *
   * @param puser user to set
   * @param pplaceToken a place token to go after user change
   */
  public ChangeUserEvent(final UserData puser, final String pplaceToken) {
    super();
    user = puser;
    placeToken = pplaceToken;
  }

  @Override
  public final Type<ChangeUserEventHandler> getAssociatedType() {
    return TYPE;
  }

  @Override
  protected final void dispatch(final ChangeUserEventHandler phandler) {
    phandler.onChangeUser(this);
  }

  /**
   * static method returning the type.
   *
   * @return the type of the event
   */
  public static Type<ChangeUserEventHandler> getType() {
    return TYPE;
  }

  /**
   * get user.
   *
   * @return the user
   */
  public final UserData getUser() {
    return user;
  }

  /**
   * set user.
   *
   * @param puser the user to set
   */
  public final void setUser(final UserData puser) {
    user = puser;
  }

  /**
   * get place token.
   *
   * @return the placeToken
   */
  public final String getPlaceToken() {
    return placeToken;
  }

  /**
   * set place token.
   *
   * @param pplaceToken the placeToken to set
   */
  public final void setPlaceToken(final String pplaceToken) {
    placeToken = pplaceToken;
  }
}

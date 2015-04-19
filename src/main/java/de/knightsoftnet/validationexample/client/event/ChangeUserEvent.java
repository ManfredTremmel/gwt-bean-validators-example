/*
 * Licensed to the Apache Software Foundation (ASF) under one or more contributor license
 * agreements. See the NOTICE file distributed with this work for additional information regarding
 * copyright ownership. The ASF licenses this file to You under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance with the License. You may obtain a
 * copy of the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */

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

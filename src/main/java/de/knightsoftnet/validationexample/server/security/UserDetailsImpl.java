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

package de.knightsoftnet.validationexample.server.security;

import de.knightsoftnet.validationexample.shared.models.UserData;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

/**
 * user details data. based on the work of https://github.com/imrabti/gwtp-spring-security
 *
 * @author Manfred Tremmel
 */
public class UserDetailsImpl implements UserDetails {
  private static final long serialVersionUID = -1869291730385491771L;

  private UserData user;
  private final List<GrantedAuthority> authorities;

  public UserDetailsImpl(final UserData puser, final List<GrantedAuthority> pauthorities) {
    this.user = puser;
    this.authorities = pauthorities;
  }

  public UserData getUser() {
    return this.user;
  }

  public void setUser(final UserData user) {
    this.user = user;
  }

  @Override
  public String getPassword() {
    return this.user.getPassword();
  }

  @Override
  public String getUsername() {
    return this.user.getUserName();
  }

  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  public boolean isEnabled() {
    return true;
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return this.authorities;
  }
}

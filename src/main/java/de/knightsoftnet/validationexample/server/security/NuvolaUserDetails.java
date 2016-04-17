package de.knightsoftnet.validationexample.server.security;

import de.knightsoftnet.validationexample.shared.models.UserData;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

public class NuvolaUserDetails implements UserDetails {
  private static final long serialVersionUID = -1869291730385491771L;

  private UserData user;
  private final List<GrantedAuthority> authorities;

  public NuvolaUserDetails(final UserData puser, final List<GrantedAuthority> pauthorities) {
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

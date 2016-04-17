package de.knightsoftnet.validationexample.server.security;

import de.knightsoftnet.validationexample.server.service.UserService;
import de.knightsoftnet.validationexample.shared.models.UserData;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class NuvolaUserDetailsService implements UserDetailsService {
  private final UserService userService;

  @Autowired
  NuvolaUserDetailsService(final UserService puserService) {
    this.userService = puserService;
  }

  @Override
  public UserDetails loadUserByUsername(final String pusername) throws UsernameNotFoundException {
    final UserData user = this.userService.getUserByUsername(pusername);
    if (user == null) {
      throw new UsernameNotFoundException(pusername);
    }

    final List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
    final List<String> permissions = this.userService.getPermissions(user.getUserName());
    for (final String permission : permissions) {
      grantedAuthorities.add(new SimpleGrantedAuthority(permission));
    }

    return new NuvolaUserDetails(user, grantedAuthorities);
  }
}

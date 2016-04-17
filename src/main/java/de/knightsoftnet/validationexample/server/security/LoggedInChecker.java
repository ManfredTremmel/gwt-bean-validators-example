package de.knightsoftnet.validationexample.server.security;

import de.knightsoftnet.validationexample.shared.models.UserData;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class LoggedInChecker {
  /**
   * get logged in user.
   * 
   * @return UserData or null if no one is logged in
   */
  public UserData getLoggedInUser() {
    UserData user = null;

    final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    if (authentication != null) {
      final Object principal = authentication.getPrincipal();

      // principal can be "anonymousUser" (String)
      if (principal instanceof NuvolaUserDetails) {
        final NuvolaUserDetails userDetails = (NuvolaUserDetails) principal;
        user = userDetails.getUser();
      }
    }
    return user;
  }
}

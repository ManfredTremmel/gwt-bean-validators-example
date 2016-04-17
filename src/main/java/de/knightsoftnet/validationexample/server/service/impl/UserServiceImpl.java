package de.knightsoftnet.validationexample.server.service.impl;

import de.knightsoftnet.validationexample.server.security.LoggedInChecker;
import de.knightsoftnet.validationexample.server.service.UserService;
import de.knightsoftnet.validationexample.shared.models.GenderEnum;
import de.knightsoftnet.validationexample.shared.models.UserData;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {

  /**
   * map of users, in a real application, users would be in LDAP or a database, but this is only a
   * test application.
   */
  private static final Map<String, UserData> USER_MAP;

  static {
    USER_MAP = new HashMap<String, UserData>();
    USER_MAP.put("test1",
        new UserData("test1", new ShaPasswordEncoder().encodePassword("test1", null), "Herbert",
            "Mustermann", GenderEnum.M));
    USER_MAP.put("test2",
        new UserData("test2", new ShaPasswordEncoder().encodePassword("test2", null), "Maria",
            "Musterfrau", GenderEnum.F));
  }

  private final LoggedInChecker loggedInChecker;

  @Autowired
  UserServiceImpl(final LoggedInChecker ploggedInChecker) {
    this.loggedInChecker = ploggedInChecker;
  }

  @Override
  public UserData getUserByUsername(final String pusername) {
    if (USER_MAP.containsKey(pusername)) {
      return USER_MAP.get(pusername);
    } else {
      return null;
    }
  }

  @Override
  public List<String> getPermissions(final String pusername) {
    return new ArrayList<>();
  }

  @Override
  public UserData getCurrentUser() {
    return this.loggedInChecker.getLoggedInUser();
  }

  @Override
  public Boolean isCurrentUserLoggedIn() {
    return this.loggedInChecker.getLoggedInUser() != null;
  }
}

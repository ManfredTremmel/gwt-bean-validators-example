package de.knightsoftnet.validationexample.server.service;

import de.knightsoftnet.validationexample.shared.models.UserData;

import java.util.List;

public interface UserService {
  UserData getUserByUsername(String pusername);

  List<String> getPermissions(String pusername);

  UserData getCurrentUser();

  Boolean isCurrentUserLoggedIn();
}

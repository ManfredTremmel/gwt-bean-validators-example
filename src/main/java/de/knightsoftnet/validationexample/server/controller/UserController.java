package de.knightsoftnet.validationexample.server.controller;

import static org.springframework.http.ResponseEntity.ok;

import de.knightsoftnet.validationexample.server.service.UserService;
import de.knightsoftnet.validationexample.shared.ResourcePaths.User;
import de.knightsoftnet.validationexample.shared.models.UserData;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.security.PermitAll;

@RestController
@RequestMapping(value = User.ROOT, produces = MediaType.APPLICATION_JSON_VALUE)
public class UserController {
  private final UserService userService;

  @Autowired
  UserController(final UserService puserService) {
    this.userService = puserService;
  }

  @RequestMapping(value = User.LOGIN, method = RequestMethod.GET)
  @PermitAll
  ResponseEntity<Boolean> isCurrentUserLoggedIn() {
    return new ResponseEntity<>(this.userService.isCurrentUserLoggedIn(), HttpStatus.OK);
  }

  @RequestMapping(method = RequestMethod.GET)
  ResponseEntity<UserData> getCurrentUser() {
    return ok(this.userService.getCurrentUser());
  }
}

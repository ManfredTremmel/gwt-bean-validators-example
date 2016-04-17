package de.knightsoftnet.validationexample.server.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class HttpLogoutSuccessHandler implements LogoutSuccessHandler {
  @Override
  public void onLogoutSuccess(final HttpServletRequest request, final HttpServletResponse response,
      final Authentication authentication) throws IOException {
    response.setStatus(HttpServletResponse.SC_OK);
    response.getWriter().flush();
  }
}

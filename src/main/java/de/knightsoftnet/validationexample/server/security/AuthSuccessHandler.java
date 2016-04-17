package de.knightsoftnet.validationexample.server.security;

import de.knightsoftnet.validationexample.shared.models.UserData;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class AuthSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {
  private static final Logger LOGGER = LoggerFactory.getLogger(AuthSuccessHandler.class);

  private final ObjectMapper mapper;

  @Autowired
  AuthSuccessHandler(final MappingJackson2HttpMessageConverter messageConverter) {
    super();
    this.mapper = messageConverter.getObjectMapper();
  }

  @Override
  public void onAuthenticationSuccess(final HttpServletRequest request,
      final HttpServletResponse response, final Authentication authentication)
      throws IOException, ServletException {
    response.setStatus(HttpServletResponse.SC_OK);

    final NuvolaUserDetails userDetails = (NuvolaUserDetails) authentication.getPrincipal();
    final UserData user = userDetails.getUser();
    userDetails.setUser(user);

    LOGGER.info(userDetails.getUsername() + " got is connected ");

    final PrintWriter writer = response.getWriter();
    this.mapper.writeValue(writer, user);
    writer.flush();
  }
}

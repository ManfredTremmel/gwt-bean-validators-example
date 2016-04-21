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

/**
 * authentication success handler for gwt applications. based on the work of
 * https://github.com/imrabti/gwtp-spring-security
 *
 * @author Manfred Tremmel
 */
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

    final UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
    final UserData user = userDetails.getUser();
    userDetails.setUser(user);

    LOGGER.info(userDetails.getUsername() + " got is connected ");

    final PrintWriter writer = response.getWriter();
    this.mapper.writeValue(writer, user);
    writer.flush();
  }
}

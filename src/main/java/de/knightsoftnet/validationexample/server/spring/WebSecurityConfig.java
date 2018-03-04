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

package de.knightsoftnet.validationexample.server.spring;

import de.knightsoftnet.validationexample.client.ui.navigation.NameTokens;
import de.knightsoftnet.validationexample.shared.AppResourcePaths;
import de.knightsoftnet.validators.server.security.AuthFailureHandler;
import de.knightsoftnet.validators.server.security.AuthSuccessHandler;
import de.knightsoftnet.validators.server.security.CsrfCookieHandler;
import de.knightsoftnet.validators.server.security.HttpAuthenticationEntryPoint;
import de.knightsoftnet.validators.server.security.HttpLogoutSuccessHandler;
import de.knightsoftnet.validators.shared.Parameters;
import de.knightsoftnet.validators.shared.ResourcePaths;
import de.knightsoftnet.validators.shared.ResourcePaths.PhoneNumber;
import de.knightsoftnet.validators.shared.ResourcePaths.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.GlobalAuthenticationConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.csrf.CsrfFilter;
import org.springframework.security.web.csrf.CsrfTokenRepository;
import org.springframework.security.web.csrf.HttpSessionCsrfTokenRepository;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * configuration for spring security.
 *
 * @author Manfred Tremmel
 */
@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

  private static final String LOGIN_PATH = User.ROOT + User.LOGIN;

  @Autowired
  private HttpAuthenticationEntryPoint authenticationEntryPoint;
  @Autowired
  private AuthSuccessHandler authSuccessHandler;
  @Autowired
  private AuthFailureHandler authFailureHandler;
  @Autowired
  private HttpLogoutSuccessHandler logoutSuccessHandler;
  @Autowired
  private CsrfCookieHandler csrfCookieHandler;

  /**
   * configure urls which are ignored by security.
   *
   * @param pweb web security config
   */
  @Override
  public void configure(final WebSecurity pweb) throws Exception { // NOPMD
    pweb.ignoring() //
        .antMatchers("/", //
            "/index.html", //
            "/favicon.ico", //
            NameTokens.LOGIN, //
            NameTokens.LOGOUT, //
            NameTokens.SEPA, //
            NameTokens.ADDRESS, //
            NameTokens.EMAIL_LIST, //
            NameTokens.PERSON, //
            NameTokens.PERSON + "**", //
            NameTokens.PHONE_NUMBER, //
            NameTokens.SECRET, //
            NameTokens.SETTINGS, //
            "/gwtBeanValidatorsExample/**");
  }

  /**
   * configure security settings.
   *
   * @param phttp http security
   */
  @Override
  protected void configure(final HttpSecurity phttp) throws Exception { // NOPMD

    // csrf/xsrf protection
    phttp.csrf().csrfTokenRepository(this.csrfTokenRepository()) //
        .and().addFilterAfter(this.csrfHeaderFilter(), CsrfFilter.class) //
        .authorizeRequests() //

        // services without authentication
        .antMatchers(AppResourcePaths.PHONE_NUMBER, //
            AppResourcePaths.POSTAL_ADDRESS, //
            AppResourcePaths.EMAIL_LIST, //
            AppResourcePaths.SEPA, //
            PhoneNumber.ROOT + "/**") //
        .permitAll() //

        // all other requests need authentication
        .anyRequest().authenticated() //

        // handle not allowed access, delegate to authentication entry poing
        .and() //
        .exceptionHandling() //
        .authenticationEntryPoint(this.authenticationEntryPoint) //

        // login form handling
        .and() //
        .formLogin() //
        .permitAll().loginProcessingUrl(LOGIN_PATH) //
        .usernameParameter(Parameters.USERNAME) //
        .passwordParameter(Parameters.PASSWORD) //
        .successHandler(this.authSuccessHandler) //
        .failureHandler(this.authFailureHandler) //

        .and().logout().permitAll() //
        .logoutRequestMatcher(new AntPathRequestMatcher(LOGIN_PATH, "DELETE")) //
        .logoutSuccessHandler(this.logoutSuccessHandler) //

        .and() //
        .sessionManagement().maximumSessions(1);
  }

  @Configuration
  protected static class AuthenticationConfiguration extends GlobalAuthenticationConfigurerAdapter {

    @Override
    public void init(final AuthenticationManagerBuilder auth) throws Exception { // NOPMD
      auth.ldapAuthentication().userDnPatterns("uid={0},ou=people").groupSearchBase("ou=groups")
          .contextSource().ldif("classpath:test-server.ldif");
    }
  }

  private Filter csrfHeaderFilter() {
    return new OncePerRequestFilter() {
      @Override
      protected void doFilterInternal(final HttpServletRequest prequest,
          final HttpServletResponse presponse, final FilterChain pfilterChain)
          throws ServletException, IOException {
        WebSecurityConfig.this.csrfCookieHandler.setCookie(prequest, presponse);
        pfilterChain.doFilter(prequest, presponse);
      }
    };
  }

  /**
   * define csrf header entry.
   *
   * @return csrf header entry
   */
  @Bean
  public CsrfTokenRepository csrfTokenRepository() {
    final HttpSessionCsrfTokenRepository repository = new HttpSessionCsrfTokenRepository();
    repository.setHeaderName(ResourcePaths.XSRF_HEADER);
    return repository;
  }
}

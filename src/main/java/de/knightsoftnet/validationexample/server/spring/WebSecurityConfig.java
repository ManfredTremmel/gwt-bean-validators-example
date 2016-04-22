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

import de.knightsoftnet.validationexample.server.security.AuthFailureHandler;
import de.knightsoftnet.validationexample.server.security.AuthSuccessHandler;
import de.knightsoftnet.validationexample.server.security.HttpAuthenticationEntryPoint;
import de.knightsoftnet.validationexample.server.security.HttpLogoutSuccessHandler;
import de.knightsoftnet.validationexample.server.security.UserDetailsServiceImpl;
import de.knightsoftnet.validationexample.shared.Parameters;
import de.knightsoftnet.validationexample.shared.ResourcePaths;
import de.knightsoftnet.validationexample.shared.ResourcePaths.PhoneNumber;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

/**
 * configuration for spring security.
 *
 * @author Manfred Tremmel
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true)
@ComponentScan(value = "de.knightsoftnet.**.security")
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
  private static final String LOGIN_PATH = ResourcePaths.User.ROOT + ResourcePaths.User.LOGIN;

  @Autowired
  private UserDetailsServiceImpl userDetailsService;
  @Autowired
  private HttpAuthenticationEntryPoint authenticationEntryPoint;
  @Autowired
  private AuthSuccessHandler authSuccessHandler;
  @Autowired
  private AuthFailureHandler authFailureHandler;
  @Autowired
  private HttpLogoutSuccessHandler logoutSuccessHandler;

  @Bean
  @Override
  public AuthenticationManager authenticationManagerBean() throws Exception { // NOPMD
    return super.authenticationManagerBean();
  }

  @Bean
  @Override
  public UserDetailsService userDetailsServiceBean() throws Exception { // NOPMD
    return super.userDetailsServiceBean();
  }

  /**
   * authentication provider.
   *
   * @return the authentication provider
   */
  @Bean
  public AuthenticationProvider authenticationProvider() {
    final DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
    authenticationProvider.setUserDetailsService(this.userDetailsService);
    authenticationProvider.setPasswordEncoder(new ShaPasswordEncoder());

    return authenticationProvider;
  }

  @Override
  protected AuthenticationManager authenticationManager() throws Exception { // NOPMD
    return super.authenticationManager();
  }

  @Override
  protected void configure(final AuthenticationManagerBuilder auth) throws Exception { // NOPMD
    auth.authenticationProvider(this.authenticationProvider());
  }

  /**
   * configure security settings.
   *
   * @param phttp http security
   */
  @Override
  protected void configure(final HttpSecurity phttp) throws Exception { // NOPMD
    phttp.csrf().disable() //
        .authorizeRequests() //
        .antMatchers("/").permitAll() //
        .antMatchers("/gwtBeanValidatorsExample/**").permitAll() //
        .antMatchers("/favicon.ico").permitAll() //
        .antMatchers("/index.html").permitAll() //
        .antMatchers("/validationexample/gwtBeanValidatorsExample/**").permitAll() //
        .antMatchers("/validationexample/favicon.ico").permitAll() //
        .antMatchers("/validationexample/index.html").permitAll() //
        .antMatchers(PhoneNumber.ROOT + "/**").permitAll() //
        .antMatchers(ResourcePaths.PHONE_NUMBER).permitAll() //
        .antMatchers(ResourcePaths.POSTAL_ADDRESS).permitAll() //
        .antMatchers(ResourcePaths.SEPA).permitAll() //
        .anyRequest().authenticated() //
        .and().authenticationProvider(this.authenticationProvider()) //
        .exceptionHandling() //
        .authenticationEntryPoint(this.authenticationEntryPoint) //
        .and().formLogin() //
        .permitAll().loginProcessingUrl(LOGIN_PATH) //
        .usernameParameter(Parameters.USERNAME) //
        .passwordParameter(Parameters.PASSWORD) //
        .successHandler(this.authSuccessHandler) //
        .failureHandler(this.authFailureHandler) //
        .and().logout().permitAll() //
        .logoutRequestMatcher(new AntPathRequestMatcher(LOGIN_PATH, "DELETE")) //
        .logoutSuccessHandler(this.logoutSuccessHandler) //
        .and().sessionManagement() //
        .maximumSessions(1);

    phttp.authorizeRequests().anyRequest().authenticated();
  }
}

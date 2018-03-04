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

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.tuckey.web.filters.urlrewrite.UrlRewriteFilter;

/**
 * configuration of the url filter, it's used to redirect all pushstate urls (which don't realy
 * exists) to the index.html page.
 *
 * @author Manfred Tremmel
 */
@Configuration
public class FilterRegistrationConfig {

  /**
   * register filter bean.
   *
   * @return FilterRegistrationBean
   */
  @Bean
  public FilterRegistrationBean<UrlRewriteFilter> filterRegistrationBean() {
    final FilterRegistrationBean<UrlRewriteFilter> registrationBean =
        new FilterRegistrationBean<>();

    registrationBean.setFilter(new UrlRewriteFilter());
    registrationBean.addUrlPatterns("*");
    registrationBean.addInitParameter("confReloadCheckInterval", "5");
    registrationBean.addInitParameter("logLevel", "INFO");

    return registrationBean;
  }
}

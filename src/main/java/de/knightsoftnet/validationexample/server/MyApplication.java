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

package de.knightsoftnet.validationexample.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * Main method for spring boot start.
 *
 * @author Manfred Tremmel
 */
@SpringBootApplication
@EntityScan("de.knightsoftnet.validationexample.shared.models")
@ComponentScan(basePackages = {"de.knightsoftnet.validationexample.shared.models",
    "de.knightsoftnet.validationexample.server", "de.knightsoftnet.validators.server"})
@EnableJpaRepositories(basePackages = {"de.knightsoftnet.validationexample.shared.models",
    "de.knightsoftnet.validationexample.server.repository"})
public class MyApplication {

  public static void main(final String[] args) {
    SpringApplication.run(MyApplication.class, args);
  }
}

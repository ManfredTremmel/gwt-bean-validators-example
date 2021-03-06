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

package de.knightsoftnet.validationexample.client.gin;

import de.knightsoftnet.navigation.client.OwnPlaceManagerImpl;
import de.knightsoftnet.validationexample.client.resources.ResourceLoader;
import de.knightsoftnet.validationexample.client.ui.navigation.NameTokens;
import de.knightsoftnet.validators.client.gin.ServiceModule;

import com.gwtplatform.mvp.client.gin.AbstractPresenterModule;
import com.gwtplatform.mvp.client.gin.DefaultModule;
import com.gwtplatform.mvp.shared.proxy.OwnRouteTokenFormatter;

public class ClientModule extends AbstractPresenterModule {

  @Override
  protected void configure() {
    install(new DefaultModule.Builder() //
        .tokenFormatter(OwnRouteTokenFormatter.class) //
        .placeManager(OwnPlaceManagerImpl.class) //
        .defaultPlace(NameTokens.ADDRESS) //
        .errorPlace(NameTokens.LOGIN) //
        .unauthorizedPlace(NameTokens.LOGIN) //
        .build());

    install(new ApplicationModule());
    install(new ServiceModule());

    bind(ResourceLoader.class).asEagerSingleton();
  }
}

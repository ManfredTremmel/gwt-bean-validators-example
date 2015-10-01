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

package de.knightsoftnet.validationexample.client.injection;

import de.knightsoftnet.validationexample.client.OwnPlaceManagerImpl;
import de.knightsoftnet.validationexample.client.ui.navigation.NameTokens;

import com.gwtplatform.mvp.client.gin.AbstractPresenterModule;
import com.gwtplatform.mvp.client.gin.DefaultModule;

public class ClientModule extends AbstractPresenterModule {

  @Override
  protected void configure() {
    this.install(new DefaultModule.Builder().placeManager(OwnPlaceManagerImpl.class)
        .defaultPlace(NameTokens.ADDRESS).errorPlace(NameTokens.LOGIN)
        .unauthorizedPlace(NameTokens.LOGIN).build());

    this.install(new ApplicationModule());
  }
}

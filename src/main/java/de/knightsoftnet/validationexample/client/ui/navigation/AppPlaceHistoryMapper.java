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

package de.knightsoftnet.validationexample.client.ui.navigation;

import de.knightsoftnet.validationexample.client.ui.page.about.AboutPlace;
import de.knightsoftnet.validationexample.client.ui.page.address.AddressPlace;
import de.knightsoftnet.validationexample.client.ui.page.login.LoginPlace;
import de.knightsoftnet.validationexample.client.ui.page.logout.LogoutPlace;
import de.knightsoftnet.validationexample.client.ui.page.secret.SecretPlace;
import de.knightsoftnet.validationexample.client.ui.page.sepa.SepaPlace;
import de.knightsoftnet.validationexample.client.ui.page.settings.SettingsPlace;

import com.google.gwt.place.shared.PlaceHistoryMapper;
import com.google.gwt.place.shared.WithTokenizers;

/**
 * Application history mapper.
 *
 * @author Manfred Tremmel
 *
 */
@WithTokenizers({SettingsPlace.Tokenizer.class, LoginPlace.Tokenizer.class,
    LogoutPlace.Tokenizer.class, SecretPlace.Tokenizer.class, AboutPlace.Tokenizer.class,
    SepaPlace.Tokenizer.class, AddressPlace.Tokenizer.class})
public interface AppPlaceHistoryMapper extends PlaceHistoryMapper {
  // nothing to do
}

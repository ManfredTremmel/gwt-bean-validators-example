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

import com.google.gwt.i18n.client.Constants;

/**
 * Localized values for the settings ui.
 *
 * @author Manfred Tremmel
 *
 */
public interface NavigationConstants extends Constants {
  /**
   * text of the navigation button.
   *
   * @return button
   */
  String button();

  /**
   * text of the header line.
   *
   * @return header
   */
  String header();

  /**
   * error message displayed when loading data failes.
   *
   * @return errorLoading
   */
  String errorLoading();

  /**
   * text of the test menu entry.
   *
   * @return test
   */
  String menuTest();

  /**
   * text of the settings menu entry.
   *
   * @return settings
   */
  String menuSettings();

  /**
   * text of the login menu entry.
   *
   * @return login
   */
  String menuLogin();

  /**
   * text of the logout menu entry.
   *
   * @return logout
   */
  String menuLogout();

  /**
   * text of the test folder menu entry.
   *
   * @return testFolder
   */
  String menuTestFolder();

  /**
   * text of the secret menu entry.
   *
   * @return secret
   */
  String menuSecret();

  /**
   * text of the sepa menu entry.
   *
   * @return sepa
   */
  String menuSepa();

  /**
   * text of the postal address menu entry.
   *
   * @return postalAddress
   */
  String menuPostalAddress();

  /**
   * text of the phone number menu entry.
   *
   * @return phoneNumber
   */
  String menuPhoneNumber();

  /**
   * text of the person menu entry.
   *
   * @return person
   */
  String menuPerson();
}

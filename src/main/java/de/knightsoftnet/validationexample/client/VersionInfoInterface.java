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

package de.knightsoftnet.validationexample.client;

import com.google.gwt.safehtml.shared.SafeHtml;

/**
 * The implementation of the <code>VersionInfoInterface</code> should provide version information
 * for the application.
 *
 * @author Manfred Tremmel
 */
public interface VersionInfoInterface {

  /**
   * get the copyright text.
   *
   * @return copyright text
   */
  SafeHtml getCopyrightText();

  /**
   * get the version number.
   *
   * @return version number
   */
  SafeHtml getVersionNumber();

  /**
   * get the version date.
   *
   * @return version date
   */
  SafeHtml getVersionDate();

  /**
   * get the author.
   *
   * @return author
   */
  SafeHtml getAuthor();

}

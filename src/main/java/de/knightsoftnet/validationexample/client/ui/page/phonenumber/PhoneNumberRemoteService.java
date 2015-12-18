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

package de.knightsoftnet.validationexample.client.ui.page.phonenumber;

import de.knightsoftnet.validationexample.shared.models.PhoneNumberData;
import de.knightsoftnet.validators.shared.exceptions.ValidationException;

import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.google.gwt.user.client.rpc.XsrfProtectedService;
import com.google.gwt.user.server.rpc.NoXsrfProtect;

/**
 * Definition of the sepa remote services.
 *
 * @author Manfred Tremmel
 */
@RemoteServiceRelativePath("phonenumber")
public interface PhoneNumberRemoteService extends XsrfProtectedService {
  /**
   * send phone number data.
   *
   * @param pphoneNumberData phone number data
   * @return PhoneNumberData
   * @throws ValidationException if login data are not valid
   */
  @NoXsrfProtect
  PhoneNumberData sendPhoneNumber(PhoneNumberData pphoneNumberData) throws ValidationException;
}

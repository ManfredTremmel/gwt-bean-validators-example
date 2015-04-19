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

import de.knightsoftnet.validationexample.client.ui.page.address.AddressRemoteService;
import de.knightsoftnet.validationexample.shared.models.PostalAddressData;
import de.knightsoftnet.validators.shared.exceptions.ValidationException;

import com.google.gwt.user.server.rpc.XsrfProtectedServiceServlet;

import java.util.Set;

import javax.servlet.annotation.WebServlet;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;

/**
 * Definition of the postal address remote services.
 *
 * @author Manfred Tremmel
 */
@WebServlet(urlPatterns = {"/gwtBeanValidatorsExample/postaladdress"})
public class PostalAddressServiceServlet extends XsrfProtectedServiceServlet implements
    AddressRemoteService {

  /**
   * serial version unique id.
   */
  private static final long serialVersionUID = -2875140338699149401L;


  @Override
  public PostalAddressData sendPostalAddress(final PostalAddressData ppostalAddressData)
      throws ValidationException {
    // validate input data, don't trust the client validation
    final Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

    final Set<ConstraintViolation<PostalAddressData>> cv1 = validator.validate(ppostalAddressData);

    // no error, fine ;-)
    if (cv1.isEmpty()) {
      // do the job
    } else {
      throw new ValidationException(cv1);
    }
    return ppostalAddressData;
  }
}

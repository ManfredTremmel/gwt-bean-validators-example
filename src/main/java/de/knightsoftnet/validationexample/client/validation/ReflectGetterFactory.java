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

package de.knightsoftnet.validationexample.client.validation;

import de.knightsoftnet.validationexample.shared.models.LoginData;
import de.knightsoftnet.validationexample.shared.models.PhoneNumberData;
import de.knightsoftnet.validationexample.shared.models.PostalAddressData;
import de.knightsoftnet.validationexample.shared.models.SepaData;
import de.knightsoftnet.validators.client.AbstractGwtReflectGetterFactory;
import de.knightsoftnet.validators.client.GwtReflectGetterInterface;

import com.google.gwt.core.client.GWT;
import com.google.gwt.validation.client.GwtValidation;

/**
 * The <code>ReflectGetterFactory</code> class is used for client side reflector replacement of the
 * getters. All beans which need reflection like access to the getters, have to be added to
 * {@link GwtValidation} annotation.
 *
 * @author Manfred Tremmel
 *
 */
public class ReflectGetterFactory extends AbstractGwtReflectGetterFactory {

  /**
   * Validator marker for the bean validator example project. Only the classes and groups listed in
   * the {@link GwtValidation} annotation can be reflected.
   */
  @GwtValidation(
      value = {LoginData.class, SepaData.class, PostalAddressData.class, PhoneNumberData.class})
  public interface GwtGetReflector extends GwtReflectGetterInterface {
  }

  @Override
  public GwtReflectGetterInterface createGwtReflectGetter() {
    return GWT.create(GwtGetReflector.class);
  }
}

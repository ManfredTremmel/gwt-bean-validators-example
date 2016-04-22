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

package de.knightsoftnet.validationexample.server.converter.custom;

import de.knightsoftnet.validationexample.shared.models.ValidationDto;
import de.knightsoftnet.validationexample.shared.models.ValidationResultData;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

/**
 * convert binding results produced by spring bean validation to validation result data which can be
 * sent to client.
 *
 * @author Manfred Tremmel
 */
@Component
public class BindingResult2ValidationResultDataConverter
    implements Converter<BindingResult, ValidationResultData> {

  @Override
  public ValidationResultData convert(final BindingResult psource) {
    final ValidationResultData validationResult = new ValidationResultData();

    if (psource != null && psource.hasErrors()) {
      for (final FieldError error : psource.getFieldErrors()) {
        final ValidationDto validationDto = new ValidationDto();
        validationDto.setPropertyPath(error.getField());
        validationDto.setMessage(error.getDefaultMessage());
        validationResult.getValidationErrorSet().add(validationDto);
      }
    }
    return validationResult;
  }

}

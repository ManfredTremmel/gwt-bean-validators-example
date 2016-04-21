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

package de.knightsoftnet.validationexample.server.controller;

import de.knightsoftnet.validationexample.converter.custom.BindingResult2ValidationResultDataConverter;
import de.knightsoftnet.validationexample.shared.ResourcePaths;
import de.knightsoftnet.validationexample.shared.models.SepaData;
import de.knightsoftnet.validationexample.shared.models.ValidationResultData;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.security.PermitAll;
import javax.validation.Valid;

/**
 * Sepa web service.
 *
 * @author Manfred Tremmel
 */
@RestController
@RequestMapping(value = ResourcePaths.SEPA, produces = MediaType.APPLICATION_JSON_VALUE)
public class SepaController {

  private static final Logger LOG = Logger.getLogger(SepaController.class);

  @Autowired
  private BindingResult2ValidationResultDataConverter bindingResult2ValidationResultDataConverter;

  /**
   * web service takes data, logs it and validates it, validation result is returned to caller.
   *
   * @param psepaData data from client
   * @param pbindingResult validation result
   * @return validation result for client, should contain a empty list if
   */
  @RequestMapping(method = RequestMethod.POST)
  @PermitAll
  public ValidationResultData checkSepa(@Valid @RequestBody final SepaData psepaData,
      final BindingResult pbindingResult) {

    LOG.info(psepaData);

    if (!pbindingResult.hasErrors()) {
      // do something usefull
    }

    return this.bindingResult2ValidationResultDataConverter.convert(pbindingResult);
  }
}

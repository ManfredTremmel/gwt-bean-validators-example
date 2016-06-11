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

import de.knightsoftnet.validationexample.server.persistence.dao.PersonSpecificationsBuilder;
import de.knightsoftnet.validationexample.server.repository.PersonRepository;
import de.knightsoftnet.validationexample.shared.AppResourcePaths;
import de.knightsoftnet.validationexample.shared.models.Person;
import de.knightsoftnet.validationexample.shared.search.SearchOperation;
import de.knightsoftnet.validators.shared.Parameters;

import com.google.common.base.Joiner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * controller implements search functionality.
 *
 * @author Manfred Tremmel
 */
@Controller
public class PersonController {

  @Autowired
  private PersonRepository dao;

  /**
   * search for persons.
   *
   * @param psearch search parameter string
   * @return list of found persons
   */
  @RequestMapping(method = RequestMethod.GET, value = AppResourcePaths.PERSON_SEARCH)
  @ResponseBody
  public List<Person> findAllBySpecification(
      @RequestParam(value = Parameters.SEARCH) final String psearch) {
    final PersonSpecificationsBuilder builder = new PersonSpecificationsBuilder();
    final String operationSetExper = Joiner.on("|").join(SearchOperation.SIMPLE_OPERATION_SET);
    final Pattern pattern =
        Pattern.compile("(\\w+?)(" + operationSetExper + ")(\\p{Punct}?)(\\w+?)(\\p{Punct}?),");
    final Matcher matcher = pattern.matcher(psearch + ",");
    while (matcher.find()) {
      builder.with(matcher.group(1), matcher.group(2), matcher.group(4), matcher.group(3),
          matcher.group(5));
    }

    final Specification<Person> spec = builder.build();
    return this.dao.findAll(spec);
  }
}

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

package de.knightsoftnet.validationexample.server.repository;

import de.knightsoftnet.validationexample.shared.AppResourcePaths;
import de.knightsoftnet.validationexample.shared.models.Person;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

/**
 * person repository, used by spring data to create rest service for person data.
 *
 * @author Manfred Tremmel
 */
@RepositoryRestResource(collectionResourceRel = AppResourcePaths.PERSON_SUFFIX,
    path = AppResourcePaths.PERSON_SUFFIX)
public interface PersonRepository
    extends PagingAndSortingRepository<Person, Long>, JpaSpecificationExecutor<Person> {
}

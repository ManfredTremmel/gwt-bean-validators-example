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

package de.knightsoftnet.validationexample.client.services;

import de.knightsoftnet.validationexample.shared.Parameters;
import de.knightsoftnet.validationexample.shared.ResourcePaths;
import de.knightsoftnet.validationexample.shared.models.UserData;

import com.gwtplatform.dispatch.rest.shared.RestAction;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;

/**
 * Definition of the login/logout remote services.
 *
 * @author Manfred Tremmel
 */
@Path(ResourcePaths.User.ROOT)
public interface UserRestService {

  @POST
  @Path(ResourcePaths.User.LOGIN)
  @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
  RestAction<UserData> login(@FormParam(Parameters.USERNAME) String username,
      @FormParam(Parameters.PASSWORD) String password);

  @DELETE
  @Path(ResourcePaths.User.LOGIN)
  RestAction<Void> logout();

  @GET
  @Path(ResourcePaths.User.LOGIN)
  RestAction<Boolean> isCurrentUserLoggedIn();

  @GET
  RestAction<UserData> getCurrentUser();
}
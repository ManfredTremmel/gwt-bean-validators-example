package de.knightsoftnet.validationexample.client.gin;

import de.knightsoftnet.validationexample.shared.ResourcePaths;

import com.google.gwt.core.client.GWT;
import com.google.gwt.inject.client.AbstractGinModule;
import com.google.inject.Provides;
import com.gwtplatform.dispatch.rest.client.RestApplicationPath;
import com.gwtplatform.dispatch.rest.client.gin.RestDispatchAsyncModule;
import com.gwtplatform.dispatch.shared.SecurityCookie;

public class ServiceModule extends AbstractGinModule {
  @Override
  protected void configure() {
    this.install(new RestDispatchAsyncModule.Builder()
        .xsrfTokenHeaderName(ResourcePaths.XSRF_HEADER).build());

    this.bindConstant().annotatedWith(SecurityCookie.class).to(ResourcePaths.XSRF_COOKIE);
  }

  @Provides
  @RestApplicationPath
  String getApplicationPath() {
    String baseUrl = GWT.getHostPageBaseURL();
    if (baseUrl.endsWith("/")) {
      baseUrl = baseUrl.substring(0, baseUrl.length() - 1);
    }

    return baseUrl;
  }
}

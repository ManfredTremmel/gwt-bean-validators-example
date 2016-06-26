package de.knightsoftnet.validationexample.client.gin;

import de.knightsoftnet.validators.shared.ResourcePaths;

import com.google.gwt.core.client.GWT;
import com.google.gwt.inject.client.AbstractGinModule;
import com.google.inject.Provides;
import com.gwtplatform.dispatch.rest.client.RestApplicationPath;
import com.gwtplatform.dispatch.rest.client.gin.HalRestDispatchAsyncModule;
import com.gwtplatform.dispatch.shared.SecurityCookie;

import org.apache.commons.lang3.StringUtils;

public class ServiceModule extends AbstractGinModule {
  @Override
  protected void configure() {
    this.install(new HalRestDispatchAsyncModule.Builder()
        .xsrfTokenHeaderName(ResourcePaths.XSRF_HEADER).build());

    this.bindConstant().annotatedWith(SecurityCookie.class).to(ResourcePaths.XSRF_COOKIE);
  }

  @Provides
  @RestApplicationPath
  String getApplicationPath() {
    return StringUtils.removeEnd(StringUtils
        .removeEnd(StringUtils.removeEnd(GWT.getModuleBaseURL(), "/"), GWT.getModuleName()), "/");
  }
}

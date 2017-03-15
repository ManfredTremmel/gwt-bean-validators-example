package de.knightsoftnet.validationexample.client.resources;

import com.google.gwt.debug.client.DebugInfo;

import org.apache.commons.lang3.StringUtils;

import javax.inject.Inject;

public class ResourceLoader {
  @Inject
  ResourceLoader(final ResourcesFile appResources) {
    appResources.grid().ensureInjected();
    DebugInfo.setDebugIdPrefix(StringUtils.EMPTY);
  }
}

package de.knightsoftnet.validationexample.client.resources;

import javax.inject.Inject;

public class ResourceLoader {
  @Inject
  ResourceLoader(final ResourcesFile appResources) {
    appResources.grid().ensureInjected();
  }
}

package de.knightsoftnet.validationexample.client;

import de.knightsoftnet.validationexample.shared.ResourcePaths;

import com.gwtplatform.mvp.client.PreBootstrapper;
import com.wallissoftware.pushstate.client.PushStateHistorian;

public class MyPreBootstrapper implements PreBootstrapper {
  @Override
  public void onPreBootstrap() {
    PushStateHistorian.setRelativePath(ResourcePaths.BASE_DIR);
  }
}

package de.knightsoftnet.validationexample.client;

import com.gwtplatform.mvp.client.PreBootstrapper;
import com.wallissoftware.pushstate.client.PushStateHistorian;

public class MyPreBootstrapper implements PreBootstrapper {
  @Override
  public void onPreBootstrap() {
    PushStateHistorian.setRelativePath("/validationexample");
  }
}

package de.knightsoftnet.validationexample.client;

import de.knightsoftnet.navigation.client.version.AbstractVersionInfo;

import com.google.gwt.core.shared.GWT;
import com.gwtplatform.mvp.client.PreBootstrapper;
import com.wallissoftware.pushstate.client.PushStateHistorian;

public class MyPreBootstrapper implements PreBootstrapper {
  @Override
  public void onPreBootstrap() {
    final AbstractVersionInfo versionInfo = GWT.create(AbstractVersionInfo.class);
    PushStateHistorian.setRelativePath("/" + versionInfo.getContextRoot());
  }
}

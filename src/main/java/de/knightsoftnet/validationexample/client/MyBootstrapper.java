package de.knightsoftnet.validationexample.client;

import de.knightsoftnet.validationexample.client.services.UserRestService;
import de.knightsoftnet.validationexample.shared.models.UserData;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.gwtplatform.dispatch.rest.client.RestDispatch;
import com.gwtplatform.mvp.client.Bootstrapper;

import javax.inject.Inject;

public class MyBootstrapper implements Bootstrapper {

  private final CurrentSession currentSession;
  private final RestDispatch dispatcher;
  private final UserRestService userService;

  /**
   * constructor injecting data.
   *
   * @param pdispatcher rest dispatcher for calling rest services
   * @param puserService user service to read user
   * @param pcurrentSession session data
   */
  @Inject
  public MyBootstrapper(final RestDispatch pdispatcher, final UserRestService puserService,
      final CurrentSession pcurrentSession) {
    super();
    this.dispatcher = pdispatcher;
    this.userService = puserService;
    this.currentSession = pcurrentSession;
  }

  @Override
  public void onBootstrap() {
    this.readSessionData();
  }

  private void readSessionData() {
    this.dispatcher.execute(this.userService.getCurrentUser(), new AsyncCallback<UserData>() {
      @Override
      public void onFailure(final Throwable pcaught) {
        GWT.log("Error reading session user", pcaught);
      }

      @Override
      public void onSuccess(final UserData presult) {
        MyBootstrapper.this.currentSession.setUser(presult);
      }
    });
  }
}

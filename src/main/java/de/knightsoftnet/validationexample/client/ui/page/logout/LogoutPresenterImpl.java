package de.knightsoftnet.validationexample.client.ui.page.logout;

import de.knightsoftnet.validationexample.client.ClientFactoryInterface;
import de.knightsoftnet.validationexample.client.ui.basepage.AbstractPresenter;

import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.AcceptsOneWidget;

/**
 * Activity/Presenter of the logout, implementation.
 *
 * @author Manfred Tremmel
 *
 */
public class LogoutPresenterImpl extends AbstractPresenter implements LogoutPresenterInterface {
  /**
   * the constructor of the activity/presenter.
   *
   * @param pclientFactory client factory
   */
  public LogoutPresenterImpl(final ClientFactoryInterface pclientFactory) {
    super(pclientFactory);
  }

  @Override
  public final void start(final AcceptsOneWidget ppanel, final EventBus peventBus) {
    // removed user and go to home screen
    this.getClientFactory().getLoginLogoutService().logout(new AsyncCallback<Void>() {

      @Override
      public void onFailure(final Throwable pcaught) {
        // we've logged out, it doesn't matter!
      }

      @Override
      public void onSuccess(final Void presult) {
        // we've logged out, it doesn't matter!
      }

    });
    this.getClientFactory().setUser(null,
        this.getClientFactory().getNavigationPlace().getFirstToken());
  }

  @Override
  public final String mayStop() {
    // ignore this
    return null;
  }

  @Override
  public final void onCancel() {
    // ignore this
  }

  @Override
  public final void onStop() {
    // ignore this
  }
}

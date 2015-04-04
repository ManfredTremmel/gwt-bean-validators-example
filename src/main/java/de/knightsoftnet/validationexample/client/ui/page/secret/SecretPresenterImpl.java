package de.knightsoftnet.validationexample.client.ui.page.secret;

import de.knightsoftnet.validationexample.client.ClientFactoryInterface;
import de.knightsoftnet.validationexample.client.JsHelper;
import de.knightsoftnet.validationexample.client.ui.basepage.AbstractPresenter;

import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.RunAsyncCallback;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.ui.AcceptsOneWidget;

/**
 * Activity/Presenter of the secret page, implementation.
 *
 * @author Manfred Tremmel
 *
 */
public class SecretPresenterImpl extends AbstractPresenter implements SecretPresenterInterface {
  /**
   * the constructor of the activity/presenter.
   *
   * @param pclientFactory client factory
   */
  public SecretPresenterImpl(final ClientFactoryInterface pclientFactory) {
    super(pclientFactory);
  }

  @Override
  public final void start(final AcceptsOneWidget ppanel, final EventBus pcventBus) {
    GWT.runAsync(new RunAsyncCallback() {

      @Override
      public void onSuccess() {
        final SecretViewInterface view =
            SecretPresenterImpl.this.getClientFactory().getSecretView();
        view.setPresenter(SecretPresenterImpl.this);
        ppanel.setWidget(view.asWidget());
      }

      @Override
      public void onFailure(final Throwable preason) {
        JsHelper.forceReload();
      }
    });
  }

  @Override
  public final String mayStop() {
    // Nothing to do
    return null;
  }

  @Override
  public final void onCancel() {
    // Nothing to do
  }

  @Override
  public final void onStop() {
    // Nothing to do
  }
}

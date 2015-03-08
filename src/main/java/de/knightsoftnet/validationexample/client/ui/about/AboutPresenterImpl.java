package de.knightsoftnet.validationexample.client.ui.about;

import de.knightsoftnet.validationexample.client.basepage.AbstractPresenter;
import de.knightsoftnet.validationexample.client.ClientFactoryInterface;
import de.knightsoftnet.validationexample.client.JsHelper;

import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.RunAsyncCallback;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.ui.AcceptsOneWidget;

/**
 * Activity/Presenter of the about page, implementation.
 *
 * @author Manfred Tremmel
 *
 */
public class AboutPresenterImpl extends AbstractPresenter implements AboutPresenterInterface {

  /**
   * place to remember.
   */
  private final AboutPlace place;

  /**
   * the constructor of the activity/presenter.
   *
   * @param pplace the place representing this page
   * @param pclientFactory client factory
   */
  public AboutPresenterImpl(final AboutPlace pplace, final ClientFactoryInterface pclientFactory) {
    super(pclientFactory);
    this.place = pplace;
  }

  @Override
  public final void start(final AcceptsOneWidget ppanel, final EventBus peventBus) {
    GWT.runAsync(new RunAsyncCallback() {

      @Override
      public void onSuccess() {
        final AboutViewInterface view = AboutPresenterImpl.this.getClientFactory().getAboutView();
        view.setPresenter(AboutPresenterImpl.this);

        ppanel.setWidget(view.asWidget());
      }

      @Override
      public void onFailure(final Throwable preason) {
        JsHelper.forceReload();
      }
    });
  }

  @Override
  public final void goBackToPreviousPage() {
    if (this.place != null) {
      this.getClientFactory()
          .getPlaceController()
          .goTo(
              this.getClientFactory().getPlaceHistoryMapper()
                  .getPlace(this.place.getRedirectToken()));
    }
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

package de.knightsoftnet.validationexample.client.ui.basepage;

import de.knightsoftnet.validationexample.client.ClientFactoryInterface;
import de.knightsoftnet.validationexample.client.ui.page.about.AboutViewInterface;

import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.Window;

/**
 * Activity/Presenter of the base page, implementation.
 *
 * @author Manfred Tremmel
 *
 */
public class BasePagePresenterImpl extends AbstractPresenter implements BasePagePresenterInterface {
  /**
   * dialog copyright box.
   */
  private DialogBox dialogCopyrightBox;

  /**
   * the constructor of the activity/presenter.
   *
   * @param pclientFactory client factory
   */
  public BasePagePresenterImpl(final ClientFactoryInterface pclientFactory) {
    super(pclientFactory);
  }

  @Override
  public final void start(final AcceptsOneWidget ppanel, final EventBus peventBus) {
    final BasePageViewInterface basePageView = getClientFactory().getBasePageView();
    basePageView.setPresenter(this);
    final AboutViewInterface aboutPageView = getClientFactory().getAboutView();
    aboutPageView.setPresenter(this);
    ppanel.setWidget(basePageView.asWidget());

    dialogCopyrightBox = new DialogBox();
    dialogCopyrightBox.hide();
    dialogCopyrightBox.setWidget(aboutPageView.asWidget());
  }

  @Override
  public final void goBackToPreviousPage() {
    hideInfo();
  }

  @Override
  public final void showInfo() {
    final int seven = 7;
    final int ten = 10;
    dialogCopyrightBox.setWidth(Integer.toString(Window.getClientWidth() * seven / ten) + "px");
    dialogCopyrightBox.center();
    dialogCopyrightBox.show();
  }

  @Override
  public final void hideInfo() {
    dialogCopyrightBox.hide();
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

package de.knightsoftnet.validationexample.client.ui.basepage;

import de.knightsoftnet.validationexample.client.ClientFactoryInterface;
import de.knightsoftnet.validationexample.client.ui.page.about.AboutPlace;

import com.google.gwt.activity.shared.AbstractActivity;
import com.google.web.bindery.event.shared.SimpleEventBus;

/**
 * Activity/Presenter, abstract implementation.
 *
 * @author Manfred Tremmel
 *
 */
public abstract class AbstractPresenter extends AbstractActivity implements BasePresenterInterface {
  /**
   * client factory.
   */
  private final ClientFactoryInterface clientFactory;

  /**
   * the constructor of the activity/presenter.
   *
   * @param pclientFactory client factory
   */
  public AbstractPresenter(final ClientFactoryInterface pclientFactory) {
    super();
    clientFactory = pclientFactory;
  }

  @Override
  public final SimpleEventBus getEventBus() {
    return clientFactory.getEventBus();
  }

  @Override
  public final void viewAbout() {
    final AboutPlace aboutPlace =
        new AboutPlace(clientFactory.getPlaceHistoryMapper().getToken(
            clientFactory.getPlaceController().getWhere()));
    clientFactory.getPlaceController().goTo(aboutPlace);
  }

  /**
   * get the client factory.
   *
   * @return the clientFactory
   */
  public final ClientFactoryInterface getClientFactory() {
    return clientFactory;
  }

  @Override
  public final void showNavigation() {
    clientFactory.getPlaceController().goTo(clientFactory.getNavigationPlace());
  }
}

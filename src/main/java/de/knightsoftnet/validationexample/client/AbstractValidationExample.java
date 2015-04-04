package de.knightsoftnet.validationexample.client;

import de.knightsoftnet.validationexample.client.ui.navigation.AppPlaceHistoryMapper;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.GWT.UncaughtExceptionHandler;
import com.google.gwt.place.shared.PlaceHistoryHandler;
import com.google.gwt.place.shared.PlaceHistoryHandler.Historian;
import com.google.gwt.user.client.Window;
import com.wallissoftware.pushstate.client.PushStateHistorian;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 *
 * @author Manfred Tremmel
 */
public abstract class AbstractValidationExample implements EntryPoint {
  /**
   * create the application display.
   *
   * @param pclientFactory Client Factory
   */
  protected abstract void createDisplay(final ClientFactoryInterface pclientFactory);

  /**
   * create the client factory.
   *
   * @return ClientFactoryInterface
   */
  protected abstract ClientFactoryInterface createClientFactory();

  /**
   * This is the entry point method.
   */
  @Override
  public final void onModuleLoad() {
    GWT.setUncaughtExceptionHandler(new UncaughtExceptionHandler() {
      @Override
      public void onUncaughtException(final Throwable exeption) {
        Window.alert("uncaught: " + exeption.getMessage());
        exeption.printStackTrace();
      }
    });

    Window.enableScrolling(false);
    Window.setMargin("0px");

    final ClientFactoryInterface clientFactory = this.createClientFactory();

    final Historian historian = GWT.create(Historian.class);
    PushStateHistorian.setRelativePath("/validationexample");

    // Start PlaceHistoryHandler with our PlaceHistoryMapper
    final AppPlaceHistoryMapper historyMapper = clientFactory.getPlaceHistoryMapper();
    final PlaceHistoryHandler historyHandler = new PlaceHistoryHandler(historyMapper, historian);

    historyHandler.register(clientFactory.getPlaceController(), clientFactory.getEventBus(),
        historyMapper.getPlace(clientFactory.getNavigationPlace().getFirstToken()));

    this.createDisplay(clientFactory);
    historyHandler.handleCurrentHistory();
  }
}

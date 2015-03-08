package de.knightsoftnet.validationexample.client;

import de.knightsoftnet.validationexample.client.basepage.BasePagePresenterImpl;
import de.knightsoftnet.validationexample.client.basepage.BasePagePresenterInterface;
import de.knightsoftnet.validationexample.client.basepage.BasePageViewInterface;
import de.knightsoftnet.validationexample.client.navigation.NavigationActivityMapper;

import com.google.gwt.activity.shared.ActivityManager;
import com.google.gwt.activity.shared.ActivityMapper;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.HasOneWidget;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.UIObject;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 *
 * @author Manfred Tremmel
 */
public class ValidationExampleGwtImpl extends AbstractValidationExample implements EntryPoint {
  @Override
  protected final void createDisplay(final ClientFactoryInterface pclientFactory) {
    final SimplePanel container = new SimplePanel();
    final BasePagePresenterInterface presenter = new BasePagePresenterImpl(pclientFactory);
    final BasePageViewInterface view = pclientFactory.getBasePageView();

    // set up navigation panel
    final HasOneWidget navContainer = view.getNavigationContainer();
    ((UIObject) navContainer).getElement().setId("nav");
    final ActivityMapper navActivityMapper = new NavigationActivityMapper(pclientFactory);
    final ActivityManager navActivityManager =
        new ActivityManager(navActivityMapper, pclientFactory.getEventBus());
    navActivityManager.setDisplay(navContainer);

    // set up main content panel
    final HasOneWidget mainContainer = view.getContentContainer();
    ((UIObject) mainContainer).getElement().setId("main");
    final AppActivityMapper mainActivityMapper = new AppActivityMapper(pclientFactory);
    final ActivityManager mainActivityManager =
        new ActivityManager(mainActivityMapper, pclientFactory.getEventBus());
    mainActivityManager.setDisplay(mainContainer);

    RootPanel.get().add(container);
    presenter.start(container, null);
  }

  @Override
  protected final ClientFactoryInterface createClientFactory() {
    return new ClientFactoryGwtImpl();
  }
}

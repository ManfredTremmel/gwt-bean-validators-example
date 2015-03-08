package de.knightsoftnet.validationexample.client;

import de.knightsoftnet.validationexample.client.event.ChangeUserEvent;
import de.knightsoftnet.validationexample.client.mvp.AbstractCustomPlaceHistoryMapper;
import de.knightsoftnet.validationexample.client.navigation.AbstractNavigationPlace;
import de.knightsoftnet.validationexample.client.navigation.AppPlaceHistoryMapper;
import de.knightsoftnet.validationexample.client.navigation.NavigationPlace;
import de.knightsoftnet.validationexample.client.ui.address.AddressRemoteService;
import de.knightsoftnet.validationexample.client.ui.address.AddressRemoteServiceAsync;
import de.knightsoftnet.validationexample.client.ui.login.LoginLogoutRemoteService;
import de.knightsoftnet.validationexample.client.ui.login.LoginLogoutRemoteServiceAsync;
import de.knightsoftnet.validationexample.client.ui.sepa.SepaRemoteService;
import de.knightsoftnet.validationexample.client.ui.sepa.SepaRemoteServiceAsync;
import de.knightsoftnet.validationexample.shared.models.UserData;

import com.google.gwt.core.client.GWT;
import com.google.gwt.http.client.URL;
import com.google.gwt.place.shared.PlaceController;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.HasRpcToken;
import com.google.gwt.user.client.rpc.ServiceDefTarget;
import com.google.gwt.user.client.rpc.XsrfToken;
import com.google.gwt.user.client.rpc.XsrfTokenService;
import com.google.gwt.user.client.rpc.XsrfTokenServiceAsync;
import com.google.gwt.user.client.Window;
import com.google.web.bindery.event.shared.SimpleEventBus;

import org.apache.commons.lang3.StringUtils;

/**
 * Client factory implementation.
 *
 * @author Manfred Tremmel
 *
 */
public abstract class AbstractClientFactoryImpl implements ClientFactoryInterface {
  /**
   * event bus.
   */
  private final SimpleEventBus eventBus;

  /**
   * place controller.
   */
  private final PlaceController placeController;

  /**
   * history mapper.
   */
  private final AppPlaceHistoryMapper placeHistoryMapper;

  /**
   * navigation place.
   */
  private final AbstractNavigationPlace navigationPlace;

  /**
   * Cross-Site Request Forgery protection token.
   */
  private XsrfToken xsrfToken;

  /**
   * logged in user or null if no user is logged in.
   */
  private UserData user;

  /**
   * the login/logout service.
   */
  private LoginLogoutRemoteServiceAsync loginLogoutService;

  /**
   * the sepa service.
   */
  private SepaRemoteServiceAsync sepaService;

  /**
   * the address service.
   */
  private AddressRemoteServiceAsync addressService;

  /**
   * default constructor.
   */
  public AbstractClientFactoryImpl() {
    super();
    this.eventBus = new SimpleEventBus();
    this.placeController = new PlaceController(this.eventBus);
    this.placeHistoryMapper = GWT.create(AppPlaceHistoryMapper.class);
    this.navigationPlace = new NavigationPlace(this.placeHistoryMapper);

    // get page token
    final String urlString = Window.Location.getHref();
    final String pageToken;
    if (urlString.indexOf('#') >= 0) {
      pageToken = URL.decode(urlString.substring(urlString.indexOf('#') + 1));
    } else if (urlString.indexOf(AbstractCustomPlaceHistoryMapper.DELIMITER) >= 0) {
      pageToken =
          URL.decode(urlString.substring(urlString
              .indexOf(AbstractCustomPlaceHistoryMapper.DELIMITER) + 1));
    } else {
      pageToken = null;
    }

    // create a session and a xsrf protection token
    this.loginLogoutService = GWT.create(LoginLogoutRemoteService.class);
    this.loginLogoutService.createSession(new AsyncCallback<Void>() {
      @Override
      public void onFailure(final Throwable pcaught) {
        throw new RuntimeException(pcaught);
      }

      @Override
      public void onSuccess(final Void presult) {
        final XsrfTokenServiceAsync xsrf =
            (XsrfTokenServiceAsync) GWT.create(XsrfTokenService.class);
        ((ServiceDefTarget) xsrf).setServiceEntryPoint(GWT.getModuleBaseURL() + "xsrf");

        xsrf.getNewXsrfToken(new AsyncCallback<XsrfToken>() {
          @Override
          public void onSuccess(final XsrfToken ptoken) {
            AbstractClientFactoryImpl.this.xsrfToken = ptoken;
            ((HasRpcToken) AbstractClientFactoryImpl.this.loginLogoutService).setRpcToken(ptoken);

            // look if we do already have a valid user in the session
            AbstractClientFactoryImpl.this.loginLogoutService
                .getSessionUser(new AsyncCallback<UserData>() {

                  @Override
                  public void onFailure(final Throwable pcaught) {
                    throw new RuntimeException(pcaught);
                  }

                  @Override
                  public void onSuccess(final UserData presult) {
                    if (presult != null) {
                      AbstractClientFactoryImpl.this.setUser(presult, pageToken);
                    }
                  }
                });
          }

          @Override
          public void onFailure(final Throwable pcaught) {
            throw new RuntimeException(pcaught);
          }
        });
      }
    });
  }

  @Override
  public final SimpleEventBus getEventBus() {
    return this.eventBus;
  }

  @Override
  public final PlaceController getPlaceController() {
    return this.placeController;
  }

  @Override
  public final AppPlaceHistoryMapper getPlaceHistoryMapper() {
    return this.placeHistoryMapper;
  }

  @Override
  public final UserData getUser() {
    return this.user;
  }

  @Override
  public final void setUser(final UserData presult, final String pplaceToken) {
    this.user = presult;
    // fire a user change event
    if (StringUtils.isEmpty(pplaceToken)) {
      this.getEventBus().fireEvent(
          new ChangeUserEvent(this.user, this.getNavigationPlace().getFirstToken()));
    } else {
      this.getEventBus().fireEvent(new ChangeUserEvent(this.user, pplaceToken));
    }
  }

  @Override
  public final AbstractNavigationPlace getNavigationPlace() {
    return this.navigationPlace;
  }

  @Override
  public final LoginLogoutRemoteServiceAsync getLoginLogoutService() {
    if (this.loginLogoutService == null) {
      this.loginLogoutService = GWT.create(LoginLogoutRemoteService.class);
      ((HasRpcToken) this.loginLogoutService).setRpcToken(this.xsrfToken);
    }
    return this.loginLogoutService;
  }

  @Override
  public final SepaRemoteServiceAsync getSepaService() {
    if (this.sepaService == null) {
      this.sepaService = GWT.create(SepaRemoteService.class);
      ((HasRpcToken) this.sepaService).setRpcToken(this.xsrfToken);
    }
    return this.sepaService;
  }

  @Override
  public final AddressRemoteServiceAsync getAddressService() {
    if (this.addressService == null) {
      this.addressService = GWT.create(AddressRemoteService.class);
      ((HasRpcToken) this.addressService).setRpcToken(this.xsrfToken);
    }
    return this.addressService;
  }
}

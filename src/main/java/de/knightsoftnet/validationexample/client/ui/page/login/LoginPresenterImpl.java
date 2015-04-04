package de.knightsoftnet.validationexample.client.ui.page.login;

import de.knightsoftnet.validationexample.client.ClientFactoryInterface;
import de.knightsoftnet.validationexample.client.JsHelper;
import de.knightsoftnet.validationexample.client.ui.basepage.AbstractPresenter;
import de.knightsoftnet.validationexample.shared.models.LoginData;
import de.knightsoftnet.validationexample.shared.models.UserData;
import de.knightsoftnet.validators.shared.exceptions.ValidationException;

import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.RunAsyncCallback;
import com.google.gwt.core.client.Scheduler;
import com.google.gwt.core.client.Scheduler.RepeatingCommand;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.AcceptsOneWidget;

/**
 * Activity/Presenter of the login, implementation.
 *
 * @author Manfred Tremmel
 *
 */
public class LoginPresenterImpl extends AbstractPresenter implements LoginPresenterInterface {
  /**
   * three hundred milliseconds.
   */
  public static final int FOCUS_DELAY = 300;

  /**
   * user data to remember.
   */
  private final LoginData loginData;

  /**
   * place to remember.
   */
  private final LoginPlace place;

  /**
   * view of the page.
   */
  private LoginViewInterface view;

  /**
   * the constructor of the activity/presenter.
   *
   * @param pplace place of this page
   * @param pclientFactory client factory
   */
  public LoginPresenterImpl(final LoginPlace pplace, final ClientFactoryInterface pclientFactory) {
    super(pclientFactory);
    this.place = pplace;
    this.loginData = new LoginData();
  }

  @Override
  public final void start(final AcceptsOneWidget ppanel, final EventBus peventBus) {
    GWT.runAsync(new RunAsyncCallback() {

      @Override
      public void onSuccess() {
        LoginPresenterImpl.this.view = LoginPresenterImpl.this.getClientFactory().getLoginView();
        LoginPresenterImpl.this.view.setPresenter(LoginPresenterImpl.this);
        LoginPresenterImpl.this.view.fillForm(LoginPresenterImpl.this.loginData);
        ppanel.setWidget(LoginPresenterImpl.this.view.asWidget());
        Scheduler.get().scheduleFixedDelay(new RepeatingCommand() {
          @Override
          public boolean execute() {
            LoginPresenterImpl.this.view.setFocusOnFirstWidget();
            return false;
          }
        }, FOCUS_DELAY);
      }

      @Override
      public void onFailure(final Throwable preason) {
        JsHelper.forceReload();
      }
    });
  }

  @Override
  public final void tryToLogin() {
    this.getClientFactory().getLoginLogoutService()
        .login(this.loginData, new AsyncCallback<UserData>() {
          @Override
          public void onFailure(final Throwable pcaught) {
            try {
              throw pcaught;
            } catch (final ValidationException e) {
              LoginPresenterImpl.this
                  .getClientFactory()
                  .getLoginView()
                  .getDriver()
                  .setConstraintViolations(
                      e.getValidationErrorSet(LoginPresenterImpl.this.loginData));
            } catch (final Throwable e) {
              final LoginConstants constants = GWT.create(LoginConstants.class);
              LoginPresenterImpl.this.getClientFactory().getLoginView()
                  .showMessage(constants.messageLoginError());
            }
          }

          @Override
          public void onSuccess(final UserData presult) {
            if (presult == null) {
              final LoginConstants constants = GWT.create(LoginConstants.class);
              LoginPresenterImpl.this.getClientFactory().getLoginView()
                  .showMessage(constants.messageLoginError());
            } else {
              // loginData is ok, set it to client factory and also give the place to go to
              if (LoginPresenterImpl.this.place.getRedirectToken() == null) {
                LoginPresenterImpl.this.getClientFactory()
                    .setUser(
                        presult,
                        LoginPresenterImpl.this.getClientFactory().getNavigationPlace()
                            .getFirstToken());
              } else {
                LoginPresenterImpl.this.getClientFactory().setUser(presult,
                    LoginPresenterImpl.this.place.getRedirectToken());
              }
            }
          }
        });
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

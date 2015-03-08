package de.knightsoftnet.validationexample.client.ui.settings;

import de.knightsoftnet.validationexample.client.basepage.AbstractPresenter;
import de.knightsoftnet.validationexample.client.ClientFactoryInterface;
import de.knightsoftnet.validationexample.client.JsHelper;

import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.RunAsyncCallback;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.i18n.client.LocaleInfo;
import com.google.gwt.user.client.Cookies;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.Window;

/**
 * Activity/Presenter of the validator test settings.
 *
 * @author Manfred Tremmel
 *
 */
public class SettingsPresenterImpl extends AbstractPresenter implements SettingsPresenterInterface {
  /**
   * the constructor of the activity/presenter.
   *
   * @param pclientFactory client factory
   */
  public SettingsPresenterImpl(final ClientFactoryInterface pclientFactory) {
    super(pclientFactory);
  }

  @Override
  public final void start(final AcceptsOneWidget ppanel, final EventBus peventBus) {
    GWT.runAsync(new RunAsyncCallback() {

      @Override
      public void onSuccess() {
        final SettingsViewInterface view =
            SettingsPresenterImpl.this.getClientFactory().getSettingsView();
        view.setPresenter(SettingsPresenterImpl.this);

        ppanel.setWidget(view.asWidget());
      }

      @Override
      public void onFailure(final Throwable preason) {
        JsHelper.forceReload();
      }
    });
  }

  @Override
  public final void changeLanguage(final String planguage) {
    Cookies.setCookie(LocaleInfo.getLocaleCookieName(), planguage);
    Window.Location.reload();
  }

  @Override
  public final void fillLanguages(final ListBox plistBox) {
    plistBox.clear();
    final String currentLang = LocaleInfo.getCurrentLocale().getLocaleName();
    int currentLangPos = 0;
    int langPos = 0;
    for (final String lang : LocaleInfo.getAvailableLocaleNames()) {
      if (!"default".equals(lang)) {
        plistBox.addItem(LocaleInfo.getLocaleNativeDisplayName(lang), lang);
        if (lang.equals(currentLang)) {
          currentLangPos = langPos;
        }
        langPos++;
      }
    }
    plistBox.setItemSelected(currentLangPos, true);
  }

  @Override
  public final String mayStop() {
    return null;
  }

  @Override
  public final void onCancel() {
    // Nothing to do!
  }

  @Override
  public final void onStop() {
    // Nothing to do!
  }
}

package de.knightsoftnet.validationexample.client.ui.page.settings;

import com.google.gwt.user.client.ui.IsWidget;

/**
 * View of the validator Test application interface.
 *
 * @author Manfred Tremmel
 *
 */
public interface SettingsViewInterface extends IsWidget {
  /**
   * set a reference to the presenter/activity.
   *
   * @param psettingsPresenter reference to set
   */
  void setPresenter(SettingsPresenterInterface psettingsPresenter);
}

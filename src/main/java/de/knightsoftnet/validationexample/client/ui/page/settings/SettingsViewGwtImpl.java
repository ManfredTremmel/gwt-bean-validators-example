package de.knightsoftnet.validationexample.client.ui.page.settings;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.Widget;

/**
 * View of the validator test Settings.
 *
 * @author Manfred Tremmel
 *
 */
public class SettingsViewGwtImpl extends Composite implements SettingsViewInterface {

  /**
   * bind ui.
   */
  private static SettingsViewUiBinder uiBinder = GWT.create(SettingsViewUiBinder.class);

  /**
   * view interface.
   */
  interface SettingsViewUiBinder extends UiBinder<Widget, SettingsViewGwtImpl> {
  }

  /**
   * language list box.
   */
  @UiField
  ListBox language;

  /**
   * reference to the activity.
   */
  private SettingsPresenterInterface activity;

  /**
   * default constructor.
   */
  public SettingsViewGwtImpl() {
    super();
    this.initWidget(uiBinder.createAndBindUi(this));
  }

  @Override
  public final void setPresenter(final SettingsPresenterInterface psettingsPresenter) {
    this.activity = psettingsPresenter;
    this.activity.fillLanguages(this.language);
  }

  /**
   * language changes.
   *
   * @param pchangeEvent change event.
   */
  @UiHandler("language")
  final void onChange(final ChangeEvent pchangeEvent) {
    this.activity.changeLanguage(((ListBox) pchangeEvent.getSource())
        .getValue(((ListBox) pchangeEvent.getSource()).getSelectedIndex()));
  }
}

package de.knightsoftnet.validationexample.client.ui.page.sepa;

import de.knightsoftnet.validationexample.shared.models.CountryEnum;
import de.knightsoftnet.validationexample.shared.models.SepaData;
import de.knightsoftnet.validators.client.decorators.UniversalDecoratorWithIcons;
import de.knightsoftnet.validators.client.editor.BeanValidationEditorDriver;
import de.knightsoftnet.validators.client.event.FormSubmitEvent;
import de.knightsoftnet.validators.client.event.FormSubmitHandler;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;

/**
 * View of the validator test Sepa.
 *
 * @author Manfred Tremmel
 *
 */
public class SepaViewGwtImpl extends Composite implements SepaViewInterface {

  /**
   * bind ui.
   */
  private static SepaViewUiBinder uiBinder = GWT.create(SepaViewUiBinder.class);

  /**
   * view interface.
   */
  interface SepaViewUiBinder extends UiBinder<Widget, SepaViewGwtImpl> {
  }

  /**
   * bank name.
   */
  @UiField
  UniversalDecoratorWithIcons<String> bankName;

  /**
   * password.
   */
  @UiField
  UniversalDecoratorWithIcons<String> accountOwner;

  /**
   * country code.
   */
  @UiField
  UniversalDecoratorWithIcons<CountryEnum> countryCode;

  /**
   * iban.
   */
  @UiField
  UniversalDecoratorWithIcons<String> iban;

  /**
   * bic.
   */
  @UiField
  UniversalDecoratorWithIcons<String> bic;

  /**
   * label to display messages.
   */
  @Ignore
  @UiField
  Label logMessages;

  /**
   * Sepa button.
   */
  @Ignore
  @UiField
  Button sepaButton;

  /**
   * interface of the driver to combine ui and bean.
   */
  interface Driver extends BeanValidationEditorDriver<SepaData, SepaViewGwtImpl> {
  }

  /**
   * create driver out of the interface.
   */
  private final Driver driver = GWT.create(Driver.class);

  /**
   * reference to the activity.
   */
  private SepaPresenterInterface activity;

  /**
   * default constructor.
   */
  public SepaViewGwtImpl() {
    super();
    this.initWidget(uiBinder.createAndBindUi(this));
    this.driver.initialize(this);
    this.driver.setSubmitButton(this.sepaButton);
    this.driver.addFormSubmitHandler(new FormSubmitHandler<SepaData>() {
      @Override
      public void onFormSubmit(final FormSubmitEvent<SepaData> pevent) {
        SepaViewGwtImpl.this.activity.tryToSend();
      }
    });
  }

  @Override
  public final void setPresenter(final SepaPresenterInterface psepaPresenter) {
    this.activity = psepaPresenter;
  }

  @Override
  public final void fillForm(final SepaData puser) {
    this.driver.edit(puser);
  }

  @Override
  public final void showMessage(final String pmessage) {
    this.logMessages.setText(pmessage);
  }

  @Override
  public final BeanValidationEditorDriver<SepaData, ? extends SepaViewInterface> getDriver() {
    return this.driver;
  }

  @Override
  public final void setFocusOnFirstWidget() {
    this.bankName.setFocus(true);
  }
}

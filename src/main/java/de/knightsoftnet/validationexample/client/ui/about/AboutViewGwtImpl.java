package de.knightsoftnet.validationexample.client.ui.about;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

/**
 * View of the about page, gwt implementation.
 *
 * @author Manfred Tremmel
 *
 */
public class AboutViewGwtImpl extends Composite implements AboutViewInterface {

  /**
   * bind ui.
   */
  private static SecretViewUiBinder uiBinder = GWT.create(SecretViewUiBinder.class);

  /**
   * view interface.
   */
  interface SecretViewUiBinder extends UiBinder<Widget, AboutViewGwtImpl> {
  }

  /**
   * the button to close the about view.
   */
  @UiField
  Button closeButton;

  /**
   * presenter to remember.
   */
  private AboutPresenterInterface presenter;

  /**
   * default constructor.
   */
  public AboutViewGwtImpl() {
    super();
    this.initWidget(uiBinder.createAndBindUi(this));
  }

  @Override
  public final void setPresenter(final AboutPresenterInterface paboutPresenterInterface) {
    this.presenter = paboutPresenterInterface;
  }

  /**
   * click on the login button.
   *
   * @param pclickEvent click event.
   */
  @UiHandler("closeButton")
  final void onClick(final ClickEvent pclickEvent) {
    this.presenter.goBackToPreviousPage();
  }
}

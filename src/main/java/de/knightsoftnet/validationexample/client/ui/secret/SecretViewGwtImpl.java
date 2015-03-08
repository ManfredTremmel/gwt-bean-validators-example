package de.knightsoftnet.validationexample.client.ui.secret;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

/**
 * View of the secret page, gwt implementation.
 *
 * @author Manfred Tremmel
 *
 */
public class SecretViewGwtImpl extends Composite implements SecretViewInterface {

  /**
   * bind ui.
   */
  private static SecretViewUiBinder uiBinder = GWT.create(SecretViewUiBinder.class);

  /**
   * view interface.
   */
  interface SecretViewUiBinder extends UiBinder<Widget, SecretViewGwtImpl> {
  }

  /**
   * reference to the presenter.
   */
  // private SecretPresenterInterface presenter;

  /**
   * default constructor.
   */
  public SecretViewGwtImpl() {
    super();
    this.initWidget(uiBinder.createAndBindUi(this));
  }

  @Override
  public final void setPresenter(final SecretPresenterInterface psecretPresenterInterface) {
    // we don't need it here
    // this.presenter = pSecretPresenterInterface;
  }
}

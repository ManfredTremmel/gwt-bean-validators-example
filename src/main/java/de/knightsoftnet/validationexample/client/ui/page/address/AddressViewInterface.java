package de.knightsoftnet.validationexample.client.ui.page.address;

import de.knightsoftnet.validationexample.shared.models.PostalAddressData;
import de.knightsoftnet.validators.client.editor.BeanValidationEditorDriver;

import com.google.gwt.editor.client.Editor;
import com.google.gwt.user.client.ui.IsWidget;

/**
 * View of the postal address view application interface.
 *
 * @author Manfred Tremmel
 *
 */
public interface AddressViewInterface extends Editor<PostalAddressData> {
  /**
   * set a reference to the presenter/activity.
   *
   * @param paddressPresenterInterface reference to set
   */
  void setPresenter(AddressPresenterInterface paddressPresenterInterface);

  /**
   * fill the form with data.
   *
   * @param psepData data to fill into the form
   */
  void fillForm(PostalAddressData psepData);

  /**
   * getter for the driver.
   *
   * @return the driver
   */
  BeanValidationEditorDriver<PostalAddressData, ? extends AddressViewInterface> getDriver();

  /**
   * display a message on the screen.
   *
   * @param pmessage the message to display
   */
  void showMessage(String pmessage);

  /**
   * set focus on first widget.
   */
  void setFocusOnFirstWidget();

  /**
   * give back this view as widget.
   *
   * @return IsWidget
   */
  IsWidget asWidget();
}

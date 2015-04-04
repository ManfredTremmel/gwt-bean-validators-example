package de.knightsoftnet.validationexample.client.ui.page.sepa;

import de.knightsoftnet.validationexample.shared.models.SepaData;
import de.knightsoftnet.validators.client.editor.BeanValidationEditorDriver;

import com.google.gwt.editor.client.Editor;
import com.google.gwt.user.client.ui.IsWidget;

/**
 * View of the sepa view application interface.
 *
 * @author Manfred Tremmel
 *
 */
public interface SepaViewInterface extends Editor<SepaData> {
  /**
   * set a reference to the presenter/activity.
   *
   * @param psepaPresenterInterface reference to set
   */
  void setPresenter(SepaPresenterInterface psepaPresenterInterface);

  /**
   * fill the form with data.
   *
   * @param psepData data to fill into the form
   */
  void fillForm(SepaData psepData);

  /**
   * getter for the driver.
   *
   * @return the driver
   */
  BeanValidationEditorDriver<SepaData, ? extends SepaViewInterface> getDriver();

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

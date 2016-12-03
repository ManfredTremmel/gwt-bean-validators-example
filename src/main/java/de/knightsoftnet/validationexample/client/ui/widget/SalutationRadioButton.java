package de.knightsoftnet.validationexample.client.ui.widget;

import de.knightsoftnet.mtwidgets.client.ui.widget.SortableIdAndNameRadioButton;
import de.knightsoftnet.validationexample.shared.models.SalutationEnum;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiConstructor;

public class SalutationRadioButton extends SortableIdAndNameRadioButton<SalutationEnum> {

  /**
   * widget ui constructor.
   */
  @UiConstructor
  public SalutationRadioButton() {
    super("salutation", null, GWT.create(SalutationMessages.class), SalutationEnum.values());
  }
}

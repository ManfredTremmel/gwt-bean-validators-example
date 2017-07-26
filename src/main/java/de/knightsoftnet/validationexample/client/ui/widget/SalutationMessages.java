package de.knightsoftnet.validationexample.client.ui.widget;

import de.knightsoftnet.mtwidgets.client.ui.widget.helper.MessagesForValues;
import de.knightsoftnet.validationexample.shared.models.SalutationEnum;

public interface SalutationMessages extends MessagesForValues<SalutationEnum> {
  /**
   * get salutation name for salutation code.
   *
   * @param psalutation salutation enum.
   * @return salutation name
   */
  @Override
  @DefaultMessage("Please select")
  String name(@Select SalutationEnum psalutation);
}

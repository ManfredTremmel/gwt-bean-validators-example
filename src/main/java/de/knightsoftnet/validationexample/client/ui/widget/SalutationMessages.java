package de.knightsoftnet.validationexample.client.ui.widget;

import de.knightsoftnet.mtwidgets.client.ui.widget.helper.MessagesForValues;
import de.knightsoftnet.validationexample.shared.models.SalutationEnum;

public interface SalutationMessages extends MessagesForValues<SalutationEnum> {
  /**
   * get country name for country code.
   *
   * @param pcountry country enum.
   * @return country name
   */
  @Override
  @DefaultMessage("Please select")
  String name(@Select SalutationEnum pcountry);
}

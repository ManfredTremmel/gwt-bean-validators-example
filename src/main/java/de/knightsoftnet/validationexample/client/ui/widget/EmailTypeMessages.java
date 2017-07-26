package de.knightsoftnet.validationexample.client.ui.widget;

import de.knightsoftnet.mtwidgets.client.ui.widget.helper.MessagesForValues;
import de.knightsoftnet.validationexample.shared.models.EmailTypeEnum;

public interface EmailTypeMessages extends MessagesForValues<EmailTypeEnum> {
  /**
   * get email type name for email type code.
   *
   * @param pemailTypeEnum email type enum.
   * @return email type name
   */
  @Override
  @DefaultMessage("Please select")
  String name(@Select EmailTypeEnum pemailTypeEnum);
}

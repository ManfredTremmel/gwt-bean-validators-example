package de.knightsoftnet.validationexample.client.ui.widget;

import de.knightsoftnet.validationexample.shared.models.CountryEnum;

import com.google.gwt.i18n.client.Messages;

/**
 * translate country enum to country name.
 *
 * @author Manfred Tremmel
 */
public interface CountryMessages extends Messages {
  /**
   * get country name for country code.
   * 
   * @param country country enum.
   * @return country name
   */
  String country(@Select CountryEnum country);
}

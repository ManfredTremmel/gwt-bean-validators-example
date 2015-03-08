package de.knightsoftnet.validationexample.client.ui.address;

import com.google.gwt.i18n.client.Constants;

/**
 * Localized values for the form.
 *
 * @author Manfred Tremmel
 *
 */
public interface AddressConstants extends Constants {
  /**
   * test for the header of the login view.
   *
   * @return header
   */
  String header();

  /**
   * text for the post office box label.
   *
   * @return labelPostOfficeBox
   */
  String labelPostOfficeBox();

  /**
   * text for the street label.
   *
   * @return labelStreet
   */
  String labelStreet();

  /**
   * text for the street number label.
   *
   * @return labelStreetNumber
   */
  String labelStreetNumber();

  /**
   * text for the street number additional label.
   *
   * @return labelStreetNumberAdditional
   */
  String labelStreetNumberAdditional();

  /**
   * text for the extended label.
   *
   * @return labelExtended
   */
  String labelExtended();

  /**
   * text for the postal code label.
   *
   * @return labelPostalCode
   */
  String labelPostalCode();

  /**
   * text for the locality label.
   *
   * @return labelLocality
   */
  String labelLocality();

  /**
   * text for the region code label.
   *
   * @return labelRegion
   */
  String labelRegion();

  /**
   * text for country code label.
   *
   * @return labelCountryCode
   */
  String labelCountryCode();

  /**
   * text for the address button.
   *
   * @return addressButton
   */
  String addressButton();

  /**
   * text to display when address data are not correct.
   *
   * @return messageSepaError
   */
  String messageAddressDataError();

  /**
   * text to display when address data data are correct.
   *
   * @return messageSepaOk
   */
  String messageAddressDataOk();
}

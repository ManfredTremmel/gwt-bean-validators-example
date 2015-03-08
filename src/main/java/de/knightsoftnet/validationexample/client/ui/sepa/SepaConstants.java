package de.knightsoftnet.validationexample.client.ui.sepa;

import com.google.gwt.i18n.client.Constants;

/**
 * Localized values for the form.
 *
 * @author Manfred Tremmel
 *
 */
public interface SepaConstants extends Constants {
  /**
   * test for the header of the login view.
   *
   * @return header
   */
  String header();

  /**
   * text for the bank name label.
   *
   * @return labelBankName
   */
  String labelBankName();

  /**
   * text for the account owner label.
   *
   * @return labelAccountOwner
   */
  String labelAccountOwner();

  /**
   * text for country code label.
   *
   * @return labelCountryCode
   */
  String labelCountryCode();

  /**
   * text for the new iban label.
   *
   * @return labelIban
   */
  String labelIban();

  /**
   * text for the new bic label.
   *
   * @return labelBic
   */
  String labelBic();

  /**
   * text for the sepa button.
   *
   * @return buttonSepa
   */
  String buttonSepa();

  /**
   * text to display when sepa data is not correct.
   *
   * @return messageSepaError
   */
  String messageSepaError();

  /**
   * text to display when sepa data is correct.
   *
   * @return messageSepaOk
   */
  String messageSepaOk();
}

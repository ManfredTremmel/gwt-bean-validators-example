package de.knightsoftnet.validationexample.client.validation;

import de.knightsoftnet.validationexample.shared.models.LoginData;
import de.knightsoftnet.validationexample.shared.models.PostalAddressData;
import de.knightsoftnet.validationexample.shared.models.SepaData;

import com.google.gwt.core.client.GWT;
import com.google.gwt.validation.client.AbstractGwtValidatorFactory;
import com.google.gwt.validation.client.GwtValidation;
import com.google.gwt.validation.client.impl.AbstractGwtValidator;

import javax.validation.Validator;

/**
 * The <code>ValidatorFactory</code> class is used for client side validation by annotation.
 *
 * @author Manfred Tremmel
 */
public class ValidatorFactory extends AbstractGwtValidatorFactory {

  /**
   * Validator marker for the Validation Sample project. Only the classes and groups listed in the
   * {@link GwtValidation} annotation can be validated.
   */
  @GwtValidation(value = {LoginData.class, SepaData.class, PostalAddressData.class})
  public interface GwtValidator extends Validator {
  }

  @Override
  public final AbstractGwtValidator createValidator() {
    return GWT.create(GwtValidator.class);
  }

}

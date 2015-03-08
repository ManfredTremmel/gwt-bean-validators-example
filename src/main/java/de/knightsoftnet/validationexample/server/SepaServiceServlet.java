package de.knightsoftnet.validationexample.server;

import de.knightsoftnet.validationexample.client.ui.sepa.SepaRemoteService;
import de.knightsoftnet.validationexample.shared.models.SepaData;
import de.knightsoftnet.validators.shared.exceptions.ValidationException;

import com.google.gwt.user.server.rpc.XsrfProtectedServiceServlet;

import java.util.Set;

import javax.servlet.annotation.WebServlet;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;

/**
 * Definition of the sepa remote services.
 *
 * @author Manfred Tremmel
 */
@WebServlet(urlPatterns = {"/gwtBeanValidatorsExample/sepa"})
public class SepaServiceServlet extends XsrfProtectedServiceServlet implements SepaRemoteService {

  /**
   * serial version unique id.
   */
  private static final long serialVersionUID = 7665082078524106315L;

  @Override
  public SepaData sendSepa(final SepaData psepaData) throws ValidationException {
    // validate input data, don't trust the client validation
    final Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

    final Set<ConstraintViolation<SepaData>> cv1 = validator.validate(psepaData);

    // no error, fine ;-)
    if (cv1.isEmpty()) {
      // do the job
    } else {
      throw new ValidationException(cv1);
    }
    return psepaData;
  }

}

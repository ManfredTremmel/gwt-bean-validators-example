package de.knightsoftnet.validationexample.client.ui.sepa;

import de.knightsoftnet.validationexample.shared.models.SepaData;
import de.knightsoftnet.validators.shared.exceptions.ValidationException;

import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.google.gwt.user.client.rpc.XsrfProtectedService;
import com.google.gwt.user.server.rpc.NoXsrfProtect;

/**
 * Definition of the sepa remote services.
 *
 * @author Manfred Tremmel
 */
@RemoteServiceRelativePath("sepa")
public interface SepaRemoteService extends XsrfProtectedService {
  /**
   * send sepa data.
   *
   * @param sepaData sepa data
   * @return SepaData
   * @throws ValidationException if login data are not valid
   */
  @NoXsrfProtect
  SepaData sendSepa(SepaData sepaData) throws ValidationException;
}

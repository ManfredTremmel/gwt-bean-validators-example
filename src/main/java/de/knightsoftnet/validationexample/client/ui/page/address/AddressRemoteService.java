package de.knightsoftnet.validationexample.client.ui.page.address;

import de.knightsoftnet.validationexample.shared.models.PostalAddressData;
import de.knightsoftnet.validators.shared.exceptions.ValidationException;

import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.google.gwt.user.client.rpc.XsrfProtectedService;
import com.google.gwt.user.server.rpc.NoXsrfProtect;

/**
 * Definition of the address remote services.
 *
 * @author Manfred Tremmel
 */
@RemoteServiceRelativePath("address")
public interface AddressRemoteService extends XsrfProtectedService {
  /**
   * send postal address data.
   *
   * @param postalAddressData postal address data
   * @return PostalAddressData
   * @throws ValidationException if login data are not valid
   */
  @NoXsrfProtect
  PostalAddressData sendPostalAddress(PostalAddressData postalAddressData)
      throws ValidationException;
}

package de.knightsoftnet.validationexample.client.ui.page.address;

import de.knightsoftnet.validationexample.shared.models.PostalAddressData;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface AddressRemoteServiceAsync {

  void sendPostalAddress(PostalAddressData postalAddressData,
      AsyncCallback<PostalAddressData> callback);
}

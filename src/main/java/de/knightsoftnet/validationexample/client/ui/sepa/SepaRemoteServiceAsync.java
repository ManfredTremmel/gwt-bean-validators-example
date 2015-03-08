package de.knightsoftnet.validationexample.client.ui.sepa;

import de.knightsoftnet.validationexample.shared.models.SepaData;

import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * Definition of the sepa remote async services.
 *
 * @author Manfred Tremmel
 */
public interface SepaRemoteServiceAsync {

  void sendSepa(SepaData sepaData, AsyncCallback<SepaData> callback);

}

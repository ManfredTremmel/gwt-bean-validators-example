/*
 * Licensed to the Apache Software Foundation (ASF) under one or more contributor license
 * agreements. See the NOTICE file distributed with this work for additional information regarding
 * copyright ownership. The ASF licenses this file to You under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance with the License. You may obtain a
 * copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */

package de.knightsoftnet.validationexample.client.ui.page.address;

import de.knightsoftnet.mtwidgets.shared.models.CountryEnum;
import de.knightsoftnet.validationexample.client.services.PostalAddressRestService;
import de.knightsoftnet.validationexample.client.ui.basepage.BasePagePresenter;
import de.knightsoftnet.validationexample.client.ui.navigation.NameTokens;
import de.knightsoftnet.validationexample.shared.models.PostalAddressData;
import de.knightsoftnet.validationexample.shared.models.ValidationDto;
import de.knightsoftnet.validationexample.shared.models.ValidationResultData;

import com.google.gwt.core.client.Scheduler;
import com.google.gwt.core.client.Scheduler.ScheduledCommand;
import com.google.gwt.editor.client.Editor;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.dispatch.rest.client.RestDispatch;
import com.gwtplatform.mvp.client.Presenter;
import com.gwtplatform.mvp.client.View;
import com.gwtplatform.mvp.client.annotations.NameToken;
import com.gwtplatform.mvp.client.annotations.NoGatekeeper;
import com.gwtplatform.mvp.client.annotations.ProxyCodeSplit;
import com.gwtplatform.mvp.client.proxy.ProxyPlace;

import org.hibernate.validator.engine.ConstraintViolationImpl;
import org.hibernate.validator.engine.PathImpl;

import java.util.ArrayList;

import javax.inject.Inject;
import javax.validation.ConstraintViolation;

/**
 * Presenter of the address, implementation.
 *
 * @author Manfred Tremmel
 *
 */
public class AddressPresenter extends Presenter<AddressPresenter.MyView, AddressPresenter.MyProxy> {

  public interface MyView extends View, Editor<PostalAddressData> {
    /**
     * set a reference to the presenter/activity.
     *
     * @param ppresenter reference to set
     */
    void setPresenter(AddressPresenter ppresenter);

    /**
     * fill the form with data.
     *
     * @param psepData data to fill into the form
     */
    void fillForm(PostalAddressData psepData);

    /**
     * display a message on the screen.
     *
     * @param pmessage the message to display
     */
    void showMessage(String pmessage);

    /**
     * set focus on first widget.
     */
    void setFocusOnFirstWidget();

    /**
     * display validation errors.
     *
     * @param pvalidationErrorSet list of violations
     */
    void setConstraintViolations(ArrayList<ConstraintViolation<?>> pvalidationErrorSet);
  }

  @ProxyCodeSplit
  @NameToken(NameTokens.ADDRESS)
  @NoGatekeeper
  public interface MyProxy extends ProxyPlace<AddressPresenter> {
  }

  private final AddressConstants constants;
  private final PostalAddressData addressData;

  private final RestDispatch dispatcher;
  private final PostalAddressRestService postalAddressService;

  /**
   * constructor injecting parameters.
   */
  @Inject
  public AddressPresenter(final EventBus peventBus, final AddressPresenter.MyView pview,
      final MyProxy pproxy, final AddressConstants pconstants, final RestDispatch pdispatcher,
      final PostalAddressRestService ppostalAddressService) {
    super(peventBus, pview, pproxy, BasePagePresenter.SLOT_MAIN_CONTENT);
    this.dispatcher = pdispatcher;
    this.postalAddressService = ppostalAddressService;
    this.constants = pconstants;
    this.addressData = new PostalAddressData();
    this.addressData.setCountryCode(CountryEnum.valueOf(pconstants.defaultCountry()));
    this.getView().setPresenter(this);
    this.getView().fillForm(this.addressData);
  }

  @Override
  protected void onReveal() {
    super.onReveal();
    Scheduler.get().scheduleDeferred(new ScheduledCommand() {
      @Override
      public void execute() {
        AddressPresenter.this.getView().setFocusOnFirstWidget();
      }
    });
  }

  /**
   * try to send data.
   */
  public final void tryToSend() {
    this.dispatcher.execute(this.postalAddressService.checkPostalAddress(this.addressData),
        new AsyncCallback<ValidationResultData>() {

          @Override
          public void onFailure(final Throwable pcaught) {
            AddressPresenter.this.getView()
                .showMessage(AddressPresenter.this.constants.messageAddressDataError());
          }

          @Override
          public void onSuccess(final ValidationResultData presult) {
            if (presult == null || presult.getValidationErrorSet() == null
                || presult.getValidationErrorSet().isEmpty()) {
              AddressPresenter.this.getView()
                  .showMessage(AddressPresenter.this.constants.messageAddressDataOk());
            } else {
              final ArrayList<ConstraintViolation<?>> violations =
                  new ArrayList<ConstraintViolation<?>>(presult.getValidationErrorSet().size());
              for (final ValidationDto violation : presult.getValidationErrorSet()) {
                violations.add(new ConstraintViolationImpl<PostalAddressData>(null,
                    violation.getMessage(), null, AddressPresenter.this.addressData, null, null,
                    PathImpl.createPathFromString(violation.getPropertyPath()), null, null));
              }
              AddressPresenter.this.getView().setConstraintViolations(violations);
            }
          }
        });
  }
}

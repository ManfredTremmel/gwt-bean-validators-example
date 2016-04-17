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

package de.knightsoftnet.validationexample.client.ui.page.phonenumber;

import de.knightsoftnet.mtwidgets.shared.models.CountryEnum;
import de.knightsoftnet.validationexample.client.services.PhoneNumberRestService;
import de.knightsoftnet.validationexample.client.ui.basepage.BasePagePresenter;
import de.knightsoftnet.validationexample.client.ui.navigation.NameTokens;
import de.knightsoftnet.validationexample.shared.models.PhoneNumberData;
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
 * Presenter of the Sepa, implementation.
 *
 * @author Manfred Tremmel
 *
 */
public class PhoneNumberPresenter
    extends Presenter<PhoneNumberPresenter.MyView, PhoneNumberPresenter.MyProxy> {

  public interface MyView extends View, Editor<PhoneNumberData> {
    /**
     * set a reference to the presenter/activity.
     *
     * @param ppresenter reference to set
     */
    void setPresenter(PhoneNumberPresenter ppresenter);

    /**
     * fill the form with data.
     *
     * @param pphoneNumberData data to fill into the form
     */
    void fillForm(PhoneNumberData pphoneNumberData);

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
  @NameToken(NameTokens.PHONE_NUMBER)
  @NoGatekeeper
  public interface MyProxy extends ProxyPlace<PhoneNumberPresenter> {
  }

  private final PhoneNumberData phoneNumberData;

  private final PhoneNumberConstants constants;
  private final RestDispatch dispatcher;
  private final PhoneNumberRestService phoneNumberService;

  /**
   * constructor injecting parameters.
   */
  @Inject
  public PhoneNumberPresenter(final EventBus peventBus, final PhoneNumberPresenter.MyView pview,
      final MyProxy pproxy, final PhoneNumberConstants pconstants, final RestDispatch pdispatcher,
      final PhoneNumberRestService pphoneNumberService) {
    super(peventBus, pview, pproxy, BasePagePresenter.SLOT_MAIN_CONTENT);
    this.constants = pconstants;
    this.dispatcher = pdispatcher;
    this.phoneNumberService = pphoneNumberService;
    this.phoneNumberData = new PhoneNumberData();
    this.phoneNumberData.setCountryCode(CountryEnum.valueOf(pconstants.defaultCountry()));
    this.getView().setPresenter(this);
    this.getView().fillForm(this.phoneNumberData);
  }

  @Override
  protected void onReveal() {
    super.onReveal();
    Scheduler.get().scheduleDeferred(new ScheduledCommand() {
      @Override
      public void execute() {
        PhoneNumberPresenter.this.getView().setFocusOnFirstWidget();
      }
    });
  }

  /**
   * try to send data.
   */
  public final void tryToSend() {
    this.dispatcher.execute(this.phoneNumberService.checkPhoneNumber(this.phoneNumberData),
        new AsyncCallback<ValidationResultData>() {

          @Override
          public void onFailure(final Throwable pcaught) {
            PhoneNumberPresenter.this.getView()
                .showMessage(PhoneNumberPresenter.this.constants.messagePhoneNumberError());
          }

          @Override
          public void onSuccess(final ValidationResultData presult) {
            if (presult == null || presult.getValidationErrorSet() == null
                || presult.getValidationErrorSet().isEmpty()) {
              PhoneNumberPresenter.this.getView()
                  .showMessage(PhoneNumberPresenter.this.constants.messagePhoneNumberOk());
            } else {
              final ArrayList<ConstraintViolation<?>> violations =
                  new ArrayList<ConstraintViolation<?>>(presult.getValidationErrorSet().size());
              for (final ValidationDto violation : presult.getValidationErrorSet()) {
                violations.add(new ConstraintViolationImpl<PhoneNumberData>(null,
                    violation.getMessage(), null, PhoneNumberPresenter.this.phoneNumberData, null,
                    null, PathImpl.createPathFromString(violation.getPropertyPath()), null, null));
              }
              PhoneNumberPresenter.this.getView().setConstraintViolations(violations);
            }
          }
        });
  }
}

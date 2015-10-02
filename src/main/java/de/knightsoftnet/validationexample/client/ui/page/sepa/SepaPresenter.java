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

package de.knightsoftnet.validationexample.client.ui.page.sepa;

import de.knightsoftnet.validationexample.client.ui.basepage.BasePagePresenter;
import de.knightsoftnet.validationexample.client.ui.navigation.NameTokens;
import de.knightsoftnet.validationexample.shared.models.CountryEnum;
import de.knightsoftnet.validationexample.shared.models.SepaData;
import de.knightsoftnet.validators.shared.exceptions.ValidationException;

import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.Scheduler;
import com.google.gwt.core.client.Scheduler.ScheduledCommand;
import com.google.gwt.editor.client.Editor;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.mvp.client.Presenter;
import com.gwtplatform.mvp.client.View;
import com.gwtplatform.mvp.client.annotations.NameToken;
import com.gwtplatform.mvp.client.annotations.NoGatekeeper;
import com.gwtplatform.mvp.client.annotations.ProxyCodeSplit;
import com.gwtplatform.mvp.client.proxy.ProxyPlace;

import java.util.ArrayList;

import javax.inject.Inject;
import javax.validation.ConstraintViolation;

/**
 * Activity/Presenter of the Sepa, implementation.
 *
 * @author Manfred Tremmel
 *
 */
public class SepaPresenter extends Presenter<SepaPresenter.MyView, SepaPresenter.MyProxy> {

  public interface MyView extends View, Editor<SepaData> {
    /**
     * set a reference to the presenter/activity.
     *
     * @param ppresenter reference to set
     */
    void setPresenter(SepaPresenter ppresenter);

    /**
     * fill the form with data.
     *
     * @param psepData data to fill into the form
     */
    void fillForm(SepaData psepData);

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
  @NameToken(NameTokens.SEPA)
  @NoGatekeeper
  public interface MyProxy extends ProxyPlace<SepaPresenter> {
  }

  /**
   * sepa data to remember.
   */
  private final SepaData sepaData;

  /**
   * sepa remote service.
   */
  private final SepaRemoteServiceAsync service;

  /**
   * constructor injecting parameters.
   *
   * @param peventBus event bus
   * @param pview view of the page
   * @param pproxy proxy to handle page
   * @param pservice remote service to contact the server
   * @param pconstants localization constants
   */
  @Inject
  public SepaPresenter(final EventBus peventBus, final SepaPresenter.MyView pview,
      final MyProxy pproxy, final SepaRemoteServiceAsync pservice, //
      final SepaConstants pconstants) {
    super(peventBus, pview, pproxy, BasePagePresenter.SLOT_MAIN_CONTENT);
    this.service = pservice;
    this.sepaData = new SepaData();
    this.sepaData.setCountryCode(CountryEnum.valueOf(pconstants.defaultCountry()));
    this.getView().setPresenter(this);
    this.getView().fillForm(this.sepaData);
  }

  @Override
  protected void onReveal() {
    super.onReveal();
    Scheduler.get().scheduleDeferred(new ScheduledCommand() {
      @Override
      public void execute() {
        SepaPresenter.this.getView().setFocusOnFirstWidget();
      }
    });
  }

  /**
   * try to send data.
   */
  public final void tryToSend() {
    this.service.sendSepa(this.sepaData, new AsyncCallback<SepaData>() {
      @Override
      public void onFailure(final Throwable pcaught) {
        try {
          throw pcaught;
        } catch (final ValidationException e) {
          SepaPresenter.this.getView().setConstraintViolations(
              e.getValidationErrorSet(SepaPresenter.this.sepaData));
        } catch (final Throwable e) {
          final SepaConstants constants = GWT.create(SepaConstants.class);
          SepaPresenter.this.getView().showMessage(constants.messageSepaError());
        }
      }

      @Override
      public void onSuccess(final SepaData presult) {
        final SepaConstants constants = GWT.create(SepaConstants.class);
        if (presult == null) {
          SepaPresenter.this.getView().showMessage(constants.messageSepaError());
        } else {
          SepaPresenter.this.getView().showMessage(constants.messageSepaOk());
        }
      }
    });
  }
}
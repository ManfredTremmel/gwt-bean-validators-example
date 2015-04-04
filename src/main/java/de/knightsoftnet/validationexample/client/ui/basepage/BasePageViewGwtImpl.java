package de.knightsoftnet.validationexample.client.ui.basepage;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HasOneWidget;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.Widget;

/**
 * View of the BasePage.
 *
 * @author Manfred Tremmel
 *
 */
public class BasePageViewGwtImpl extends Composite implements BasePageViewInterface {

  /**
   * bind ui.
   */
  private static BasePageViewUiBinder uiBinder = GWT.create(BasePageViewUiBinder.class);

  /**
   * view interface.
   */
  interface BasePageViewUiBinder extends UiBinder<Widget, BasePageViewGwtImpl> {
  }

  /**
   * content container.
   */
  @UiField
  SimplePanel container;

  /**
   * navigation container.
   */
  @UiField
  ScrollPanel navigation;

  /**
   * copyright text should be uses as link to info page.
   */
  @UiField
  HTML info;

  /**
   * remember the presenter.
   */
  private BasePagePresenterInterface presenter;

  /**
   * default constructor.
   */
  public BasePageViewGwtImpl() {
    super();

    this.initWidget(uiBinder.createAndBindUi(this));
  }

  @Override
  public final HasOneWidget getContentContainer() {
    return this.container;
  }

  @Override
  public final HasOneWidget getNavigationContainer() {
    return this.navigation;
  }

  /**
   * click on the login button.
   *
   * @param pclickEvent click event.
   */
  @UiHandler("info")
  final void showInfos(final ClickEvent pclickEvent) {
    this.presenter.showInfo();
  }

  @Override
  public final void setPresenter(final BasePagePresenterInterface pbasePagePresenterInterface) {
    this.presenter = pbasePagePresenterInterface;
  }
}

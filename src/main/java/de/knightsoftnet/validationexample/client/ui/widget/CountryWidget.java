package de.knightsoftnet.validationexample.client.ui.widget;

import de.knightsoftnet.validationexample.shared.models.CountryEnum;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.ui.HasValue;
import com.google.gwt.user.client.ui.ListBox;

public class CountryWidget extends ListBox implements HasValue<CountryEnum> {
  private boolean valueChangeHandlerInitialized;

  /**
   * default constructor.
   */
  public CountryWidget() {
    super();
    this.setVisibleItemCount(1);

    final CountryMessages messages = GWT.create(CountryMessages.class);

    this.clear();
    for (final CountryEnum proEnum : CountryEnum.values()) {
      this.addItem(messages.country(proEnum), proEnum.toString());
    }
  }

  @Override
  public HandlerRegistration addValueChangeHandler(final ValueChangeHandler<CountryEnum> phandler) {
    // Is this the first value change handler? If so, time to add handlers
    if (!this.valueChangeHandlerInitialized) {
      this.ensureDomEventHandlers();
      this.valueChangeHandlerInitialized = true;
    }
    return this.addHandler(phandler, ValueChangeEvent.getType());
  }

  protected void ensureDomEventHandlers() {
    this.addChangeHandler(new ChangeHandler() {
      @Override
      public void onChange(final ChangeEvent event) {
        ValueChangeEvent.fire(CountryWidget.this, CountryWidget.this.getValue());
      }
    });
  }

  @Override
  public CountryEnum getValue() {
    return CountryEnum.values()[this.getSelectedIndex()];
  }

  @Override
  public void setValue(final CountryEnum pvalue) {
    this.setValue(pvalue, false);
  }

  @Override
  public void setValue(final CountryEnum pvalue, final boolean pfireEvents) {
    this.setSelectedIndex(pvalue.ordinal());
    if (pfireEvents) {
      ValueChangeEvent.fire(this, pvalue);
    }
  }

}
